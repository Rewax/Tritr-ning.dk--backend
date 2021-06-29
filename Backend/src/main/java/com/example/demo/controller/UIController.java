package com.example.demo.controller;

import com.example.demo.model.ImageTable;
import com.example.demo.model.Pages;
import com.example.demo.model.Review;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PagesRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "ui")
public class UIController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PagesRepository pagesRepository;

    @GetMapping("/get/review")
    public List<ImageTable> getAllReviews(){
        return imageRepository.findAllByReview();
    }

    @GetMapping("/get/blog")
    public List<ImageTable> getAllBlogs(){
        return imageRepository.findAllByBlogs();
    }

    @GetMapping("/get/one/blog/{blog_id}")
    public ImageTable getOneBlog(@PathVariable int blog_id){
        return imageRepository.findOneByBlog(blog_id);
    }

    @GetMapping("/select/activities")
    public List<Pages> getActivity() {
        List<Pages> activities = pagesRepository.findAll();

        return activities;
    }

    @GetMapping("/get/one/page/{page_id}")
    public ImageTable getOnePage(@PathVariable int page_id){
        return imageRepository.findOneByPage(page_id);
    }



}
