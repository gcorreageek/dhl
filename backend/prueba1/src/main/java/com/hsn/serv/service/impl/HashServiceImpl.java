package com.hsn.serv.service.impl;

import com.hsn.serv.service.HashService;
import com.hsn.serv.domain.Hash;
import com.hsn.serv.repository.HashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Hash.
 */
@Service
@Transactional
public class HashServiceImpl implements HashService{

    private final Logger log = LoggerFactory.getLogger(HashServiceImpl.class);
    
    @Inject
    private HashRepository hashRepository;

    /**
     * Save a hash.
     *
     * @param hash the entity to save
     * @return the persisted entity
     */
    public Hash save(Hash hash) {
        log.debug("Request to save Hash : {}", hash);
        Hash result = hashRepository.save(hash);
        return result;
    }

    /**
     *  Get all the hashes.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Hash> findAll() {
        log.debug("Request to get all Hashes");
        List<Hash> result = hashRepository.findAll();

        return result;
    }

    /**
     *  Get one hash by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Hash findOne(Long id) {
        log.debug("Request to get Hash : {}", id);
        Hash hash = hashRepository.findOne(id);
        return hash;
    }

    /**
     *  Delete the  hash by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Hash : {}", id);
        hashRepository.delete(id);
    }
}
