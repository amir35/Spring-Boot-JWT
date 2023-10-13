package com.example.demo.model;

//Used in Spring 2.7.0
//import javax.persistence.*;

//Used in Spring 3.0.0
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "jwt_signup_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;

    private String email;

    private Long mobile;

    private String location;
    private String password;


//    public User() {
//    }
//
//    public User(Long id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}