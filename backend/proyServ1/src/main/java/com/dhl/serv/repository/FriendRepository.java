package com.dhl.serv.repository;

import com.dhl.serv.domain.Friend;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Friend entity.
 */
@SuppressWarnings("unused")
public interface FriendRepository extends JpaRepository<Friend,Long> {

    @Query("select friend from Friend friend where friend.user1.login = ?#{principal.username}")
    List<Friend> findByUser1IsCurrentUser();

    @Query("select friend from Friend friend where friend.user2.login = ?#{principal.username}")
    List<Friend> findByUser2IsCurrentUser();

}
