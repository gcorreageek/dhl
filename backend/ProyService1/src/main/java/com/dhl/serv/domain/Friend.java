package com.dhl.serv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Friend.
 */
@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "friend_type")
    private String friendType;

    @ManyToOne
    @JoinColumn(name="user_1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="user_2_id")
    private User user2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFriendType() {
        return friendType;
    }

    public void setFriendType(String friendType) {
        this.friendType = friendType;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user) {
        this.user1 = user;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user) {
        this.user2 = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Friend friend = (Friend) o;
        if(friend.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, friend.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Friend{" +
            "id=" + id +
            ", friendType='" + friendType + "'" +
            '}';
    }
}
