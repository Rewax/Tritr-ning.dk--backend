package com.example.demo.controller;

//import com.example.demo.model.Blog;
import com.example.demo.model.Blog;
import com.example.demo.repository.BlogPageRepository;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BlogPageController {

    @Autowired
    BlogPageRepository blogPageRepository;

    @Autowired
    ImageRepository imageRepository;

    //    ==================================================== GET BLOG ================================================

//    ====== SELECT ALL BLOG =====

    @GetMapping("/select/blogs")
    public List<Blog> getBlogs(){
        List<Blog> blogs = blogPageRepository.findAll();

        return blogs;
    }

    //    ====== SELECT ONE BLOG =====
    @GetMapping("/select/blog/{title}")
    public Blog getOneBlogWTitle(@PathVariable String title){
        Blog blog = blogPageRepository.findByTitle(title);

        return blog;
    }

    //    ====== SELECT ALL BLOGS WITH ID =====
    @GetMapping("/select/all/blogs/{id}")
    public List<Blog> getAllBlogsWId(@PathVariable int id){
        System.out.println(id);
        List<Blog> blogs = blogPageRepository.findAllById(id);

        return blogs;
    }

    //    ==================================================== POST BLOG ================================================

//    =======  INSERT BLOG =====

    @PostMapping(value = "/insert/blog", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog insertBlog(@RequestBody Blog blog) {
        System.out.println(blog);

        return blogPageRepository.save(blog);
    }

    //    =======  EDIT BLOG =====

    @PutMapping(value="/edit/blog", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog editBlog(@RequestBody Blog blog){
        System.out.println(blog.getId());

        Blog objectToUpdate = blogPageRepository.findById(blog.getId());
        if (objectToUpdate.getDescription() != null){
            objectToUpdate.setDescription(blog.getDescription());
        }
        objectToUpdate.setAuthor(blog.getAuthor());
        objectToUpdate.setImg(blog.getImg());
        objectToUpdate.setTitle(blog.getTitle());
        objectToUpdate.setDatetime(blog.getDatetime());
        System.out.println(blog);

        return blogPageRepository.save(objectToUpdate);

    }

    //    ==================================================== DELETE BLOG ================================================

    @ResponseStatus(code=HttpStatus.OK)
    @DeleteMapping("/delete/blog/{id}")
    public void deleteBlog(@PathVariable int id){
        System.out.println("ID================"+id);
        try {
            imageRepository.deleteByBlogId(id);
            blogPageRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("FEJL i DELETE =" + ex.getMessage());
        }
    }
}
