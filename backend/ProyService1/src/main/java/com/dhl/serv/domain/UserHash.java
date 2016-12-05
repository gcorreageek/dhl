package com.dhl.serv.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UserHash.
 */
@Entity
@Table(name = "user_hash")
public class UserHash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"user"})
    private Hash hash;

    @ManyToOne
    private User user;

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
        UserHash userHash = (UserHash) o;
        if(userHash.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userHash.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserHash{" +
            "id=" + id +
            '}';
    }
}
