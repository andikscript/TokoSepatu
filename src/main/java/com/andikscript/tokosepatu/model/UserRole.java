package com.andikscript.tokosepatu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private UserRoleId id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("rolesId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roles_id", nullable = false)
    private Role roles;

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

}