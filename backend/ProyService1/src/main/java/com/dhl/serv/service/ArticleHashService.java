package com.dhl.serv.service;

import com.dhl.serv.domain.ArticleHash;

import java.util.List;

/**
 * Service Interface for managing ArticleHash.
 */
public interface ArticleHashService {

    /**
     * Save a articleHash.
     *
     * @param articleHash the entity to save
     * @return the persisted entity
     */
    ArticleHash save(ArticleHash articleHash);

    /**
     *  Get all the articleHashes.
     *  
     *  @return the list of entities
     */
    List<ArticleHash> findAll();

    /**
     *  Get the "id" articleHash.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ArticleHash findOne(Long id);

    /**
     *  Delete the "id" articleHash.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
