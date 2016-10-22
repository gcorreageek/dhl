package com.dhl.serv.service;

import com.dhl.serv.domain.Hash;

import java.util.List;

/**
 * Service Interface for managing Hash.
 */
public interface HashService {

    /**
     * Save a hash.
     *
     * @param hash the entity to save
     * @return the persisted entity
     */
    Hash save(Hash hash);

    /**
     *  Get all the hashes.
     *  
     *  @return the list of entities
     */
    List<Hash> findAll();

    /**
     *  Get the "id" hash.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Hash findOne(Long id);

    /**
     *  Delete the "id" hash.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
