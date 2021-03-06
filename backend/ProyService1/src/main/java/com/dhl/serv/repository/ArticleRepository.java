package com.dhl.serv.repository;

import com.dhl.serv.domain.Article;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Article entity.
 */
@SuppressWarnings("unused")
public interface ArticleRepository extends JpaRepository<Article,Long> {

    @Query("select article from Article article where article.user.login = ?#{principal.username}")
    List<Article> findByUserIsCurrentUser();



    @Query( "select distinct o from Article o,ArticleHash arthash where o=arthash.article and arthash.hash.id in :idsHash" )
    List<Article> findByHashIds(@Param("idsHash") List<Long> hashIdList);




}
