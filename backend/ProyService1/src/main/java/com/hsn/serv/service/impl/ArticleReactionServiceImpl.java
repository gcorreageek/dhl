package com.hsn.serv.service.impl;

import com.hsn.serv.service.ArticleReactionService;
import com.hsn.serv.domain.ArticleReaction;
import com.hsn.serv.repository.ArticleReactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ArticleReaction.
 */
@Service
@Transactional
public class ArticleReactionServiceImpl implements ArticleReactionService{

    private final Logger log = LoggerFactory.getLogger(ArticleReactionServiceImpl.class);
    
    @Inject
    private ArticleReactionRepository articleReactionRepository;

    /**
     * Save a articleReaction.
     *
     * @param articleReaction the entity to save
     * @return the persisted entity
     */
    public ArticleReaction save(ArticleReaction articleReaction) {
        log.debug("Request to save ArticleReaction : {}", articleReaction);
        ArticleReaction result = articleReactionRepository.save(articleReaction);
        return result;
    }

    /**
     *  Get all the articleReactions.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ArticleReaction> findAll() {
        log.debug("Request to get all ArticleReactions");
        List<ArticleReaction> result = articleReactionRepository.findAll();

        return result;
    }

    /**
     *  Get one articleReaction by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ArticleReaction findOne(Long id) {
        log.debug("Request to get ArticleReaction : {}", id);
        ArticleReaction articleReaction = articleReactionRepository.findOne(id);
        return articleReaction;
    }

    /**
     *  Delete the  articleReaction by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ArticleReaction : {}", id);
        articleReactionRepository.delete(id);
    }
}
