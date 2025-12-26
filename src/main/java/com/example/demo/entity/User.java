package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String email;
    
    private String name;
    private String password;
    private String role;
    
    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double balance = 0.0;
    
    public User() {}
    
    public User(String email, String name, String password, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.balance = 0.0;
    }
    
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    
    public static class UserBuilder {
        private Long id;
        private String email;
        private String name;
        private String password;
        private String role;
        private Double balance = 0.0;
        
        public UserBuilder id(Long id) { this.id = id; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder name(String name) { this.name = name; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder role(String role) { this.role = role; return this; }
        public UserBuilder balance(Double balance) { this.balance = balance; return this; }
        
        public User build() {
            User user = new User();
            user.id = this.id;
            user.email = this.email;
            user.name = this.name;
            user.password = this.password;
            user.role = this.role;
            user.balance = this.balance;
            return user;
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}