package com.hsn.serv.service;

import com.hsn.serv.domain.ArticleReaction;

import java.util.List;

/**
 * Service Interface for managing ArticleReaction.
 */
public interface ArticleReactionService {

    /**
     * Save a articleReaction.
     *
     * @param articleReaction the entity to save
     * @return the persisted entity
     */
    ArticleReaction save(ArticleReaction articleReaction);

    /**
     *  Get all the articleReactions.
     *  
     *  @return the list of entities
     */
    List<ArticleReaction> findAll();

    /**
     *  Get the "id" articleReaction.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ArticleReaction findOne(Long id);

    /**
     *  Delete the "id" articleReaction.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
