package com.dhl.serv.service;

import com.dhl.serv.domain.UserImagen;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    UserImagen save(UserImagen userImagen) throws IOException;

    /**
     *  Get all the userImagens.
     *
     *  @return the list of entities
     */
    List<UserImagen> findAll();
    List<UserImagen> findByUserIsCurrentUser();
    UserImagen findByUserIdAndUserImagenMain(Long idUser,Boolean main);

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
