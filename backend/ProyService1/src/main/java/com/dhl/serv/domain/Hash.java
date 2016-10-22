package com.dhl.serv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Hash.
 */
@Entity
@Table(name = "hash")
public class Hash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hash_name")
    private String hashName;

    @Column(name = "hash_type")
    private String hashType;

    @OneToMany(mappedBy = "hash")
    @JsonIgnore
    private Set<UserHash> userHashes = new HashSet<>();

    @OneToMany(mappedBy = "hash")
    @JsonIgnore
    private Set<ArticleHash> articleHashes = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getHashType() {
        return hashType;
    }

    public void setHashType(String hashType) {
        this.hashType = hashType;
    }

    public Set<UserHash> getUserHashes() {
        return userHashes;
    }

    public void setUserHashes(Set<UserHash> userHashes) {
        this.userHashes = userHashes;
    }

    public Set<ArticleHash> getArticleHashes() {
        return articleHashes;
    }

    public void setArticleHashes(Set<ArticleHash> articleHashes) {
        this.articleHashes = articleHashes;
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
        Hash hash = (Hash) o;
        if(hash.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, hash.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Hash{" +
            "id=" + id +
            ", hashName='" + hashName + "'" +
            ", hashType='" + hashType + "'" +
            '}';
    }
}
