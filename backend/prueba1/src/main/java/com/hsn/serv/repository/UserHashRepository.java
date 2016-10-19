package com.hsn.serv.repository;

import com.hsn.serv.domain.UserHash;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserHash entity.
 */
@SuppressWarnings("unused")
public interface UserHashRepository extends JpaRepository<UserHash,Long> {

    @Query("select userHash from UserHash userHash where userHash.user.login = ?#{principal.username}")
    List<UserHash> findByUserIsCurrentUser();

}
