package com.dhl.serv.service;

import com.dhl.serv.domain.Friend;

import java.util.List;

/**
 * Service Interface for managing Friend.
 */
public interface FriendService {

    /**
     * Save a friend.
     *
     * @param friend the entity to save
     * @return the persisted entity
     */
    Friend save(Friend friend);

    /**
     *  Get all the friends.
     *  
     *  @return the list of entities
     */
    List<Friend> findAll();

    /**
     *  Get the "id" friend.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Friend findOne(Long id);

    /**
     *  Delete the "id" friend.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
