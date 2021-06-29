package com.example.demo.controller;

import com.example.demo.model.About;
import com.example.demo.model.Review;
import com.example.demo.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")



//    ==================================================== GET ABOUT ============================================

//    ====== SELECT ABOUT =====
public class AboutController {
    @Autowired AboutRepository aboutRepository;

    @GetMapping("/select/about/{id}")
        public About getAbout(@PathVariable int id){
            About about = aboutRepository.findById(id);

            return about;
        }

    @PostMapping(value = "/insert/about" , consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public About insertAbout(@RequestBody About about){

        return aboutRepository.save(about);
    }
    @PutMapping(value = "/edit/about/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public About editAbout(@RequestBody About about){
        System.out.println(about.getId());

        About objectToUpdate = aboutRepository.findById(about.getId());
        if (!about.getDescription().equals("")){
            objectToUpdate.setDescription(about.getDescription());
        }
        objectToUpdate.setImg(about.getImg());
        objectToUpdate.setTitle(about.getTitle());
        System.out.println(about);

        return aboutRepository.save(objectToUpdate);
    }
}
