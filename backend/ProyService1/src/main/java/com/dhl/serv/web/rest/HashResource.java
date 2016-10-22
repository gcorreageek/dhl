package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.Hash;
import com.dhl.serv.service.HashService;
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
 * REST controller for managing Hash.
 */
@RestController
@RequestMapping("/api")
public class HashResource {

    private final Logger log = LoggerFactory.getLogger(HashResource.class);
        
    @Inject
    private HashService hashService;

    /**
     * POST  /hashes : Create a new hash.
     *
     * @param hash the hash to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hash, or with status 400 (Bad Request) if the hash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/hashes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hash> createHash(@RequestBody Hash hash) throws URISyntaxException {
        log.debug("REST request to save Hash : {}", hash);
        if (hash.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("hash", "idexists", "A new hash cannot already have an ID")).body(null);
        }
        Hash result = hashService.save(hash);
        return ResponseEntity.created(new URI("/api/hashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("hash", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hashes : Updates an existing hash.
     *
     * @param hash the hash to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hash,
     * or with status 400 (Bad Request) if the hash is not valid,
     * or with status 500 (Internal Server Error) if the hash couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/hashes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hash> updateHash(@RequestBody Hash hash) throws URISyntaxException {
        log.debug("REST request to update Hash : {}", hash);
        if (hash.getId() == null) {
            return createHash(hash);
        }
        Hash result = hashService.save(hash);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("hash", hash.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hashes : get all the hashes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hashes in body
     */
    @RequestMapping(value = "/hashes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Hash> getAllHashes() {
        log.debug("REST request to get all Hashes");
        return hashService.findAll();
    }

    /**
     * GET  /hashes/:id : get the "id" hash.
     *
     * @param id the id of the hash to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hash, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/hashes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hash> getHash(@PathVariable Long id) {
        log.debug("REST request to get Hash : {}", id);
        Hash hash = hashService.findOne(id);
        return Optional.ofNullable(hash)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /hashes/:id : delete the "id" hash.
     *
     * @param id the id of the hash to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/hashes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHash(@PathVariable Long id) {
        log.debug("REST request to delete Hash : {}", id);
        hashService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("hash", id.toString())).build();
    }

}
