package com.springproject.movies.Model;

import lombok.Data;

@Data
public class CreateReviewSchema {
    private String imdbid;
    private String review;

    public CreateReviewSchema(String imdbid, String review){
        this.imdbid = imdbid;
        this.review = review;
    }
}
