package com.kepes.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="app_user")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private String id_user;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="picture_url")
    private String picture_url;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
