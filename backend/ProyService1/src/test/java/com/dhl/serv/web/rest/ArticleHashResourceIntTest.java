package com.dhl.serv.web.rest;

import com.dhl.serv.ProyService1App;

import com.dhl.serv.domain.ArticleHash;
import com.dhl.serv.repository.ArticleHashRepository;
import com.dhl.serv.service.ArticleHashService;

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
 * Test class for the ArticleHashResource REST controller.
 *
 * @see ArticleHashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyService1App.class)
public class ArticleHashResourceIntTest {


    @Inject
    private ArticleHashRepository articleHashRepository;

    @Inject
    private ArticleHashService articleHashService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restArticleHashMockMvc;

    private ArticleHash articleHash;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArticleHashResource articleHashResource = new ArticleHashResource();
        ReflectionTestUtils.setField(articleHashResource, "articleHashService", articleHashService);
        this.restArticleHashMockMvc = MockMvcBuilders.standaloneSetup(articleHashResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleHash createEntity(EntityManager em) {
        ArticleHash articleHash = new ArticleHash();
        return articleHash;
    }

    @Before
    public void initTest() {
        articleHash = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleHash() throws Exception {
        int databaseSizeBeforeCreate = articleHashRepository.findAll().size();

        // Create the ArticleHash

        restArticleHashMockMvc.perform(post("/api/article-hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(articleHash)))
                .andExpect(status().isCreated());

        // Validate the ArticleHash in the database
        List<ArticleHash> articleHashes = articleHashRepository.findAll();
        assertThat(articleHashes).hasSize(databaseSizeBeforeCreate + 1);
        ArticleHash testArticleHash = articleHashes.get(articleHashes.size() - 1);
    }

    @Test
    @Transactional
    public void getAllArticleHashes() throws Exception {
        // Initialize the database
        articleHashRepository.saveAndFlush(articleHash);

        // Get all the articleHashes
        restArticleHashMockMvc.perform(get("/api/article-hashes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(articleHash.getId().intValue())));
    }

    @Test
    @Transactional
    public void getArticleHash() throws Exception {
        // Initialize the database
        articleHashRepository.saveAndFlush(articleHash);

        // Get the articleHash
        restArticleHashMockMvc.perform(get("/api/article-hashes/{id}", articleHash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(articleHash.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingArticleHash() throws Exception {
        // Get the articleHash
        restArticleHashMockMvc.perform(get("/api/article-hashes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleHash() throws Exception {
        // Initialize the database
        articleHashService.save(articleHash);

        int databaseSizeBeforeUpdate = articleHashRepository.findAll().size();

        // Update the articleHash
        ArticleHash updatedArticleHash = articleHashRepository.findOne(articleHash.getId());

        restArticleHashMockMvc.perform(put("/api/article-hashes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedArticleHash)))
                .andExpect(status().isOk());

        // Validate the ArticleHash in the database
        List<ArticleHash> articleHashes = articleHashRepository.findAll();
        assertThat(articleHashes).hasSize(databaseSizeBeforeUpdate);
        ArticleHash testArticleHash = articleHashes.get(articleHashes.size() - 1);
    }

    @Test
    @Transactional
    public void deleteArticleHash() throws Exception {
        // Initialize the database
        articleHashService.save(articleHash);

        int databaseSizeBeforeDelete = articleHashRepository.findAll().size();

        // Get the articleHash
        restArticleHashMockMvc.perform(delete("/api/article-hashes/{id}", articleHash.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ArticleHash> articleHashes = articleHashRepository.findAll();
        assertThat(articleHashes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
