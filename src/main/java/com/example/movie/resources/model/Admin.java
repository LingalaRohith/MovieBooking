package com.example.movie.resources.model;

import com.example.movie.resources.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userID;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

    public Admin() {
    }

    public Admin(int userID, String email, String password, UserRole userRole) {
        super();
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }


}
