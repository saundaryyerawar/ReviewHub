package com.springproject.movies.Model;

import lombok.Data;

@Data
public class RegisterUserSchema {
    private String username;
    private String name;
    private String email;
    private String password;

    public RegisterUserSchema(String username, String name, String email, String password){
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    } 
}
