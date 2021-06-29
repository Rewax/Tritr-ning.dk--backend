package com.example.demo.controller;

import com.example.demo.model.Pages;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PagesRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PagesController {

    @Autowired
    PagesRepository pagesRepository;
    @Autowired
    ImageRepository imageRepository;

    //    ==================================================== GET Page ============================================

    //    ====== SELECT ALL ACTIVITIES =====
    @GetMapping("/select/page")
    public List<Pages> getActivity() {
        List<Pages> activities = pagesRepository.findAll();

        return activities;

    }

//    ====== SELECT ALL ACTIVITIES BY ID =====
    @GetMapping("/select/all/activities/id/{id}")
    public Pages getActivityById(@PathVariable int id) {
        Pages activities = pagesRepository.asd(id);
        System.out.println(id);
        return activities;

    }
    //    ====== SELECT ONE ACTIVITIES WITH TITLE =====
    @GetMapping("/select/page/{title}")
    public Pages getOneActivityWId(@PathVariable String title) {
        Pages pages = pagesRepository.findByTitle(title);

        return pages;
    }

    //    ====== SELECT ONE ACTIVITIES WITH ID =====
    @GetMapping("/select/one/activity/{id}")
    public Pages getOneActivityWId(@PathVariable int id) {
        Pages pages = pagesRepository.findById(id);

        return pages;
    }

    //    ====== SELECT ALL ACTIVITIES WITH TITLE =====
    @GetMapping("/select/all/activities/{title}")
    public List<Pages> getAllEventsWTitle(@PathVariable String title){
        System.out.println(title);
        List<Pages> activities = pagesRepository.findAllByTitle(title);

        return activities;
    }

    //    ==================================================== Insert Page ===========================================

    @PostMapping(value="/insert/page", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pages insertActivity(@RequestBody Pages pages){

        return pagesRepository.save(pages);

    }

    //    ==================================================== Update Page ===========================================

    @PutMapping(value="/edit/page", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pages editActivityWId(@RequestBody Pages pages){
        System.out.println(pages.getId());
        System.out.println(pages);

        Pages objectToUpdate = pagesRepository.findById(pages.getId());
        System.out.println(objectToUpdate);
        if (pages.getDescription() != null){
            objectToUpdate.setDescription(pages.getDescription());
        }
//        objectToUpdate.setBanner(pages.getBanner());
        objectToUpdate.setImg(pages.getImg());
        objectToUpdate.setTitle(pages.getTitle());
        System.out.println(pages);

        return pagesRepository.save(objectToUpdate);

    }
//    ==================================================== DELETE Page =============================================

    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/page/{id}")
    public void deletePage(@PathVariable int id){
        System.out.println(id);
        try {
            imageRepository.deleteByPageId(id);
            pagesRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("FEJL i DELETE =" + ex.getMessage());
        }
    }
}

