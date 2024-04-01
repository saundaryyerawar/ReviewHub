package com.springproject.movies.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springproject.movies.Model.Movie;

public interface MovieRepository extends MongoRepository<Movie, ObjectId>{  

    Optional<Movie> findByImdbId(String imdbId);

}
