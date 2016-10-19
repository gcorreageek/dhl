package com.hsn.serv.web.rest;

import com.hsn.serv.Prueba1App;

import com.hsn.serv.domain.Friend;
import com.hsn.serv.repository.FriendRepository;
import com.hsn.serv.service.FriendService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FriendResource REST controller.
 *
 * @see FriendResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Prueba1App.class)
public class FriendResourceIntTest {

    private static final String DEFAULT_FRIEND_TYPE = "AAAAA";
    private static final String UPDATED_FRIEND_TYPE = "BBBBB";

    @Inject
    private FriendRepository friendRepository;

    @Inject
    private FriendService friendService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restFriendMockMvc;

    private Friend friend;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FriendResource friendResource = new FriendResource();
        ReflectionTestUtils.setField(friendResource, "friendService", friendService);
        this.restFriendMockMvc = MockMvcBuilders.standaloneSetup(friendResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Friend createEntity(EntityManager em) {
        Friend friend = new Friend();
        friend.setFriendType(DEFAULT_FRIEND_TYPE);
        return friend;
    }

    @Before
    public void initTest() {
        friend = createEntity(em);
    }

    @Test
    @Transactional
    public void createFriend() throws Exception {
        int databaseSizeBeforeCreate = friendRepository.findAll().size();

        // Create the Friend

        restFriendMockMvc.perform(post("/api/friends")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(friend)))
                .andExpect(status().isCreated());

        // Validate the Friend in the database
        List<Friend> friends = friendRepository.findAll();
        assertThat(friends).hasSize(databaseSizeBeforeCreate + 1);
        Friend testFriend = friends.get(friends.size() - 1);
        assertThat(testFriend.getFriendType()).isEqualTo(DEFAULT_FRIEND_TYPE);
    }

    @Test
    @Transactional
    public void getAllFriends() throws Exception {
        // Initialize the database
        friendRepository.saveAndFlush(friend);

        // Get all the friends
        restFriendMockMvc.perform(get("/api/friends?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(friend.getId().intValue())))
                .andExpect(jsonPath("$.[*].friendType").value(hasItem(DEFAULT_FRIEND_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getFriend() throws Exception {
        // Initialize the database
        friendRepository.saveAndFlush(friend);

        // Get the friend
        restFriendMockMvc.perform(get("/api/friends/{id}", friend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(friend.getId().intValue()))
            .andExpect(jsonPath("$.friendType").value(DEFAULT_FRIEND_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFriend() throws Exception {
        // Get the friend
        restFriendMockMvc.perform(get("/api/friends/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFriend() throws Exception {
        // Initialize the database
        friendService.save(friend);

        int databaseSizeBeforeUpdate = friendRepository.findAll().size();

        // Update the friend
        Friend updatedFriend = friendRepository.findOne(friend.getId());
        updatedFriend.setFriendType(UPDATED_FRIEND_TYPE);

        restFriendMockMvc.perform(put("/api/friends")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedFriend)))
                .andExpect(status().isOk());

        // Validate the Friend in the database
        List<Friend> friends = friendRepository.findAll();
        assertThat(friends).hasSize(databaseSizeBeforeUpdate);
        Friend testFriend = friends.get(friends.size() - 1);
        assertThat(testFriend.getFriendType()).isEqualTo(UPDATED_FRIEND_TYPE);
    }

    @Test
    @Transactional
    public void deleteFriend() throws Exception {
        // Initialize the database
        friendService.save(friend);

        int databaseSizeBeforeDelete = friendRepository.findAll().size();

        // Get the friend
        restFriendMockMvc.perform(delete("/api/friends/{id}", friend.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Friend> friends = friendRepository.findAll();
        assertThat(friends).hasSize(databaseSizeBeforeDelete - 1);
    }
}
