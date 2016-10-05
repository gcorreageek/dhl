package com.dhl.serv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UserPlus.
 */
@Entity
@Table(name = "user_plus")
public class UserPlus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "info_1")
    private String info1;

    @Column(name = "info_2")
    private String info2;

    @Column(name = "info_3")
    private String info3;

    @Column(name = "info_4")
    private String info4;

    @Column(name = "info_5")
    private String info5;

    @Column(name = "info_6")
    private String info6;

    @Column(name = "info_7")
    private String info7;

    @Column(name = "info_8")
    private String info8;

    @Column(name = "info_9")
    private String info9;

    @Column(name = "info_10")
    private String info10;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public String getInfo6() {
        return info6;
    }

    public void setInfo6(String info6) {
        this.info6 = info6;
    }

    public String getInfo7() {
        return info7;
    }

    public void setInfo7(String info7) {
        this.info7 = info7;
    }

    public String getInfo8() {
        return info8;
    }

    public void setInfo8(String info8) {
        this.info8 = info8;
    }

    public String getInfo9() {
        return info9;
    }

    public void setInfo9(String info9) {
        this.info9 = info9;
    }

    public String getInfo10() {
        return info10;
    }

    public void setInfo10(String info10) {
        this.info10 = info10;
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
        UserPlus userPlus = (UserPlus) o;
        if(userPlus.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userPlus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserPlus{" +
            "id=" + id +
            ", info1='" + info1 + "'" +
            ", info2='" + info2 + "'" +
            ", info3='" + info3 + "'" +
            ", info4='" + info4 + "'" +
            ", info5='" + info5 + "'" +
            ", info6='" + info6 + "'" +
            ", info7='" + info7 + "'" +
            ", info8='" + info8 + "'" +
            ", info9='" + info9 + "'" +
            ", info10='" + info10 + "'" +
            '}';
    }
}
