package com.dhl.serv.web.rest;

import com.dhl.serv.ProyServ1App;

import com.dhl.serv.domain.UserPlus;
import com.dhl.serv.repository.UserPlusRepository;

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
 * Test class for the UserPlusResource REST controller.
 *
 * @see UserPlusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyServ1App.class)
public class UserPlusResourceIntTest {

    private static final String DEFAULT_INFO_1 = "AAAAA";
    private static final String UPDATED_INFO_1 = "BBBBB";
    private static final String DEFAULT_INFO_2 = "AAAAA";
    private static final String UPDATED_INFO_2 = "BBBBB";
    private static final String DEFAULT_INFO_3 = "AAAAA";
    private static final String UPDATED_INFO_3 = "BBBBB";
    private static final String DEFAULT_INFO_4 = "AAAAA";
    private static final String UPDATED_INFO_4 = "BBBBB";
    private static final String DEFAULT_INFO_5 = "AAAAA";
    private static final String UPDATED_INFO_5 = "BBBBB";
    private static final String DEFAULT_INFO_6 = "AAAAA";
    private static final String UPDATED_INFO_6 = "BBBBB";
    private static final String DEFAULT_INFO_7 = "AAAAA";
    private static final String UPDATED_INFO_7 = "BBBBB";
    private static final String DEFAULT_INFO_8 = "AAAAA";
    private static final String UPDATED_INFO_8 = "BBBBB";
    private static final String DEFAULT_INFO_9 = "AAAAA";
    private static final String UPDATED_INFO_9 = "BBBBB";
    private static final String DEFAULT_INFO_10 = "AAAAA";
    private static final String UPDATED_INFO_10 = "BBBBB";

    @Inject
    private UserPlusRepository userPlusRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserPlusMockMvc;

