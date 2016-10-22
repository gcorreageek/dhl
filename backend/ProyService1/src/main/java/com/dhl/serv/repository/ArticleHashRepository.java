package com.dhl.serv.repository;

import com.dhl.serv.domain.ArticleHash;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArticleHash entity.
 */
@SuppressWarnings("unused")
public interface ArticleHashRepository extends JpaRepository<ArticleHash,Long> {

}
