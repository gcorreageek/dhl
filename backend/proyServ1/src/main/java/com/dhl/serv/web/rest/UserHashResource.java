package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.UserHash;

import com.dhl.serv.repository.UserHashRepository;
import com.dhl.serv.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserHash.
 */
@RestController
@RequestMapping("/api")
public class UserHashResource {

    private final Logger log = LoggerFactory.getLogger(UserHashResource.class);
        
    @Inject
    private UserHashRepository userHashRepository;

    /**
     * POST  /user-hashes : Create a new userHash.
     *
     * @param userHash the userHash to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userHash, or with status 400 (Bad Request) if the userHash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-hashes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserHash> createUserHash(@RequestBody UserHash userHash) throws URISyntaxException {
        log.debug("REST request to save UserHash : {}", userHash);
        if (userHash.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userHash", "idexists", "A new userHash cannot already have an ID")).body(null);
        }
        UserHash result = userHashRepository.save(userHash);
        return ResponseEntity.created(new URI("/api/user-hashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userHash", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-hashes : Updates an existing userHash.
     *
     * @param userHash the userHash to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userHash,
     * or with status 400 (Bad Request) if the userHash is not valid,
     * or with status 500 (Internal Server Error) if the userHash couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-hashes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserHash> updateUserHash(@RequestBody UserHash userHash) throws URISyntaxException {
        log.debug("REST request to update UserHash : {}", userHash);
        if (userHash.getId() == null) {
            return createUserHash(userHash);
        }
        UserHash result = userHashRepository.save(userHash);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userHash", userHash.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-hashes : get all the userHashes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userHashes in body
     */
    @RequestMapping(value = "/user-hashes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserHash> getAllUserHashes() {
        log.debug("REST request to get all UserHashes");
        List<UserHash> userHashes = userHashRepository.findAll();
        return userHashes;
    }

    /**
     * GET  /user-hashes/:id : get the "id" userHash.
     *
     * @param id the id of the userHash to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userHash, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/user-hashes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserHash> getUserHash(@PathVariable Long id) {
        log.debug("REST request to get UserHash : {}", id);
        UserHash userHash = userHashRepository.findOne(id);
        return Optional.ofNullable(userHash)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /user-hashes/:id : delete the "id" userHash.
     *
     * @param id the id of the userHash to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/user-hashes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUserHash(@PathVariable Long id) {
        log.debug("REST request to delete UserHash : {}", id);
        userHashRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userHash", id.toString())).build();
    }

}
