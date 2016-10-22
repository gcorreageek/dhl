package com.dhl.serv.service.impl;

import com.dhl.serv.service.UserPlusService;
import com.dhl.serv.domain.UserPlus;
import com.dhl.serv.repository.UserPlusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserPlus.
 */
@Service
@Transactional
public class UserPlusServiceImpl implements UserPlusService{

    private final Logger log = LoggerFactory.getLogger(UserPlusServiceImpl.class);
    
    @Inject
    private UserPlusRepository userPlusRepository;

    /**
     * Save a userPlus.
     *
     * @param userPlus the entity to save
     * @return the persisted entity
     */
    public UserPlus save(UserPlus userPlus) {
        log.debug("Request to save UserPlus : {}", userPlus);
        UserPlus result = userPlusRepository.save(userPlus);
        return result;
    }

    /**
     *  Get all the userPluses.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UserPlus> findAll() {
        log.debug("Request to get all UserPluses");
        List<UserPlus> result = userPlusRepository.findAll();

        return result;
    }

    /**
     *  Get one userPlus by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserPlus findOne(Long id) {
        log.debug("Request to get UserPlus : {}", id);
        UserPlus userPlus = userPlusRepository.findOne(id);
        return userPlus;
    }

    /**
     *  Delete the  userPlus by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserPlus : {}", id);
        userPlusRepository.delete(id);
    }
}
