package com.hsn.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hsn.serv.domain.Friend;
import com.hsn.serv.service.FriendService;
import com.hsn.serv.web.rest.util.HeaderUtil;
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
 * REST controller for managing Friend.
 */
@RestController
@RequestMapping("/api")
public class FriendResource {

    private final Logger log = LoggerFactory.getLogger(FriendResource.class);
        
    @Inject
    private FriendService friendService;

    /**
     * POST  /friends : Create a new friend.
     *
     * @param friend the friend to create
     * @return the ResponseEntity with status 201 (Created) and with body the new friend, or with status 400 (Bad Request) if the friend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/friends",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend) throws URISyntaxException {
        log.debug("REST request to save Friend : {}", friend);
        if (friend.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("friend", "idexists", "A new friend cannot already have an ID")).body(null);
        }
        Friend result = friendService.save(friend);
        return ResponseEntity.created(new URI("/api/friends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("friend", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /friends : Updates an existing friend.
     *
     * @param friend the friend to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated friend,
     * or with status 400 (Bad Request) if the friend is not valid,
     * or with status 500 (Internal Server Error) if the friend couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/friends",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Friend> updateFriend(@RequestBody Friend friend) throws URISyntaxException {
        log.debug("REST request to update Friend : {}", friend);
        if (friend.getId() == null) {
            return createFriend(friend);
        }
        Friend result = friendService.save(friend);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("friend", friend.getId().toString()))
            .body(result);
    }

    /**
     * GET  /friends : get all the friends.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of friends in body
     */
    @RequestMapping(value = "/friends",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Friend> getAllFriends() {
        log.debug("REST request to get all Friends");
        return friendService.findAll();
    }

    /**
     * GET  /friends/:id : get the "id" friend.
     *
     * @param id the id of the friend to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the friend, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/friends/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Friend> getFriend(@PathVariable Long id) {
        log.debug("REST request to get Friend : {}", id);
        Friend friend = friendService.findOne(id);
        return Optional.ofNullable(friend)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /friends/:id : delete the "id" friend.
     *
     * @param id the id of the friend to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/friends/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        log.debug("REST request to delete Friend : {}", id);
        friendService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("friend", id.toString())).build();
    }

}
