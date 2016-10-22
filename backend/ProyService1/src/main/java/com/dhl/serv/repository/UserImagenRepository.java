package com.dhl.serv.repository;

import com.dhl.serv.domain.UserImagen;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserImagen entity.
 */
@SuppressWarnings("unused")
public interface UserImagenRepository extends JpaRepository<UserImagen,Long> {

    @Query("select userImagen from UserImagen userImagen where userImagen.user.login = ?#{principal.username}")
    List<UserImagen> findByUserIsCurrentUser();

}
