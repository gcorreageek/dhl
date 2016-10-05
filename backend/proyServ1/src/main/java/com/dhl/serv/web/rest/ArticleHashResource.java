package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.ArticleHash;

import com.dhl.serv.repository.ArticleHashRepository;
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
 * REST controller for managing ArticleHash.
 */
@RestController
@RequestMapping("/api")
public class ArticleHashResource {

    private final Logger log = LoggerFactory.getLogger(ArticleHashResource.class);
        
    @Inject
    private ArticleHashRepository articleHashRepository;

    /**
     * POST  /article-hashes : Create a new articleHash.
     *
     * @param articleHash the articleHash to create
     * @return the ResponseEntity with status 201 (Created) and with body the new articleHash, or with status 400 (Bad Request) if the articleHash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/article-hashes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleHash> createArticleHash(@RequestBody ArticleHash articleHash) throws URISyntaxException {
        log.debug("REST request to save ArticleHash : {}", articleHash);
        if (articleHash.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("articleHash", "idexists", "A new articleHash cannot already have an ID")).body(null);
        }
        ArticleHash result = articleHashRepository.save(articleHash);
        return ResponseEntity.created(new URI("/api/article-hashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("articleHash", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /article-hashes : Updates an existing articleHash.
     *
     * @param articleHash the articleHash to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated articleHash,
     * or with status 400 (Bad Request) if the articleHash is not valid,
     * or with status 500 (Internal Server Error) if the articleHash couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/article-hashes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleHash> updateArticleHash(@RequestBody ArticleHash articleHash) throws URISyntaxException {
        log.debug("REST request to update ArticleHash : {}", articleHash);
        if (articleHash.getId() == null) {
            return createArticleHash(articleHash);
        }
        ArticleHash result = articleHashRepository.save(articleHash);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("articleHash", articleHash.getId().toString()))
            .body(result);
    }

    /**
     * GET  /article-hashes : get all the articleHashes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of articleHashes in body
     */
    @RequestMapping(value = "/article-hashes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ArticleHash> getAllArticleHashes() {
        log.debug("REST request to get all ArticleHashes");
        List<ArticleHash> articleHashes = articleHashRepository.findAll();
        return articleHashes;
    }

    /**
     * GET  /article-hashes/:id : get the "id" articleHash.
     *
     * @param id the id of the articleHash to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the articleHash, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/article-hashes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleHash> getArticleHash(@PathVariable Long id) {
        log.debug("REST request to get ArticleHash : {}", id);
        ArticleHash articleHash = articleHashRepository.findOne(id);
        return Optional.ofNullable(articleHash)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /article-hashes/:id : delete the "id" articleHash.
     *
     * @param id the id of the articleHash to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/article-hashes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteArticleHash(@PathVariable Long id) {
        log.debug("REST request to delete ArticleHash : {}", id);
        articleHashRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("articleHash", id.toString())).build();
    }

}
