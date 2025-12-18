package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
 public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name=unique)
    private String email;
    private String password;
    private String ADMIN;
    private String USER;
    
    public User(Long id,String name,String email,String password,String aDMIN,String uSER){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        ADMIN=aDMIN;
        USER=uSER;

    }
    public User(){

    }
    public Long getId(){
        return id;

    }
    public void setId(Long id){
        this.id=id;
    
    }
    public Long getName(){
        return name;

    }
    public void setName(String name){
        this.name=name;
    
    }
    public Long getEmail(){
        return email;

    }
    public void setEmail(String email){
        this.email=email;
    
    }
    public Long getPassword(){
        return password;

    }
    public void setPassword(String Password){
        this.password=password;
    
    }
    public Long getADMIN(){
        return ADMIN;

    }
    public void setADMIN(String aDMIN){
            ADMIN=aDMIN;
    }


 }