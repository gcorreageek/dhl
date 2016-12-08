package com.dhl.serv.service.impl;

import com.dhl.serv.domain.*;
import com.dhl.serv.security.SecurityUtils;
import com.dhl.serv.service.*;
import com.dhl.serv.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service Implementation for managing Article.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Inject
    private ArticleRepository articleRepository;
    @Inject
    private UserImagenService userImagenService;
    @Inject
    private UserService userService;
    @Inject
    private ArticleHashService articleHashService;
    @Inject
    private ArticleReactionService articleReactionService;
    @Inject
    private UserHashService userHashService;


    /**
     * Save a article.
     *
     * @param article the entity to save
     * @return the persisted entity
     */
    public Article save(Article article) throws IOException {
        log.debug("Request to save Article : {}", article);

        User user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).get();

        UserImagen userImagen = article.getUserImagen();
        userImagen.setUser(user);
        userImagen.setUserImagenPath("no_pri");
        userImagen =userImagenService.save(userImagen);


        article.setUser(user);
        article.setUserImagen(userImagen);
//        ZonedDateTime zonedDateTime = new ZonedDateTime();

//        article.setArticleDateTime(ZonedDateTime.from(ZonedDateTime.now().toInstant()));


        Set<ArticleHash> articleHash  =article.getArticleHashes();
//        for (ArticleHash articleHash : article.getArticleHashes()) {
//            articleHash.setArticle(article);
//        }

        for (ArticleHash hash : articleHash) {
            log.info("hash.getHash():",hash.getHash());
            hash.setArticle(article);
            hash.setHash(hash.getHash());
//            articleHashService.save(hash);
        }

        Article result = articleRepository.save(article);








        return result;
    }

    /**
     *  Get all the articles.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Article> findAll() {
        log.debug("Request to get all Articles");
        List<Article> result = articleRepository.findAll();

        return result;
    }
    @Transactional(readOnly = true)
    public List<Article> findByUserIsCurrentUser() {
        log.debug("Request to get all Articles");
        List<Article> result = articleRepository.findByUserIsCurrentUser();
        for (Article article : result) {
            List<ArticleReaction> articleReactions =  articleReactionService.findByArticleId(article.getId());
            article.setArticleReactions(articleReactions);
            for (ArticleHash articleHash : article.getArticleHashes()) {
                articleHash.setHash(articleHashService.findOne(articleHash.getId()).getHash());
            }
        }
        return result;
    }
    @Transactional(readOnly = true)
    public List<Article> findByPreferencesHash(){


//        User user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).get();

        List<UserHash> userHashes = userHashService.findByUserIsCurrentUser();
        List<Long> ids = new ArrayList<Long>();
        for (UserHash userHash : userHashes) {
            ids.add(userHash.getHash().getId());
        }
        List<Article> returns =  articleRepository.findByHashIds(ids);
        //TODO: FALTA PASAR LA IMAGEN DEL USUARIO QUE HIZO LA PLUCACION
        for (Article aReturn : returns) {
            List<ArticleReaction> articleReactions =  articleReactionService.findByArticleId(aReturn.getId());
            aReturn.setArticleReactions(articleReactions);
            for (ArticleHash articleHash : aReturn.getArticleHashes()) {
                articleHash.setHash(articleHashService.findOne(articleHash.getId()).getHash());
            }

            UserImagen userImagen = userImagenService.findByUserIdAndUserImagenMain(aReturn.getUser().getId(),true);
            if(userImagen!=null){
                aReturn.getUser().setUserImagen(userImagen);
            }
        }

        return returns;

    }

    /**
     *  Get one article by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Article findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        Article article = articleRepository.findOne(id);
        return article;
    }

    /**
     *  Delete the  article by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.delete(id);
    }
}
