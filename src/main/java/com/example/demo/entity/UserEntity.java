package com.example.demo.entity;


 public class UserEntity{
    @Id
    private Long id;
    private String name;
    @Column(name=unique)
    private String email;
    private String password;
    
    public UserEntity(Long id,String name,String email,String password){
        
    }

 }