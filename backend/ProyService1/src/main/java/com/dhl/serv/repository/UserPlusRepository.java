package com.dhl.serv.repository;

import com.dhl.serv.domain.UserPlus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserPlus entity.
 */
@SuppressWarnings("unused")
public interface UserPlusRepository extends JpaRepository<UserPlus,Long> {

    @Query("select userPlus from UserPlus userPlus where userPlus.user.login = ?#{principal.username}")
    List<UserPlus> findByUserIsCurrentUser();

}
