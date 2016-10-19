package com.hsn.serv.web.rest;

import com.hsn.serv.ProyService1App;

import com.hsn.serv.domain.ArticleReaction;
import com.hsn.serv.repository.ArticleReactionRepository;
import com.hsn.serv.service.ArticleReactionService;

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
 * Test class for the ArticleReactionResource REST controller.
 *
 * @see ArticleReactionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyService1App.class)
public class ArticleReactionResourceIntTest {

    private static final String DEFAULT_ARTICLE_REACTION_TYPE = "AAAAA";
    private static final String UPDATED_ARTICLE_REACTION_TYPE = "BBBBB";

    @Inject
    private ArticleReactionRepository articleReactionRepository;

    @Inject
    private ArticleReactionService articleReactionService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restArticleReactionMockMvc;

    private ArticleReaction articleReaction;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArticleReactionResource articleReactionResource = new ArticleReactionResource();
        ReflectionTestUtils.setField(articleReactionResource, "articleReactionService", articleReactionService);
        this.restArticleReactionMockMvc = MockMvcBuilders.standaloneSetup(articleReactionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleReaction createEntity(EntityManager em) {
        ArticleReaction articleReaction = new ArticleReaction();
        articleReaction.setArticleReactionType(DEFAULT_ARTICLE_REACTION_TYPE);
        return articleReaction;
    }

    @Before
    public void initTest() {
        articleReaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleReaction() throws Exception {
        int databaseSizeBeforeCreate = articleReactionRepository.findAll().size();

        // Create the ArticleReaction

        restArticleReactionMockMvc.perform(post("/api/article-reactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(articleReaction)))
                .andExpect(status().isCreated());

        // Validate the ArticleReaction in the database
        List<ArticleReaction> articleReactions = articleReactionRepository.findAll();
        assertThat(articleReactions).hasSize(databaseSizeBeforeCreate + 1);
        ArticleReaction testArticleReaction = articleReactions.get(articleReactions.size() - 1);
        assertThat(testArticleReaction.getArticleReactionType()).isEqualTo(DEFAULT_ARTICLE_REACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllArticleReactions() throws Exception {
        // Initialize the database
        articleReactionRepository.saveAndFlush(articleReaction);

        // Get all the articleReactions
        restArticleReactionMockMvc.perform(get("/api/article-reactions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(articleReaction.getId().intValue())))
                .andExpect(jsonPath("$.[*].articleReactionType").value(hasItem(DEFAULT_ARTICLE_REACTION_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getArticleReaction() throws Exception {
        // Initialize the database
        articleReactionRepository.saveAndFlush(articleReaction);

        // Get the articleReaction
        restArticleReactionMockMvc.perform(get("/api/article-reactions/{id}", articleReaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(articleReaction.getId().intValue()))
            .andExpect(jsonPath("$.articleReactionType").value(DEFAULT_ARTICLE_REACTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArticleReaction() throws Exception {
        // Get the articleReaction
        restArticleReactionMockMvc.perform(get("/api/article-reactions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleReaction() throws Exception {
        // Initialize the database
        articleReactionService.save(articleReaction);

        int databaseSizeBeforeUpdate = articleReactionRepository.findAll().size();

        // Update the articleReaction
        ArticleReaction updatedArticleReaction = articleReactionRepository.findOne(articleReaction.getId());
        updatedArticleReaction.setArticleReactionType(UPDATED_ARTICLE_REACTION_TYPE);

        restArticleReactionMockMvc.perform(put("/api/article-reactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedArticleReaction)))
                .andExpect(status().isOk());

        // Validate the ArticleReaction in the database
        List<ArticleReaction> articleReactions = articleReactionRepository.findAll();
        assertThat(articleReactions).hasSize(databaseSizeBeforeUpdate);
        ArticleReaction testArticleReaction = articleReactions.get(articleReactions.size() - 1);
        assertThat(testArticleReaction.getArticleReactionType()).isEqualTo(UPDATED_ARTICLE_REACTION_TYPE);
    }

    @Test
    @Transactional
    public void deleteArticleReaction() throws Exception {
        // Initialize the database
        articleReactionService.save(articleReaction);

        int databaseSizeBeforeDelete = articleReactionRepository.findAll().size();

        // Get the articleReaction
        restArticleReactionMockMvc.perform(delete("/api/article-reactions/{id}", articleReaction.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ArticleReaction> articleReactions = articleReactionRepository.findAll();
        assertThat(articleReactions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
