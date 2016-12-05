package com.dhl.serv.service.dto;

import com.dhl.serv.config.Constants;

import com.dhl.serv.domain.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

//    @JsonIgnoreProperties({"purchaseRequest"})
//    @OneToMany(mappedBy = "purchaseRequest", cascade = {CascadeType.ALL}, orphanRemoval = true)
//    private List<PurchaseRequestMotive> purchaseRequestMotiveList;


    @OneToMany
    private User user;

    @JsonIgnoreProperties({"user"})
    @OneToMany
    private UserPlus userPlus;

    @JsonIgnoreProperties({"user"})
    @OneToMany
    private List<UserHash> userHash;

    @JsonIgnoreProperties({"user"})
    @OneToMany
    private UserImagen userImagen;


    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()));
    }

    public UserDTO(String login, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }


    public UserDTO(User user, UserPlus userPlus, List<UserHash> userHash, UserImagen userImagen) {
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.getActivated();
        this.langKey = user.getLangKey();
        this.authorities = user.getAuthorities().stream().map(Authority::getName) .collect(Collectors.toSet());

        this.user = user;
        this.userPlus = userPlus;
        this.userHash = userHash;
        this.userImagen = userImagen;


    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            "}";
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPlus getUserPlus() {
        return userPlus;
    }

    public void setUserPlus(UserPlus userPlus) {
        this.userPlus = userPlus;
    }

    public List<UserHash> getUserHash() {
        return userHash;
    }

    public void setUserHash(List<UserHash> userHash) {
        this.userHash = userHash;
    }

    public UserImagen getUserImagen() {
        return userImagen;
    }

    public void setUserImagen(UserImagen userImagen) {
        this.userImagen = userImagen;
    }

}
