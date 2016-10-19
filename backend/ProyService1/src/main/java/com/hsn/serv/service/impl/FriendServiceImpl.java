package com.hsn.serv.service.impl;

import com.hsn.serv.service.FriendService;
import com.hsn.serv.domain.Friend;
import com.hsn.serv.repository.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Friend.
 */
@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);
    
    @Inject
    private FriendRepository friendRepository;

    /**
     * Save a friend.
     *
     * @param friend the entity to save
     * @return the persisted entity
     */
    public Friend save(Friend friend) {
        log.debug("Request to save Friend : {}", friend);
        Friend result = friendRepository.save(friend);
        return result;
    }

    /**
     *  Get all the friends.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Friend> findAll() {
        log.debug("Request to get all Friends");
        List<Friend> result = friendRepository.findAll();

        return result;
    }

    /**
     *  Get one friend by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Friend findOne(Long id) {
        log.debug("Request to get Friend : {}", id);
        Friend friend = friendRepository.findOne(id);
        return friend;
    }

    /**
     *  Delete the  friend by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Friend : {}", id);
        friendRepository.delete(id);
    }
}
