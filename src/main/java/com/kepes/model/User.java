package com.kepes.model;

import jakarta.persistence.*;

// lombok getter and setters annotations are not working
@Entity
// can not use "user" as name in postgres database
@Table(name="app_user")
public class User {

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idUser;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="picture_url")
    private String pictureUrl;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String id_user) {
        this.idUser = id_user;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
