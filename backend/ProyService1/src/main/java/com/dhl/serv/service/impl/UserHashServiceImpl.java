package com.dhl.serv.service.impl;

import com.dhl.serv.domain.User;
import com.dhl.serv.repository.UserRepository;
import com.dhl.serv.security.SecurityUtils;
import com.dhl.serv.service.UserHashService;
import com.dhl.serv.domain.UserHash;
import com.dhl.serv.repository.UserHashRepository;
import com.dhl.serv.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
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
    @Inject
    private UserService userService;
    @Inject
    private UserRepository userRepository;

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
    public UserHash save(List<UserHash> userHash) {
//        log.debug("Request to save UserHash : {}", userHash);
        User user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).get();
        List<UserHash> userHashes = userHashRepository.findByUserIsCurrentUser();
        userHashRepository.delete(userHashes);
        List<UserHash> toInsert = new ArrayList<>();
        UserHash result = null;
        for (UserHash hash : userHash) {
            hash.setId(null);
            hash.setUser(user);
            toInsert.add(hash);
        }
        userHashRepository.save(toInsert);
//        List<UserHash> toInsert2 = new ArrayList<>();
//        for (UserHash hash : toInsert) {
//            if(isRegistered(userHashes,hash)){
//
//            }
//        }






//        userRepository.save(user);

//        UserHash result = userHashRepository.sa;
        return result;
    }

    private boolean isRegistered(List<UserHash> agora,UserHash to){
        for (UserHash userHash : agora) {
            if(to.getId()==userHash.getId()){
                return true;
            }
        }
        return false;
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
    @Transactional(readOnly = true)
    public List<UserHash> findByUserIsCurrentUser() {
        log.debug("Request to get all UserHashes");
        List<UserHash> result = userHashRepository.findByUserIsCurrentUser();

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
