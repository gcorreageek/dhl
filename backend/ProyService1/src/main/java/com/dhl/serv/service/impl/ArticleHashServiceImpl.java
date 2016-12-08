package com.dhl.serv.service.impl;

import com.dhl.serv.service.ArticleHashService;
import com.dhl.serv.domain.ArticleHash;
import com.dhl.serv.repository.ArticleHashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ArticleHash.
 */
@Service
@Transactional
public class ArticleHashServiceImpl implements ArticleHashService{

    private final Logger log = LoggerFactory.getLogger(ArticleHashServiceImpl.class);

    @Inject
    private ArticleHashRepository articleHashRepository;

    /**
     * Save a articleHash.
     *
     * @param articleHash the entity to save
     * @return the persisted entity
     */
    public ArticleHash save(ArticleHash articleHash) {
        log.debug("Request to save ArticleHash : {}", articleHash);
        ArticleHash result = articleHashRepository.save(articleHash);
        return result;
    }

    /**
     *  Get all the articleHashes.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ArticleHash> findAll() {
        log.debug("Request to get all ArticleHashes");
        List<ArticleHash> result = articleHashRepository.findAll();

        return result;
    }

    /**
     *  Get one articleHash by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ArticleHash findOne(Long id) {
        log.debug("Request to get ArticleHash : {}", id);
        ArticleHash articleHash = articleHashRepository.findOne(id);
        return articleHash;
    }

    /**
     *  Delete the  articleHash by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ArticleHash : {}", id);
        articleHashRepository.delete(id);
    }
}
