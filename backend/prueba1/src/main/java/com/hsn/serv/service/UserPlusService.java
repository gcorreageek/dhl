package com.hsn.serv.service;

import com.hsn.serv.domain.UserPlus;

import java.util.List;

/**
 * Service Interface for managing UserPlus.
 */
public interface UserPlusService {

    /**
     * Save a userPlus.
     *
     * @param userPlus the entity to save
     * @return the persisted entity
     */
    UserPlus save(UserPlus userPlus);

    /**
     *  Get all the userPluses.
     *  
     *  @return the list of entities
     */
    List<UserPlus> findAll();

    /**
     *  Get the "id" userPlus.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserPlus findOne(Long id);

    /**
     *  Delete the "id" userPlus.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
