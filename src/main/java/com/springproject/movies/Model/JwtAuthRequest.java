package com.springproject.movies.Model;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
