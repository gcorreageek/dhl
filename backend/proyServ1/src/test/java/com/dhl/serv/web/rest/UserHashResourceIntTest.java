package com.dhl.serv.web.rest;

import com.dhl.serv.ProyServ1App;

import com.dhl.serv.domain.UserHash;
import com.dhl.serv.repository.UserHashRepository;

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
 * Test class for the UserHashResource REST controller.
 *
 * @see UserHashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyServ1App.class)
public class UserHashResourceIntTest {


    @Inject
    private UserHashRepository userHashRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserHashMockMvc;

    private UserHash userHash;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserHashResource userHashResource = new UserHashResource();
        ReflectionTestUtils.setField(userHashResource, "userHashRepository", userHashRepository);
        this.restUserHashMockMvc = MockMvcBuilders.standaloneSetup(userHashResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserHash createEntity(EntityManager em) {
        UserHash userHash = new UserHash();
        return userHash;
    }

    @Before
    public void initTest() {
        userHash = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserHash() throws Exception {
        int databaseSizeBeforeCreate = userHashRepository.findAll().size();

        // Create the UserHash

        restUserHashMockMvc.perform(post("/api/user-hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userHash)))
                .andExpect(status().isCreated());

        // Validate the UserHash in the database
        List<UserHash> userHashes = userHashRepository.findAll();
        assertThat(userHashes).hasSize(databaseSizeBeforeCreate + 1);
        UserHash testUserHash = userHashes.get(userHashes.size() - 1);
    }

    @Test
    @Transactional
    public void getAllUserHashes() throws Exception {
        // Initialize the database
        userHashRepository.saveAndFlush(userHash);

        // Get all the userHashes
        restUserHashMockMvc.perform(get("/api/user-hashes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(userHash.getId().intValue())));
    }

    @Test
    @Transactional
    public void getUserHash() throws Exception {
        // Initialize the database
        userHashRepository.saveAndFlush(userHash);

        // Get the userHash
        restUserHashMockMvc.perform(get("/api/user-hashes/{id}", userHash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userHash.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserHash() throws Exception {
        // Get the userHash
        restUserHashMockMvc.perform(get("/api/user-hashes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserHash() throws Exception {
        // Initialize the database
        userHashRepository.saveAndFlush(userHash);
        int databaseSizeBeforeUpdate = userHashRepository.findAll().size();

        // Update the userHash
        UserHash updatedUserHash = userHashRepository.findOne(userHash.getId());

        restUserHashMockMvc.perform(put("/api/user-hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserHash)))
                .andExpect(status().isOk());

        // Validate the UserHash in the database
        List<UserHash> userHashes = userHashRepository.findAll();
        assertThat(userHashes).hasSize(databaseSizeBeforeUpdate);
        UserHash testUserHash = userHashes.get(userHashes.size() - 1);
    }

    @Test
    @Transactional
    public void deleteUserHash() throws Exception {
        // Initialize the database
        userHashRepository.saveAndFlush(userHash);
        int databaseSizeBeforeDelete = userHashRepository.findAll().size();

        // Get the userHash
        restUserHashMockMvc.perform(delete("/api/user-hashes/{id}", userHash.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserHash> userHashes = userHashRepository.findAll();
        assertThat(userHashes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