    private UserPlus userPlus;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserPlusResource userPlusResource = new UserPlusResource();
        ReflectionTestUtils.setField(userPlusResource, "userPlusRepository", userPlusRepository);
        this.restUserPlusMockMvc = MockMvcBuilders.standaloneSetup(userPlusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPlus createEntity(EntityManager em) {
        UserPlus userPlus = new UserPlus();
        userPlus.setInfo1(DEFAULT_INFO_1);
        userPlus.setInfo2(DEFAULT_INFO_2);
        userPlus.setInfo3(DEFAULT_INFO_3);
        userPlus.setInfo4(DEFAULT_INFO_4);
        userPlus.setInfo5(DEFAULT_INFO_5);
        userPlus.setInfo6(DEFAULT_INFO_6);
        userPlus.setInfo7(DEFAULT_INFO_7);
        userPlus.setInfo8(DEFAULT_INFO_8);
        userPlus.setInfo9(DEFAULT_INFO_9);
        userPlus.setInfo10(DEFAULT_INFO_10);
        return userPlus;
    }

    @Before
    public void initTest() {
        userPlus = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPlus() throws Exception {
        int databaseSizeBeforeCreate = userPlusRepository.findAll().size();

        // Create the UserPlus

        restUserPlusMockMvc.perform(post("/api/user-pluses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userPlus)))
                .andExpect(status().isCreated());

        // Validate the UserPlus in the database
        List<UserPlus> userPluses = userPlusRepository.findAll();
        assertThat(userPluses).hasSize(databaseSizeBeforeCreate + 1);
        UserPlus testUserPlus = userPluses.get(userPluses.size() - 1);
        assertThat(testUserPlus.getInfo1()).isEqualTo(DEFAULT_INFO_1);
        assertThat(testUserPlus.getInfo2()).isEqualTo(DEFAULT_INFO_2);
        assertThat(testUserPlus.getInfo3()).isEqualTo(DEFAULT_INFO_3);
        assertThat(testUserPlus.getInfo4()).isEqualTo(DEFAULT_INFO_4);
        assertThat(testUserPlus.getInfo5()).isEqualTo(DEFAULT_INFO_5);
        assertThat(testUserPlus.getInfo6()).isEqualTo(DEFAULT_INFO_6);
        assertThat(testUserPlus.getInfo7()).isEqualTo(DEFAULT_INFO_7);
        assertThat(testUserPlus.getInfo8()).isEqualTo(DEFAULT_INFO_8);
        assertThat(testUserPlus.getInfo9()).isEqualTo(DEFAULT_INFO_9);
        assertThat(testUserPlus.getInfo10()).isEqualTo(DEFAULT_INFO_10);
    }

    @Test
    @Transactional
    public void getAllUserPluses() throws Exception {
        // Initialize the database
        userPlusRepository.saveAndFlush(userPlus);

        // Get all the userPluses
        restUserPlusMockMvc.perform(get("/api/user-pluses?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(userPlus.getId().intValue())))
                .andExpect(jsonPath("$.[*].info1").value(hasItem(DEFAULT_INFO_1.toString())))
                .andExpect(jsonPath("$.[*].info2").value(hasItem(DEFAULT_INFO_2.toString())))
                .andExpect(jsonPath("$.[*].info3").value(hasItem(DEFAULT_INFO_3.toString())))
                .andExpect(jsonPath("$.[*].info4").value(hasItem(DEFAULT_INFO_4.toString())))
                .andExpect(jsonPath("$.[*].info5").value(hasItem(DEFAULT_INFO_5.toString())))
                .andExpect(jsonPath("$.[*].info6").value(hasItem(DEFAULT_INFO_6.toString())))
                .andExpect(jsonPath("$.[*].info7").value(hasItem(DEFAULT_INFO_7.toString())))
                .andExpect(jsonPath("$.[*].info8").value(hasItem(DEFAULT_INFO_8.toString())))
                .andExpect(jsonPath("$.[*].info9").value(hasItem(DEFAULT_INFO_9.toString())))
                .andExpect(jsonPath("$.[*].info10").value(hasItem(DEFAULT_INFO_10.toString())));
    }

    @Test
    @Transactional
    public void getUserPlus() throws Exception {
        // Initialize the database
        userPlusRepository.saveAndFlush(userPlus);

        // Get the userPlus
        restUserPlusMockMvc.perform(get("/api/user-pluses/{id}", userPlus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userPlus.getId().intValue()))
            .andExpect(jsonPath("$.info1").value(DEFAULT_INFO_1.toString()))
            .andExpect(jsonPath("$.info2").value(DEFAULT_INFO_2.toString()))
            .andExpect(jsonPath("$.info3").value(DEFAULT_INFO_3.toString()))
            .andExpect(jsonPath("$.info4").value(DEFAULT_INFO_4.toString()))
            .andExpect(jsonPath("$.info5").value(DEFAULT_INFO_5.toString()))
            .andExpect(jsonPath("$.info6").value(DEFAULT_INFO_6.toString()))
            .andExpect(jsonPath("$.info7").value(DEFAULT_INFO_7.toString()))
            .andExpect(jsonPath("$.info8").value(DEFAULT_INFO_8.toString()))
            .andExpect(jsonPath("$.info9").value(DEFAULT_INFO_9.toString()))
            .andExpect(jsonPath("$.info10").value(DEFAULT_INFO_10.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserPlus() throws Exception {
        // Get the userPlus
        restUserPlusMockMvc.perform(get("/api/user-pluses/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPlus() throws Exception {
        // Initialize the database
        userPlusRepository.saveAndFlush(userPlus);
        int databaseSizeBeforeUpdate = userPlusRepository.findAll().size();

        // Update the userPlus
        UserPlus updatedUserPlus = userPlusRepository.findOne(userPlus.getId());
        updatedUserPlus.setInfo1(UPDATED_INFO_1);
        updatedUserPlus.setInfo2(UPDATED_INFO_2);
        updatedUserPlus.setInfo3(UPDATED_INFO_3);
        updatedUserPlus.setInfo4(UPDATED_INFO_4);
        updatedUserPlus.setInfo5(UPDATED_INFO_5);
        updatedUserPlus.setInfo6(UPDATED_INFO_6);
        updatedUserPlus.setInfo7(UPDATED_INFO_7);
        updatedUserPlus.setInfo8(UPDATED_INFO_8);
        updatedUserPlus.setInfo9(UPDATED_INFO_9);
        updatedUserPlus.setInfo10(UPDATED_INFO_10);

        restUserPlusMockMvc.perform(put("/api/user-pluses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserPlus)))
                .andExpect(status().isOk());

        // Validate the UserPlus in the database
        List<UserPlus> userPluses = userPlusRepository.findAll();
        assertThat(userPluses).hasSize(databaseSizeBeforeUpdate);
        UserPlus testUserPlus = userPluses.get(userPluses.size() - 1);
        assertThat(testUserPlus.getInfo1()).isEqualTo(UPDATED_INFO_1);
        assertThat(testUserPlus.getInfo2()).isEqualTo(UPDATED_INFO_2);
        assertThat(testUserPlus.getInfo3()).isEqualTo(UPDATED_INFO_3);
        assertThat(testUserPlus.getInfo4()).isEqualTo(UPDATED_INFO_4);
        assertThat(testUserPlus.getInfo5()).isEqualTo(UPDATED_INFO_5);
        assertThat(testUserPlus.getInfo6()).isEqualTo(UPDATED_INFO_6);
        assertThat(testUserPlus.getInfo7()).isEqualTo(UPDATED_INFO_7);
        assertThat(testUserPlus.getInfo8()).isEqualTo(UPDATED_INFO_8);
        assertThat(testUserPlus.getInfo9()).isEqualTo(UPDATED_INFO_9);
        assertThat(testUserPlus.getInfo10()).isEqualTo(UPDATED_INFO_10);
    }

    @Test
    @Transactional
    public void deleteUserPlus() throws Exception {
        // Initialize the database
        userPlusRepository.saveAndFlush(userPlus);
        int databaseSizeBeforeDelete = userPlusRepository.findAll().size();

        // Get the userPlus
        restUserPlusMockMvc.perform(delete("/api/user-pluses/{id}", userPlus.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserPlus> userPluses = userPlusRepository.findAll();
        assertThat(userPluses).hasSize(databaseSizeBeforeDelete - 1);
    }
}
