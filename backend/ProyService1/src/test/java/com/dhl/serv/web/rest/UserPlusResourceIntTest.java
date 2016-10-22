package com.dhl.serv.web.rest;

import com.dhl.serv.ProyService1App;

import com.dhl.serv.domain.UserPlus;
import com.dhl.serv.repository.UserPlusRepository;
import com.dhl.serv.service.UserPlusService;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
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
@SpringBootTest(classes = ProyService1App.class)
public class UserPlusResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final String DEFAULT_WEIGH = "AAAAA";
    private static final String UPDATED_WEIGH = "BBBBB";
    private static final String DEFAULT_HEIGHT = "AAAAA";
    private static final String UPDATED_HEIGHT = "BBBBB";

    private static final ZonedDateTime DEFAULT_BIRTHDAY = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_BIRTHDAY = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_BIRTHDAY_STR = dateTimeFormatter.format(DEFAULT_BIRTHDAY);
    private static final String DEFAULT_SEX = "AAAAA";
    private static final String UPDATED_SEX = "BBBBB";
    private static final String DEFAULT_COUNTRY = "AAAAA";
    private static final String UPDATED_COUNTRY = "BBBBB";
    private static final String DEFAULT_LANGUAJE = "AAAAA";
    private static final String UPDATED_LANGUAJE = "BBBBB";

    private static final Boolean DEFAULT_DISABLED_PROFILE = false;
    private static final Boolean UPDATED_DISABLED_PROFILE = true;

    private static final Boolean DEFAULT_SHOW_WEIGH = false;
    private static final Boolean UPDATED_SHOW_WEIGH = true;

    private static final Boolean DEFAULT_SHOW_HEIGHT = false;
    private static final Boolean UPDATED_SHOW_HEIGHT = true;

    private static final Boolean DEFAULT_SHOW_BIRTHDAY = false;
    private static final Boolean UPDATED_SHOW_BIRTHDAY = true;

    private static final Boolean DEFAULT_SHOW_SEX = false;
    private static final Boolean UPDATED_SHOW_SEX = true;

    private static final Boolean DEFAULT_SHOW_COUNTRY = false;
    private static final Boolean UPDATED_SHOW_COUNTRY = true;

    private static final Boolean DEFAULT_SHOW_LANGUAJE = false;
    private static final Boolean UPDATED_SHOW_LANGUAJE = true;

    private static final Boolean DEFAULT_NOTIFICATION_NEWS = false;
    private static final Boolean UPDATED_NOTIFICATION_NEWS = true;
    private static final String DEFAULT_OPTIONS = "AAAAA";
    private static final String UPDATED_OPTIONS = "BBBBB";

    @Inject
    private UserPlusRepository userPlusRepository;

    @Inject
    private UserPlusService userPlusService;

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
        ReflectionTestUtils.setField(userPlusResource, "userPlusService", userPlusService);
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
        userPlus.setWeigh(DEFAULT_WEIGH);
        userPlus.setHeight(DEFAULT_HEIGHT);
        userPlus.setBirthday(DEFAULT_BIRTHDAY);
        userPlus.setSex(DEFAULT_SEX);
        userPlus.setCountry(DEFAULT_COUNTRY);
        userPlus.setLanguaje(DEFAULT_LANGUAJE);
        userPlus.setDisabledProfile(DEFAULT_DISABLED_PROFILE);
        userPlus.setShowWeigh(DEFAULT_SHOW_WEIGH);
        userPlus.setShowHeight(DEFAULT_SHOW_HEIGHT);
        userPlus.setShowBirthday(DEFAULT_SHOW_BIRTHDAY);
        userPlus.setShowSex(DEFAULT_SHOW_SEX);
        userPlus.setShowCountry(DEFAULT_SHOW_COUNTRY);
        userPlus.setShowLanguaje(DEFAULT_SHOW_LANGUAJE);
        userPlus.setNotificationNews(DEFAULT_NOTIFICATION_NEWS);
        userPlus.setOptions(DEFAULT_OPTIONS);
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
        assertThat(testUserPlus.getWeigh()).isEqualTo(DEFAULT_WEIGH);
        assertThat(testUserPlus.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testUserPlus.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testUserPlus.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testUserPlus.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserPlus.getLanguaje()).isEqualTo(DEFAULT_LANGUAJE);
        assertThat(testUserPlus.isDisabledProfile()).isEqualTo(DEFAULT_DISABLED_PROFILE);
        assertThat(testUserPlus.isShowWeigh()).isEqualTo(DEFAULT_SHOW_WEIGH);
        assertThat(testUserPlus.isShowHeight()).isEqualTo(DEFAULT_SHOW_HEIGHT);
        assertThat(testUserPlus.isShowBirthday()).isEqualTo(DEFAULT_SHOW_BIRTHDAY);
        assertThat(testUserPlus.isShowSex()).isEqualTo(DEFAULT_SHOW_SEX);
        assertThat(testUserPlus.isShowCountry()).isEqualTo(DEFAULT_SHOW_COUNTRY);
        assertThat(testUserPlus.isShowLanguaje()).isEqualTo(DEFAULT_SHOW_LANGUAJE);
        assertThat(testUserPlus.isNotificationNews()).isEqualTo(DEFAULT_NOTIFICATION_NEWS);
        assertThat(testUserPlus.getOptions()).isEqualTo(DEFAULT_OPTIONS);
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
                .andExpect(jsonPath("$.[*].weigh").value(hasItem(DEFAULT_WEIGH.toString())))
                .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.toString())))
                .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY_STR)))
                .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
                .andExpect(jsonPath("$.[*].languaje").value(hasItem(DEFAULT_LANGUAJE.toString())))
                .andExpect(jsonPath("$.[*].disabledProfile").value(hasItem(DEFAULT_DISABLED_PROFILE.booleanValue())))
                .andExpect(jsonPath("$.[*].showWeigh").value(hasItem(DEFAULT_SHOW_WEIGH.booleanValue())))
                .andExpect(jsonPath("$.[*].showHeight").value(hasItem(DEFAULT_SHOW_HEIGHT.booleanValue())))
                .andExpect(jsonPath("$.[*].showBirthday").value(hasItem(DEFAULT_SHOW_BIRTHDAY.booleanValue())))
                .andExpect(jsonPath("$.[*].showSex").value(hasItem(DEFAULT_SHOW_SEX.booleanValue())))
                .andExpect(jsonPath("$.[*].showCountry").value(hasItem(DEFAULT_SHOW_COUNTRY.booleanValue())))
                .andExpect(jsonPath("$.[*].showLanguaje").value(hasItem(DEFAULT_SHOW_LANGUAJE.booleanValue())))
                .andExpect(jsonPath("$.[*].notificationNews").value(hasItem(DEFAULT_NOTIFICATION_NEWS.booleanValue())))
                .andExpect(jsonPath("$.[*].options").value(hasItem(DEFAULT_OPTIONS.toString())));
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
            .andExpect(jsonPath("$.weigh").value(DEFAULT_WEIGH.toString()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY_STR))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.languaje").value(DEFAULT_LANGUAJE.toString()))
            .andExpect(jsonPath("$.disabledProfile").value(DEFAULT_DISABLED_PROFILE.booleanValue()))
            .andExpect(jsonPath("$.showWeigh").value(DEFAULT_SHOW_WEIGH.booleanValue()))
            .andExpect(jsonPath("$.showHeight").value(DEFAULT_SHOW_HEIGHT.booleanValue()))
            .andExpect(jsonPath("$.showBirthday").value(DEFAULT_SHOW_BIRTHDAY.booleanValue()))
            .andExpect(jsonPath("$.showSex").value(DEFAULT_SHOW_SEX.booleanValue()))
            .andExpect(jsonPath("$.showCountry").value(DEFAULT_SHOW_COUNTRY.booleanValue()))
            .andExpect(jsonPath("$.showLanguaje").value(DEFAULT_SHOW_LANGUAJE.booleanValue()))
            .andExpect(jsonPath("$.notificationNews").value(DEFAULT_NOTIFICATION_NEWS.booleanValue()))
            .andExpect(jsonPath("$.options").value(DEFAULT_OPTIONS.toString()));
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
        userPlusService.save(userPlus);

        int databaseSizeBeforeUpdate = userPlusRepository.findAll().size();

        // Update the userPlus
        UserPlus updatedUserPlus = userPlusRepository.findOne(userPlus.getId());
        updatedUserPlus.setWeigh(UPDATED_WEIGH);
        updatedUserPlus.setHeight(UPDATED_HEIGHT);
        updatedUserPlus.setBirthday(UPDATED_BIRTHDAY);
        updatedUserPlus.setSex(UPDATED_SEX);
        updatedUserPlus.setCountry(UPDATED_COUNTRY);
        updatedUserPlus.setLanguaje(UPDATED_LANGUAJE);
        updatedUserPlus.setDisabledProfile(UPDATED_DISABLED_PROFILE);
        updatedUserPlus.setShowWeigh(UPDATED_SHOW_WEIGH);
        updatedUserPlus.setShowHeight(UPDATED_SHOW_HEIGHT);
        updatedUserPlus.setShowBirthday(UPDATED_SHOW_BIRTHDAY);
        updatedUserPlus.setShowSex(UPDATED_SHOW_SEX);
        updatedUserPlus.setShowCountry(UPDATED_SHOW_COUNTRY);
        updatedUserPlus.setShowLanguaje(UPDATED_SHOW_LANGUAJE);
        updatedUserPlus.setNotificationNews(UPDATED_NOTIFICATION_NEWS);
        updatedUserPlus.setOptions(UPDATED_OPTIONS);

        restUserPlusMockMvc.perform(put("/api/user-pluses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserPlus)))
                .andExpect(status().isOk());

        // Validate the UserPlus in the database
        List<UserPlus> userPluses = userPlusRepository.findAll();
        assertThat(userPluses).hasSize(databaseSizeBeforeUpdate);
        UserPlus testUserPlus = userPluses.get(userPluses.size() - 1);
        assertThat(testUserPlus.getWeigh()).isEqualTo(UPDATED_WEIGH);
        assertThat(testUserPlus.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testUserPlus.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testUserPlus.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testUserPlus.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserPlus.getLanguaje()).isEqualTo(UPDATED_LANGUAJE);
        assertThat(testUserPlus.isDisabledProfile()).isEqualTo(UPDATED_DISABLED_PROFILE);
        assertThat(testUserPlus.isShowWeigh()).isEqualTo(UPDATED_SHOW_WEIGH);
        assertThat(testUserPlus.isShowHeight()).isEqualTo(UPDATED_SHOW_HEIGHT);
        assertThat(testUserPlus.isShowBirthday()).isEqualTo(UPDATED_SHOW_BIRTHDAY);
        assertThat(testUserPlus.isShowSex()).isEqualTo(UPDATED_SHOW_SEX);
        assertThat(testUserPlus.isShowCountry()).isEqualTo(UPDATED_SHOW_COUNTRY);
        assertThat(testUserPlus.isShowLanguaje()).isEqualTo(UPDATED_SHOW_LANGUAJE);
        assertThat(testUserPlus.isNotificationNews()).isEqualTo(UPDATED_NOTIFICATION_NEWS);
        assertThat(testUserPlus.getOptions()).isEqualTo(UPDATED_OPTIONS);
    }

    @Test
    @Transactional
    public void deleteUserPlus() throws Exception {
        // Initialize the database
        userPlusService.save(userPlus);

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
