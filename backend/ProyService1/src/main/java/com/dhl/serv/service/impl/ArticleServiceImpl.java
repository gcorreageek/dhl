package com.dhl.serv.service.impl;

import com.dhl.serv.service.ArticleService;
import com.dhl.serv.domain.Article;
import com.dhl.serv.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Article.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
    
    @Inject
    private ArticleRepository articleRepository;

    /**
     * Save a article.
     *
     * @param article the entity to save
     * @return the persisted entity
     */
    public Article save(Article article) {
        log.debug("Request to save Article : {}", article);
        Article result = articleRepository.save(article);
        return result;
    }

    /**
     *  Get all the articles.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Article> findAll() {
        log.debug("Request to get all Articles");
        List<Article> result = articleRepository.findAll();

        return result;
    }

    /**
     *  Get one article by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Article findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        Article article = articleRepository.findOne(id);
        return article;
    }

    /**
     *  Delete the  article by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.delete(id);
    }
}
