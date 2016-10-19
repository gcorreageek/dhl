package com.hsn.serv.service;

import com.hsn.serv.domain.UserHash;

import java.util.List;

/**
 * Service Interface for managing UserHash.
 */
public interface UserHashService {

    /**
     * Save a userHash.
     *
     * @param userHash the entity to save
     * @return the persisted entity
     */
    UserHash save(UserHash userHash);

    /**
     *  Get all the userHashes.
     *  
     *  @return the list of entities
     */
    List<UserHash> findAll();

    /**
     *  Get the "id" userHash.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserHash findOne(Long id);

    /**
     *  Delete the "id" userHash.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
