package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ImageRepository imageRepository;
    //    ==================================================== GET REVIEW ================================================


    //    ====== SELECT ALL REVIEW =====
    @GetMapping("/select/all/reviews")
    public List<Review> getReview(){
        List<Review> reviews = reviewRepository.findAll();

        return reviews;
    }

    //    ====== SELECT ONE REVIEW =====
    @GetMapping("/select/review/{id}")
    public Review getOneReviewWId(@PathVariable int id){
        Review review = reviewRepository.findById(id);

        return review;
    }

    //    ====== SELECT ONE REVIEW W AUTHOR =====
    @GetMapping("/select/one/review/{author}")
    public Review getOneReviewWAuthor(@PathVariable String author){
        Review review = reviewRepository.findByAuthor(author);

        return review;
    }


    //    ==================================================== POST REVIEW =============================================

//    =======  INSERT REVIEW =====

    @RequestMapping(value = "/insert/review",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Review insertReview(@RequestBody Review review) {
        System.out.println(review);

        return reviewRepository.save(review);
    }

    //    =======  UPDATE REVIEW =====

    @RequestMapping(value = "/edit/review",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Review updateReview(@RequestBody Review review){
        System.out.println("REVIEW==="+review);
        Review oneReview = reviewRepository.findById(review.getId());
        if (review.getDescription() != null){
            oneReview.setDescription(review.getDescription());
        }
        oneReview.setAuthor(review.getAuthor());
        oneReview.setDescription(review.getDescription());
        oneReview.setReviewImage(review.getReviewImage());
        System.out.println("ONE REVIEW ==="+oneReview);

        return reviewRepository.save(oneReview);
    }

    //    ==================================================== DELETE BLOG =============================================

    @ResponseStatus(code=HttpStatus.OK)
    @DeleteMapping("/delete/review/{id}")
    public void deleteReview(@PathVariable int id){
        System.out.println("ID================"+id);
        try {
            imageRepository.deleteByReviewId(id);
            reviewRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("FEJL i DELETE =" + ex.getMessage());
        }
    }
}

