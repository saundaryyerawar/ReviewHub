package com.springproject.movies.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.springproject.movies.Model.CreateReviewSchema;
import com.springproject.movies.Model.Movie;
import com.springproject.movies.Model.Review;
import com.springproject.movies.Model.UpdateReviewSchema;
import com.springproject.movies.Repository.ReviewRepository;

import java.security.Principal;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/getLoggedInUser")
    public ResponseEntity<String> getLoggedinUser(Principal principal){
        return new ResponseEntity<String>(principal.getName(),HttpStatus.OK);
    }

    @GetMapping("/getReview/{objId}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable String objId){
        ObjectId id = new ObjectId(objId);
        return new ResponseEntity<Optional<Review>>(reviewRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createReview(@RequestBody CreateReviewSchema payload, Principal principal){
        Review review = reviewRepository.insert(new Review(principal.getName(), payload.getReview()));
        
        mongoTemplate.update(Movie.class)
                     .matching(Criteria.where("imdbId").is(payload.getImdbid()))
                     .apply(new Update().push("reviewIds").value(review.getId()))
                     .first();
        
        return new ResponseEntity<Review>(review,HttpStatus.CREATED);    
    }

    @PostMapping("/updateReview")
    public ResponseEntity<String> updateReview(@RequestBody UpdateReviewSchema payload, Principal principal){

        ObjectId mongodbid = new ObjectId(payload.getObjId());
        Review review = reviewRepository.findById(mongodbid).get();

        if(review.getUsername().equals(principal.getName())){
            mongoTemplate.update(Review.class)
                .matching(Criteria.where("_id").is(mongodbid))
                .apply(new Update().set("review", payload.getReview()))
                .first();
            return new ResponseEntity<String>("Review - '"+ review.getReview() +"' is updated Successfully!!",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Not Allowed!!",HttpStatus.UNAUTHORIZED);
        }
    }   

    @DeleteMapping("/remove/{objId}")
    public ResponseEntity<String> deleteReview(@PathVariable String objId, Principal principal){

        ObjectId mongodbid = new ObjectId(objId);
        Review review = reviewRepository.findById(mongodbid).get();

        if(review.getUsername().equals(principal.getName())){
            reviewRepository.deleteById(mongodbid);
            return new ResponseEntity<String>("Review - '"+ review.getReview() +"' is deleted Successfully!!",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Not Allowed!!",HttpStatus.UNAUTHORIZED);
        }
    }
}
