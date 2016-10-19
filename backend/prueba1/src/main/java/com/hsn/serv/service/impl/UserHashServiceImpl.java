package com.hsn.serv.service.impl;

import com.hsn.serv.service.UserHashService;
import com.hsn.serv.domain.UserHash;
import com.hsn.serv.repository.UserHashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserHash.
 */
@Service
@Transactional
public class UserHashServiceImpl implements UserHashService{

    private final Logger log = LoggerFactory.getLogger(UserHashServiceImpl.class);
    
    @Inject
    private UserHashRepository userHashRepository;

    /**
     * Save a userHash.
     *
     * @param userHash the entity to save
     * @return the persisted entity
     */
    public UserHash save(UserHash userHash) {
        log.debug("Request to save UserHash : {}", userHash);
        UserHash result = userHashRepository.save(userHash);
        return result;
    }

    /**
     *  Get all the userHashes.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UserHash> findAll() {
        log.debug("Request to get all UserHashes");
        List<UserHash> result = userHashRepository.findAll();

        return result;
    }

    /**
     *  Get one userHash by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserHash findOne(Long id) {
        log.debug("Request to get UserHash : {}", id);
        UserHash userHash = userHashRepository.findOne(id);
        return userHash;
    }

    /**
     *  Delete the  userHash by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserHash : {}", id);
        userHashRepository.delete(id);
    }
}
