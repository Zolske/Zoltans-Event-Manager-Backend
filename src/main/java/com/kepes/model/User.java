package com.kepes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
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

}
