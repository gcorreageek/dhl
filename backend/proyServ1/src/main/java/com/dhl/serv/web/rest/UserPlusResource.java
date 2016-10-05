package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.UserPlus;

import com.dhl.serv.repository.UserPlusRepository;
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
 * REST controller for managing UserPlus.
 */
@RestController
@RequestMapping("/api")
public class UserPlusResource {

    private final Logger log = LoggerFactory.getLogger(UserPlusResource.class);
        
    @Inject
    private UserPlusRepository userPlusRepository;

    /**
     * POST  /user-pluses : Create a new userPlus.
     *
     * @param userPlus the userPlus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userPlus, or with status 400 (Bad Request) if the userPlus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-pluses",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserPlus> createUserPlus(@RequestBody UserPlus userPlus) throws URISyntaxException {
        log.debug("REST request to save UserPlus : {}", userPlus);
        if (userPlus.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userPlus", "idexists", "A new userPlus cannot already have an ID")).body(null);
        }
        UserPlus result = userPlusRepository.save(userPlus);
        return ResponseEntity.created(new URI("/api/user-pluses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userPlus", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-pluses : Updates an existing userPlus.
     *
     * @param userPlus the userPlus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userPlus,
     * or with status 400 (Bad Request) if the userPlus is not valid,
     * or with status 500 (Internal Server Error) if the userPlus couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-pluses",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserPlus> updateUserPlus(@RequestBody UserPlus userPlus) throws URISyntaxException {
        log.debug("REST request to update UserPlus : {}", userPlus);
        if (userPlus.getId() == null) {
            return createUserPlus(userPlus);
        }
        UserPlus result = userPlusRepository.save(userPlus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userPlus", userPlus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-pluses : get all the userPluses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userPluses in body
     */
    @RequestMapping(value = "/user-pluses",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserPlus> getAllUserPluses() {
        log.debug("REST request to get all UserPluses");
        List<UserPlus> userPluses = userPlusRepository.findAll();
        return userPluses;
    }

    /**
     * GET  /user-pluses/:id : get the "id" userPlus.
     *
     * @param id the id of the userPlus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userPlus, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/user-pluses/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserPlus> getUserPlus(@PathVariable Long id) {
        log.debug("REST request to get UserPlus : {}", id);
        UserPlus userPlus = userPlusRepository.findOne(id);
        return Optional.ofNullable(userPlus)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /user-pluses/:id : delete the "id" userPlus.
     *
     * @param id the id of the userPlus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/user-pluses/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUserPlus(@PathVariable Long id) {
        log.debug("REST request to delete UserPlus : {}", id);
        userPlusRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userPlus", id.toString())).build();
    }

}
