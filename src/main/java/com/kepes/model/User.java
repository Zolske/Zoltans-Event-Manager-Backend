package com.kepes.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="id_user")
    private String id_user;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="picture_url")
    private String picture_url;

}
