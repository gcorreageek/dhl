package com.dhl.serv.web.rest;

import com.dhl.serv.ProyService1App;

import com.dhl.serv.domain.Hash;
import com.dhl.serv.repository.HashRepository;
import com.dhl.serv.service.HashService;

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
 * Test class for the HashResource REST controller.
 *
 * @see HashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyService1App.class)
public class HashResourceIntTest {

    private static final String DEFAULT_HASH_NAME = "AAAAA";
    private static final String UPDATED_HASH_NAME = "BBBBB";
    private static final String DEFAULT_HASH_TYPE = "AAAAA";
    private static final String UPDATED_HASH_TYPE = "BBBBB";

    @Inject
    private HashRepository hashRepository;

    @Inject
    private HashService hashService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restHashMockMvc;

    private Hash hash;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HashResource hashResource = new HashResource();
        ReflectionTestUtils.setField(hashResource, "hashService", hashService);
        this.restHashMockMvc = MockMvcBuilders.standaloneSetup(hashResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hash createEntity(EntityManager em) {
        Hash hash = new Hash();
        hash.setHashName(DEFAULT_HASH_NAME);
        hash.setHashType(DEFAULT_HASH_TYPE);
        return hash;
    }

    @Before
    public void initTest() {
        hash = createEntity(em);
    }

    @Test
    @Transactional
    public void createHash() throws Exception {
        int databaseSizeBeforeCreate = hashRepository.findAll().size();

        // Create the Hash

        restHashMockMvc.perform(post("/api/hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hash)))
                .andExpect(status().isCreated());

        // Validate the Hash in the database
        List<Hash> hashes = hashRepository.findAll();
        assertThat(hashes).hasSize(databaseSizeBeforeCreate + 1);
        Hash testHash = hashes.get(hashes.size() - 1);
        assertThat(testHash.getHashName()).isEqualTo(DEFAULT_HASH_NAME);
        assertThat(testHash.getHashType()).isEqualTo(DEFAULT_HASH_TYPE);
    }

    @Test
    @Transactional
    public void getAllHashes() throws Exception {
        // Initialize the database
        hashRepository.saveAndFlush(hash);

        // Get all the hashes
        restHashMockMvc.perform(get("/api/hashes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(hash.getId().intValue())))
                .andExpect(jsonPath("$.[*].hashName").value(hasItem(DEFAULT_HASH_NAME.toString())))
                .andExpect(jsonPath("$.[*].hashType").value(hasItem(DEFAULT_HASH_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getHash() throws Exception {
        // Initialize the database
        hashRepository.saveAndFlush(hash);

        // Get the hash
        restHashMockMvc.perform(get("/api/hashes/{id}", hash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hash.getId().intValue()))
            .andExpect(jsonPath("$.hashName").value(DEFAULT_HASH_NAME.toString()))
            .andExpect(jsonPath("$.hashType").value(DEFAULT_HASH_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHash() throws Exception {
        // Get the hash
        restHashMockMvc.perform(get("/api/hashes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHash() throws Exception {
        // Initialize the database
        hashService.save(hash);

        int databaseSizeBeforeUpdate = hashRepository.findAll().size();

        // Update the hash
        Hash updatedHash = hashRepository.findOne(hash.getId());
        updatedHash.setHashName(UPDATED_HASH_NAME);
        updatedHash.setHashType(UPDATED_HASH_TYPE);

        restHashMockMvc.perform(put("/api/hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedHash)))
                .andExpect(status().isOk());

        // Validate the Hash in the database
        List<Hash> hashes = hashRepository.findAll();
        assertThat(hashes).hasSize(databaseSizeBeforeUpdate);
        Hash testHash = hashes.get(hashes.size() - 1);
        assertThat(testHash.getHashName()).isEqualTo(UPDATED_HASH_NAME);
        assertThat(testHash.getHashType()).isEqualTo(UPDATED_HASH_TYPE);
    }

    @Test
    @Transactional
    public void deleteHash() throws Exception {
        // Initialize the database
        hashService.save(hash);

        int databaseSizeBeforeDelete = hashRepository.findAll().size();

        // Get the hash
        restHashMockMvc.perform(delete("/api/hashes/{id}", hash.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Hash> hashes = hashRepository.findAll();
        assertThat(hashes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
