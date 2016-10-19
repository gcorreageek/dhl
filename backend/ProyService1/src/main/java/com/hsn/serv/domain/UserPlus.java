package com.hsn.serv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
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

    @Column(name = "weigh")
    private String weigh;

    @Column(name = "height")
    private String height;

    @Column(name = "birthday")
    private ZonedDateTime birthday;

    @Column(name = "sex")
    private String sex;

    @Column(name = "country")
    private String country;

    @Column(name = "languaje")
    private String languaje;

    @Column(name = "disabled_perfil")
    private Boolean disabledPerfil;

    @Column(name = "show_weigh")
    private Boolean showWeigh;

    @Column(name = "show_height")
    private Boolean showHeight;

    @Column(name = "show_birthday")
    private Boolean showBirthday;

    @Column(name = "show_sex")
    private Boolean showSex;

    @Column(name = "show_country")
    private Boolean showCountry;

    @Column(name = "show_languaje")
    private Boolean showLanguaje;

    @Column(name = "notification_news")
    private Boolean notificationNews;

    @Column(name = "options")
    private String options;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeigh() {
        return weigh;
    }

    public void setWeigh(String weigh) {
        this.weigh = weigh;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
    }

    public Boolean isDisabledPerfil() {
        return disabledPerfil;
    }

    public void setDisabledPerfil(Boolean disabledPerfil) {
        this.disabledPerfil = disabledPerfil;
    }

    public Boolean isShowWeigh() {
        return showWeigh;
    }

    public void setShowWeigh(Boolean showWeigh) {
        this.showWeigh = showWeigh;
    }

    public Boolean isShowHeight() {
        return showHeight;
    }

    public void setShowHeight(Boolean showHeight) {
        this.showHeight = showHeight;
    }

    public Boolean isShowBirthday() {
        return showBirthday;
    }

    public void setShowBirthday(Boolean showBirthday) {
        this.showBirthday = showBirthday;
    }

    public Boolean isShowSex() {
        return showSex;
    }

    public void setShowSex(Boolean showSex) {
        this.showSex = showSex;
    }

    public Boolean isShowCountry() {
        return showCountry;
    }

    public void setShowCountry(Boolean showCountry) {
        this.showCountry = showCountry;
    }

    public Boolean isShowLanguaje() {
        return showLanguaje;
    }

    public void setShowLanguaje(Boolean showLanguaje) {
        this.showLanguaje = showLanguaje;
    }

    public Boolean isNotificationNews() {
        return notificationNews;
    }

    public void setNotificationNews(Boolean notificationNews) {
        this.notificationNews = notificationNews;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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
            ", weigh='" + weigh + "'" +
            ", height='" + height + "'" +
            ", birthday='" + birthday + "'" +
            ", sex='" + sex + "'" +
            ", country='" + country + "'" +
            ", languaje='" + languaje + "'" +
            ", disabledPerfil='" + disabledPerfil + "'" +
            ", showWeigh='" + showWeigh + "'" +
            ", showHeight='" + showHeight + "'" +
            ", showBirthday='" + showBirthday + "'" +
            ", showSex='" + showSex + "'" +
            ", showCountry='" + showCountry + "'" +
            ", showLanguaje='" + showLanguaje + "'" +
            ", notificationNews='" + notificationNews + "'" +
            ", options='" + options + "'" +
            '}';
    }
}
