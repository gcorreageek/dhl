package com.dhl.serv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ArticleReaction.
 */
@Entity
@Table(name = "article_reaction")
public class ArticleReaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "article_reaction_type")
    private String articleReactionType;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleReactionType() {
        return articleReactionType;
    }

    public void setArticleReactionType(String articleReactionType) {
        this.articleReactionType = articleReactionType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
        ArticleReaction articleReaction = (ArticleReaction) o;
        if(articleReaction.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, articleReaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArticleReaction{" +
            "id=" + id +
            ", articleReactionType='" + articleReactionType + "'" +
            '}';
    }
}
