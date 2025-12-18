package com.example.demo.entity;

@Entity
 public class UserEntity{
    @Id
    private Long id;
    private String name;
    @Column(name=unique)
    private String email;
    private String password;
    private String role;
    
    public UserEntity(Long id,String name,String email,String password,String role){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;

    }
    public UserEntity(){

    }
    public Long getId(){
        return id;

    }
    public void setId(Long id){
        this.id
    }

 }