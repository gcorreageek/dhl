package com.hsn.serv.repository;

import com.hsn.serv.domain.ArticleHash;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArticleHash entity.
 */
@SuppressWarnings("unused")
public interface ArticleHashRepository extends JpaRepository<ArticleHash,Long> {

}
