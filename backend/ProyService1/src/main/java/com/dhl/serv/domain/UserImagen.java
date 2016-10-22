package com.dhl.serv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserImagen.
 */
@Entity
@Table(name = "user_imagen")
public class UserImagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_imagen_name")
    private String userImagenName;

    @Column(name = "user_imagen_path")
    private String userImagenPath;

    @Column(name = "user_imagen_path_image")
    private String userImagenPathImage;

    @Column(name = "user_imagen_main")
    private Boolean userImagenMain;

    @OneToMany(mappedBy = "userImagen")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImagenName() {
        return userImagenName;
    }

    public void setUserImagenName(String userImagenName) {
        this.userImagenName = userImagenName;
    }

    public String getUserImagenPath() {
        return userImagenPath;
    }

    public void setUserImagenPath(String userImagenPath) {
        this.userImagenPath = userImagenPath;
    }

    public String getUserImagenPathImage() {
        return userImagenPathImage;
    }

    public void setUserImagenPathImage(String userImagenPathImage) {
        this.userImagenPathImage = userImagenPathImage;
    }

    public Boolean isUserImagenMain() {
        return userImagenMain;
    }

    public void setUserImagenMain(Boolean userImagenMain) {
        this.userImagenMain = userImagenMain;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserImagen userImagen = (UserImagen) o;
        if(userImagen.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userImagen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserImagen{" +
            "id=" + id +
            ", userImagenName='" + userImagenName + "'" +
            ", userImagenPath='" + userImagenPath + "'" +
            ", userImagenPathImage='" + userImagenPathImage + "'" +
            ", userImagenMain='" + userImagenMain + "'" +
            '}';
    }
}
