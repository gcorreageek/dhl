package com.dhl.serv.web.rest;

import com.dhl.serv.ProyService1App;

import com.dhl.serv.domain.UserImagen;
import com.dhl.serv.repository.UserImagenRepository;
import com.dhl.serv.service.UserImagenService;

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
 * Test class for the UserImagenResource REST controller.
 *
 * @see UserImagenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyService1App.class)
public class UserImagenResourceIntTest {

    private static final String DEFAULT_USER_IMAGEN_NAME = "AAAAA";
    private static final String UPDATED_USER_IMAGEN_NAME = "BBBBB";
    private static final String DEFAULT_USER_IMAGEN_PATH = "AAAAA";
    private static final String UPDATED_USER_IMAGEN_PATH = "BBBBB";
    private static final String DEFAULT_USER_IMAGEN_PATH_IMAGE = "AAAAA";
    private static final String UPDATED_USER_IMAGEN_PATH_IMAGE = "BBBBB";

    private static final Boolean DEFAULT_USER_IMAGEN_MAIN = false;
    private static final Boolean UPDATED_USER_IMAGEN_MAIN = true;

    @Inject
    private UserImagenRepository userImagenRepository;

    @Inject
    private UserImagenService userImagenService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserImagenMockMvc;

    private UserImagen userImagen;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserImagenResource userImagenResource = new UserImagenResource();
        ReflectionTestUtils.setField(userImagenResource, "userImagenService", userImagenService);
        this.restUserImagenMockMvc = MockMvcBuilders.standaloneSetup(userImagenResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserImagen createEntity(EntityManager em) {
        UserImagen userImagen = new UserImagen();
        userImagen.setUserImagenName(DEFAULT_USER_IMAGEN_NAME);
        userImagen.setUserImagenPath(DEFAULT_USER_IMAGEN_PATH);
        userImagen.setUserImagenPathImage(DEFAULT_USER_IMAGEN_PATH_IMAGE);
        userImagen.setUserImagenMain(DEFAULT_USER_IMAGEN_MAIN);
        return userImagen;
    }

    @Before
    public void initTest() {
        userImagen = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserImagen() throws Exception {
        int databaseSizeBeforeCreate = userImagenRepository.findAll().size();

        // Create the UserImagen

        restUserImagenMockMvc.perform(post("/api/user-imagens")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userImagen)))
                .andExpect(status().isCreated());

        // Validate the UserImagen in the database
        List<UserImagen> userImagens = userImagenRepository.findAll();
        assertThat(userImagens).hasSize(databaseSizeBeforeCreate + 1);
        UserImagen testUserImagen = userImagens.get(userImagens.size() - 1);
        assertThat(testUserImagen.getUserImagenName()).isEqualTo(DEFAULT_USER_IMAGEN_NAME);
        assertThat(testUserImagen.getUserImagenPath()).isEqualTo(DEFAULT_USER_IMAGEN_PATH);
        assertThat(testUserImagen.getUserImagenPathImage()).isEqualTo(DEFAULT_USER_IMAGEN_PATH_IMAGE);
        assertThat(testUserImagen.isUserImagenMain()).isEqualTo(DEFAULT_USER_IMAGEN_MAIN);
    }

    @Test
    @Transactional
    public void getAllUserImagens() throws Exception {
        // Initialize the database
        userImagenRepository.saveAndFlush(userImagen);

        // Get all the userImagens
        restUserImagenMockMvc.perform(get("/api/user-imagens?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(userImagen.getId().intValue())))
                .andExpect(jsonPath("$.[*].userImagenName").value(hasItem(DEFAULT_USER_IMAGEN_NAME.toString())))
                .andExpect(jsonPath("$.[*].userImagenPath").value(hasItem(DEFAULT_USER_IMAGEN_PATH.toString())))
                .andExpect(jsonPath("$.[*].userImagenPathImage").value(hasItem(DEFAULT_USER_IMAGEN_PATH_IMAGE.toString())))
                .andExpect(jsonPath("$.[*].userImagenMain").value(hasItem(DEFAULT_USER_IMAGEN_MAIN.booleanValue())));
    }

    @Test
    @Transactional
    public void getUserImagen() throws Exception {
        // Initialize the database
        userImagenRepository.saveAndFlush(userImagen);

        // Get the userImagen
        restUserImagenMockMvc.perform(get("/api/user-imagens/{id}", userImagen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userImagen.getId().intValue()))
            .andExpect(jsonPath("$.userImagenName").value(DEFAULT_USER_IMAGEN_NAME.toString()))
            .andExpect(jsonPath("$.userImagenPath").value(DEFAULT_USER_IMAGEN_PATH.toString()))
            .andExpect(jsonPath("$.userImagenPathImage").value(DEFAULT_USER_IMAGEN_PATH_IMAGE.toString()))
            .andExpect(jsonPath("$.userImagenMain").value(DEFAULT_USER_IMAGEN_MAIN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserImagen() throws Exception {
        // Get the userImagen
        restUserImagenMockMvc.perform(get("/api/user-imagens/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserImagen() throws Exception {
        // Initialize the database
        userImagenService.save(userImagen);

        int databaseSizeBeforeUpdate = userImagenRepository.findAll().size();

        // Update the userImagen
        UserImagen updatedUserImagen = userImagenRepository.findOne(userImagen.getId());
        updatedUserImagen.setUserImagenName(UPDATED_USER_IMAGEN_NAME);
        updatedUserImagen.setUserImagenPath(UPDATED_USER_IMAGEN_PATH);
        updatedUserImagen.setUserImagenPathImage(UPDATED_USER_IMAGEN_PATH_IMAGE);
        updatedUserImagen.setUserImagenMain(UPDATED_USER_IMAGEN_MAIN);

        restUserImagenMockMvc.perform(put("/api/user-imagens")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserImagen)))
                .andExpect(status().isOk());

        // Validate the UserImagen in the database
        List<UserImagen> userImagens = userImagenRepository.findAll();
        assertThat(userImagens).hasSize(databaseSizeBeforeUpdate);
        UserImagen testUserImagen = userImagens.get(userImagens.size() - 1);
        assertThat(testUserImagen.getUserImagenName()).isEqualTo(UPDATED_USER_IMAGEN_NAME);
        assertThat(testUserImagen.getUserImagenPath()).isEqualTo(UPDATED_USER_IMAGEN_PATH);
        assertThat(testUserImagen.getUserImagenPathImage()).isEqualTo(UPDATED_USER_IMAGEN_PATH_IMAGE);
        assertThat(testUserImagen.isUserImagenMain()).isEqualTo(UPDATED_USER_IMAGEN_MAIN);
    }

    @Test
    @Transactional
    public void deleteUserImagen() throws Exception {
        // Initialize the database
        userImagenService.save(userImagen);

        int databaseSizeBeforeDelete = userImagenRepository.findAll().size();

        // Get the userImagen
        restUserImagenMockMvc.perform(delete("/api/user-imagens/{id}", userImagen.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserImagen> userImagens = userImagenRepository.findAll();
        assertThat(userImagens).hasSize(databaseSizeBeforeDelete - 1);
    }
}
