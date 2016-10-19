package com.hsn.serv.service;

import com.hsn.serv.domain.UserImagen;

import java.util.List;

/**
 * Service Interface for managing UserImagen.
 */
public interface UserImagenService {

    /**
     * Save a userImagen.
     *
     * @param userImagen the entity to save
     * @return the persisted entity
     */
    UserImagen save(UserImagen userImagen);

    /**
     *  Get all the userImagens.
     *  
     *  @return the list of entities
     */
    List<UserImagen> findAll();

    /**
     *  Get the "id" userImagen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserImagen findOne(Long id);

    /**
     *  Delete the "id" userImagen.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
