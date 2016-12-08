package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.User;
import com.dhl.serv.domain.UserImagen;
import com.dhl.serv.service.UserImagenService;
import com.dhl.serv.service.UserService;
import com.dhl.serv.web.rest.util.HeaderUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserImagen.
 */
@RestController
@RequestMapping("/api")
public class UserImagenResource {

    private final Logger log = LoggerFactory.getLogger(UserImagenResource.class);

    @Inject
    private UserImagenService userImagenService;
    @Inject
    UserService userService;

    /**
     * POST  /user-imagens : Create a new userImagen.
     *
     * @param userImagen the userImagen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userImagen, or with status 400 (Bad Request) if the userImagen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-imagens",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserImagen> createUserImagen(@RequestBody UserImagen userImagen) throws URISyntaxException {
        log.debug("REST request to save UserImagen : {}", userImagen);
        if (userImagen.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userImagen", "idexists", "A new userImagen cannot already have an ID")).body(null);
        }
        UserImagen result = null;
        try {

            result = userImagenService.save(userImagen);
        } catch (IOException e) {
            log.error("error IOException", e);
        }
        return ResponseEntity.created(new URI("/api/user-imagens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userImagen", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-imagens : Updates an existing userImagen.
     *
     * @param userImagen the userImagen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userImagen,
     * or with status 400 (Bad Request) if the userImagen is not valid,
     * or with status 500 (Internal Server Error) if the userImagen couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user-imagens",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserImagen> updateUserImagen(@RequestBody UserImagen userImagen) throws URISyntaxException {
        log.debug("REST request to update UserImagen : {}", userImagen);
        if (userImagen.getId() == null) {
            return createUserImagen(userImagen);
        }
        UserImagen result = null;
        try {
            result = userImagenService.save(userImagen);
        } catch (IOException e) {
            log.error("error IOException",e);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userImagen", userImagen.getId().toString()))
            .body(result);
    }



    /**
     * GET  /user-imagens : get all the userImagens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userImagens in body
     */
    @RequestMapping(value = "/user-imagens",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserImagen> getAllUserImagens() {
        log.debug("REST request to get all UserImagens");
        return userImagenService.findAll();
    }
    @RequestMapping(value = "/user-imagens-login",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserImagen> findByUserIsCurrentUser() {
        log.debug("REST findByUserIsCurrentUser");
        return userImagenService.findByUserIsCurrentUser();
    }

    /**
     * GET  /user-imagens/:id : get the "id" userImagen.
     *
     * @param id the id of the userImagen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userImagen, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/user-imagens/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserImagen> getUserImagen(@PathVariable Long id) {
        log.debug("REST request to get UserImagen : {}", id);
        UserImagen userImagen = userImagenService.findOne(id);
        return Optional.ofNullable(userImagen)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    /**
     * DELETE  /user-imagens/:id : delete the "id" userImagen.
     *
     * @param id the id of the userImagen to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/user-imagens/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUserImagen(@PathVariable Long id) {
        log.debug("REST request to delete UserImagen : {}", id);
        userImagenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userImagen", id.toString())).build();
    }

    @RequestMapping(value = "/user-imagens/upload", method = RequestMethod.POST)
    public ResponseEntity<UserImagen> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("login") String login) {
        log.info("" + login);
        log.info("" + file.getOriginalFilename());
        UserImagen saveImagen = new UserImagen();
        try {
            User user = userService.getUserWithAuthoritiesByLogin(login).get();
            saveImagen.setMultipartFile(file);
            saveImagen.setUserImagenPathImage("url_web2");
            saveImagen.setUserImagenPathImage("url_web2");
            saveImagen.setUserImagenName(file.getOriginalFilename());
            saveImagen.setUser(user);
            saveImagen = userImagenService.save(saveImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String id = saveImagen.getId().toString();
        log.info("saveImagen.getId().toString():"+id);
        return Optional.ofNullable(saveImagen)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
