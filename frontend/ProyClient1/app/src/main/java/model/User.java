package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gustavo on 26/11/16.
 */

public class User extends AbstractAuditingEntity  implements Serializable{
    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String activated;
    private String langKey;
    private String weigh;
    private String height;
    private Date birthday;
    private String sex;
    private String country;
    private String disabledProfile;
    private String showWeigh;
    private String showHeight;
    private String showBirthday;
    private String showSex;
    private String showCountry;
    private String showLanguaje;
    private String notificationNews;
    private String options;
    private String image;
    private String hashLike;
    private String hashDislike;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public String getDisabledProfile() {
        return disabledProfile;
    }

    public void setDisabledProfile(String disabledProfile) {
        this.disabledProfile = disabledProfile;
    }

    public String getShowWeigh() {
        return showWeigh;
    }

    public void setShowWeigh(String showWeigh) {
        this.showWeigh = showWeigh;
    }

    public String getShowHeight() {
        return showHeight;
    }

    public void setShowHeight(String showHeight) {
        this.showHeight = showHeight;
    }

    public String getShowBirthday() {
        return showBirthday;
    }

    public void setShowBirthday(String showBirthday) {
        this.showBirthday = showBirthday;
    }

    public String getShowSex() {
        return showSex;
    }

    public void setShowSex(String showSex) {
        this.showSex = showSex;
    }

    public String getShowCountry() {
        return showCountry;
    }

    public void setShowCountry(String showCountry) {
        this.showCountry = showCountry;
    }

    public String getShowLanguaje() {
        return showLanguaje;
    }

    public void setShowLanguaje(String showLanguaje) {
        this.showLanguaje = showLanguaje;
    }

    public String getNotificationNews() {
        return notificationNews;
    }

    public void setNotificationNews(String notificationNews) {
        this.notificationNews = notificationNews;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHashLike() {
        return hashLike;
    }

    public void setHashLike(String hashLike) {
        this.hashLike = hashLike;
    }

    public String getHashDislike() {
        return hashDislike;
    }

    public void setHashDislike(String hashDislike) {
        this.hashDislike = hashDislike;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", activated='" + activated + '\'' +
                ", langKey='" + langKey + '\'' +
                ", weigh='" + weigh + '\'' +
                ", height='" + height + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", disabledProfile='" + disabledProfile + '\'' +
                ", showWeigh='" + showWeigh + '\'' +
                ", showHeight='" + showHeight + '\'' +
                ", showBirthday='" + showBirthday + '\'' +
                ", showSex='" + showSex + '\'' +
                ", showCountry='" + showCountry + '\'' +
                ", showLanguaje='" + showLanguaje + '\'' +
                ", notificationNews='" + notificationNews + '\'' +
                ", options='" + options + '\'' +
                ", image='" + image + '\'' +
                ", hashLike='" + hashLike + '\'' +
                ", hashDislike='" + hashDislike + '\'' +
                '}';
    }
}
