package com.springproject.movies.Model;

import lombok.Data;

@Data
public class UpdateReviewSchema {
    private String objId;
    private String review;

    public UpdateReviewSchema(String objId, String review){
        this.objId = objId;
        this.review = review;
    }
}