package com.springproject.movies.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    @Id
    private ObjectId id;
    private String username;
    private String name;
    private String email;
    private String password;

    public AuthUser(String username, String name, String email, String password){
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
