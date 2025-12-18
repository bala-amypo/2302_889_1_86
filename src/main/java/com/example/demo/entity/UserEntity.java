package com.example.demo.entity;


 public class UserEntity{
    @Id
    private long id;
    private String name;
    @Column(name=unique)
    private String email;
    private String password;
    p

 }