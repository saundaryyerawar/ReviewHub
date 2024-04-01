package com.springproject.movies.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springproject.movies.Model.Review;


public interface ReviewRepository extends MongoRepository<Review,ObjectId>{

}
