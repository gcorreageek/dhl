package com.dhl.serv.repository;

import com.dhl.serv.domain.ArticleReaction;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArticleReaction entity.
 */
@SuppressWarnings("unused")
public interface ArticleReactionRepository extends JpaRepository<ArticleReaction,Long> {

    @Query("select articleReaction from ArticleReaction articleReaction where articleReaction.user.login = ?#{principal.username}")
    List<ArticleReaction> findByUserIsCurrentUser();

}
