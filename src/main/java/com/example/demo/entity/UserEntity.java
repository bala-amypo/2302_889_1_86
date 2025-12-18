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
    public Long getRole(){
        return role;

    }
    public void setRole(String role){
        this.email=role;
    
    }


 }