package com.dhl.serv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dhl.serv.domain.ArticleReaction;

import com.dhl.serv.repository.ArticleReactionRepository;
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
 * REST controller for managing ArticleReaction.
 */
@RestController
@RequestMapping("/api")
public class ArticleReactionResource {

    private final Logger log = LoggerFactory.getLogger(ArticleReactionResource.class);
        
    @Inject
    private ArticleReactionRepository articleReactionRepository;

    /**
     * POST  /article-reactions : Create a new articleReaction.
     *
     * @param articleReaction the articleReaction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new articleReaction, or with status 400 (Bad Request) if the articleReaction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/article-reactions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleReaction> createArticleReaction(@RequestBody ArticleReaction articleReaction) throws URISyntaxException {
        log.debug("REST request to save ArticleReaction : {}", articleReaction);
        if (articleReaction.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("articleReaction", "idexists", "A new articleReaction cannot already have an ID")).body(null);
        }
        ArticleReaction result = articleReactionRepository.save(articleReaction);
        return ResponseEntity.created(new URI("/api/article-reactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("articleReaction", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /article-reactions : Updates an existing articleReaction.
     *
     * @param articleReaction the articleReaction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated articleReaction,
     * or with status 400 (Bad Request) if the articleReaction is not valid,
     * or with status 500 (Internal Server Error) if the articleReaction couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/article-reactions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleReaction> updateArticleReaction(@RequestBody ArticleReaction articleReaction) throws URISyntaxException {
        log.debug("REST request to update ArticleReaction : {}", articleReaction);
        if (articleReaction.getId() == null) {
            return createArticleReaction(articleReaction);
        }
        ArticleReaction result = articleReactionRepository.save(articleReaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("articleReaction", articleReaction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /article-reactions : get all the articleReactions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of articleReactions in body
     */
    @RequestMapping(value = "/article-reactions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ArticleReaction> getAllArticleReactions() {
        log.debug("REST request to get all ArticleReactions");
        List<ArticleReaction> articleReactions = articleReactionRepository.findAll();
        return articleReactions;
    }

    /**
     * GET  /article-reactions/:id : get the "id" articleReaction.
     *
     * @param id the id of the articleReaction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the articleReaction, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/article-reactions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ArticleReaction> getArticleReaction(@PathVariable Long id) {
        log.debug("REST request to get ArticleReaction : {}", id);
        ArticleReaction articleReaction = articleReactionRepository.findOne(id);
        return Optional.ofNullable(articleReaction)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /article-reactions/:id : delete the "id" articleReaction.
     *
     * @param id the id of the articleReaction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/article-reactions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteArticleReaction(@PathVariable Long id) {
        log.debug("REST request to delete ArticleReaction : {}", id);
        articleReactionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("articleReaction", id.toString())).build();
    }

}
