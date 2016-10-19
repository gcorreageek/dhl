package com.hsn.serv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "article_description")
    private String articleDescription;

    @Column(name = "article_date_time")
    private ZonedDateTime articleDateTime;

    @ManyToOne
    private UserImagen userImagen;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<ArticleHash> articleHashes = new HashSet<>();

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<ArticleReaction> articleReactions = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public ZonedDateTime getArticleDateTime() {
        return articleDateTime;
    }

    public void setArticleDateTime(ZonedDateTime articleDateTime) {
        this.articleDateTime = articleDateTime;
    }

    public UserImagen getUserImagen() {
        return userImagen;
    }

    public void setUserImagen(UserImagen userImagen) {
        this.userImagen = userImagen;
    }

    public Set<ArticleHash> getArticleHashes() {
        return articleHashes;
    }

    public void setArticleHashes(Set<ArticleHash> articleHashes) {
        this.articleHashes = articleHashes;
    }

    public Set<ArticleReaction> getArticleReactions() {
        return articleReactions;
    }

    public void setArticleReactions(Set<ArticleReaction> articleReactions) {
        this.articleReactions = articleReactions;
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
        Article article = (Article) o;
        if(article.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", articleName='" + articleName + "'" +
            ", articleDescription='" + articleDescription + "'" +
            ", articleDateTime='" + articleDateTime + "'" +
            '}';
    }
}
