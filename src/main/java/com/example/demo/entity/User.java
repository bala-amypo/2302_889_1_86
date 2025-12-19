package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Max(100)
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Min(6)
    private String password;

    @NotBlank
    private String ADMIN;
    private String USER;


    public User() {}

     
    public User(Long id,String name,String email,String password,String aDMIN,String uSER){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        ADMIN=aDMIN;
        USER=uSER;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
    public String getADMIN() {
        return ADMIN;
    }

    public void setADMIN(String aDMIN) {
        this.ADMIN = aDMIN;
    }
         
    public String getUSER() {
        return USER;
    }

    public void setUSER(String uSER) {
        this.USER = uSER;
    }
}
