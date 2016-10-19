package com.hsn.serv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ArticleHash.
 */
@Entity
@Table(name = "article_hash")
public class ArticleHash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hash hash;

    @ManyToOne
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleHash articleHash = (ArticleHash) o;
        if(articleHash.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, articleHash.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArticleHash{" +
            "id=" + id +
            '}';
    }
}
