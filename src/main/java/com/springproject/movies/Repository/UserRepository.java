package com.springproject.movies.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springproject.movies.Model.AuthUser;

public interface UserRepository extends MongoRepository<AuthUser,ObjectId>{
    Optional<AuthUser> findByUsername(String username);
}
