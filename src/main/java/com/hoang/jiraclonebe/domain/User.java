package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * This class is where the attributes are declared in an object
 * for class User. It is used to map the attributes to a database
 * table.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Need to input a Username")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Need to enter your name")
    private String fullName;

    @NotBlank(message = "Need to input a password")
    private String password;

    // ensure that password matches and persist "password"
    @Transient
    private String confirmPassword;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date createdOn;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updatedOn;

    @PrePersist
    protected void createdOn(){
        this.createdOn = new Date();
    }

    @PreUpdate
    protected void updatedOn() {
        this.updatedOn = new Date();
    }

    // if delete user, delete all epic information
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private List<Epic> epics = new ArrayList<Epic>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<Epic> getEpics() {
        return epics;
    }

    public void setProjects(List<Epic> epics) {
        this.epics = epics;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
