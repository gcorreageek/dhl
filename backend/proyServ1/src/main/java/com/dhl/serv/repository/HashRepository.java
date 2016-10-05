package com.dhl.serv.repository;

import com.dhl.serv.domain.Hash;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Hash entity.
 */
@SuppressWarnings("unused")
public interface HashRepository extends JpaRepository<Hash,Long> {

    @Query("select hash from Hash hash where hash.user.login = ?#{principal.username}")
    List<Hash> findByUserIsCurrentUser();

}
