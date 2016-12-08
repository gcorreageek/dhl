package com.dhl.serv.service;

import com.dhl.serv.domain.Article;

import java.io.IOException;
import java.util.List;

/**
 * Service Interface for managing Article.
 */
public interface ArticleService {

    /**
     * Save a article.
     *
     * @param article the entity to save
     * @return the persisted entity
     */
    Article save(Article article) throws IOException;

    /**
     *  Get all the articles.
     *
     *  @return the list of entities
     */
    List<Article> findAll();
    List<Article> findByUserIsCurrentUser();
    List<Article> findByPreferencesHash();

    /**
     *  Get the "id" article.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Article findOne(Long id);

    /**
     *  Delete the "id" article.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
