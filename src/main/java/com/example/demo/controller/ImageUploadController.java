package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CompressService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "image")
public class ImageUploadController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PagesRepository pagesRepository;
    @Autowired
    BlogPageRepository blogPageRepository;
    @Autowired
    AboutRepository aboutRepository;

        CompressService compressService = new CompressService();

//    UPLOAD IMAGE -------
    @PostMapping(value="/upload", consumes =  "multipart/form-data")
    public BodyBuilder uploadImage(@RequestParam("imageFile")MultipartFile file, @RequestParam("author_id") int author_id, @RequestParam("page_id") int page_id, @RequestParam("blog_id") int blog_id, @RequestParam("about_id") int about_id) throws IOException {
        System.out.println("Orignal Image Byte Size - " + file.getBytes().length);

        if(author_id != 0){
            Review getReview = reviewRepository.getOne(author_id);

            ImageTable img = new ImageTable(file.getOriginalFilename(), file.getContentType(), compressService.compressBytes(file.getBytes()));
            img.setReview(getReview);
            imageRepository.save(img);
        }
        if(page_id != 0){
            Pages getPage = pagesRepository.getOne(page_id);

            ImageTable img = new ImageTable(file.getOriginalFilename(), file.getContentType(), compressService.compressBytes(file.getBytes()));
            img.setPages(getPage);
            imageRepository.save(img);
        }
        if(blog_id !=0){
            Blog getBlog = blogPageRepository.getOne(blog_id);

            ImageTable img = new ImageTable(file.getOriginalFilename(), file.getContentType(), compressService.compressBytes(file.getBytes()));
            img.setBlog(getBlog);
            imageRepository.save(img);
        }
        if(about_id !=0){
            About getAbout = aboutRepository.getOne(about_id);

            ImageTable img = new ImageTable(file.getOriginalFilename(), file.getContentType(), compressService.compressBytes(file.getBytes()));
            img.setAbout((getAbout));
            imageRepository.save(img);
        }


        return ResponseEntity.status(HttpStatus.OK);
    }

//    UPDATE IMAGE -------
    @PostMapping(value="/update", consumes = "multipart/form-data")
    public ImageTable updateImage(@RequestParam("imageFile")MultipartFile file,  @RequestParam("image_id") Long image_id, @RequestParam("author_id") int author_id, @RequestParam("page_id") int page_id, @RequestParam("review_id") int review_id, @RequestParam("blog_id") int blog_id) throws IOException {
//        System.out.println("Orignal Image Byte Size - " + file.getBytes().length);
        System.out.println("FIRST ID ======="+author_id);

        ImageTable objectToUpdate = null;

        if(author_id != 0){
            objectToUpdate = imageRepository.findByActivityId(author_id);

            System.out.println("IMAGE========="+objectToUpdate);

            objectToUpdate.setName(file.getOriginalFilename());
            objectToUpdate.setType(file.getContentType());
            objectToUpdate.setPicByte(compressService.compressBytes(file.getBytes()));
        }
        if(page_id != 0){
            objectToUpdate = imageRepository.findPagesImageWithId(image_id, page_id);
            System.out.println("OBJECT UPDATE======"+objectToUpdate);
            System.out.println(file.getOriginalFilename());
            objectToUpdate.setName(file.getOriginalFilename());
            objectToUpdate.setType(file.getContentType());
            objectToUpdate.setPicByte(compressService.compressBytes(file.getBytes()));
        }

        if(review_id != 0){
            objectToUpdate = imageRepository.findReviewImage(image_id, review_id);
            System.out.println("OBJECT UPDATE======"+objectToUpdate);
            System.out.println(file.getOriginalFilename());
            objectToUpdate.setName(file.getOriginalFilename());
            objectToUpdate.setType(file.getContentType());
            objectToUpdate.setPicByte(compressService.compressBytes(file.getBytes()));
        }

        if(blog_id != 0){
            objectToUpdate = imageRepository.findBlogImageWithId(image_id, blog_id);
            System.out.println("OBJECT UPDATE======"+objectToUpdate);
            System.out.println(file.getOriginalFilename());
            objectToUpdate.setName(file.getOriginalFilename());
            objectToUpdate.setType(file.getContentType());
            objectToUpdate.setPicByte(compressService.compressBytes(file.getBytes()));
        }


        return imageRepository.save(objectToUpdate);
//        return ResponseEntity.status(HttpStatus.OK);
    }

//    GET IMAGE ------
//    @GetMapping(path = {"/get/{imageName}"})
//    public List<byte[]> getMultipleImages(@PathVariable("imageName") String imageName) throws IOException{
//        final List<ImageTable> retrivedImage = imageRepository.findAllByName(imageName);
//
//        System.out.println(retrivedImage);
////        returnImage.setName();
//
////
//        List<byte[]> returnImage = new ArrayList();
////
//        for(int i = 0; i < retrivedImage.size(); i++){
////            List<? extends ImageTable> img = (List<? extends ImageTable>) new ImageTable(retrivedImage.get(i).getName(), retrivedImage.get(i).getType(), compressService.decompressBytes(retrivedImage.get(i).getPicByte()));
//            ImageTable newImage = null;
////            returnImage.add(img.get(i).getPicByte());
//            newImage.setName(retrivedImage.get(i).getName());
//            newImage.setType(retrivedImage.get(i).getType());
//            newImage.setPicByte(CompressService.decompressBytes(retrivedImage.get(i).getPicByte()));
//
//            returnImage.add(newImage.getPicByte());
//        }
//
//        return returnImage;
//    }

    @GetMapping("/get/imageid/{imageName}/{parentId}/{childTable}")
    public ImageTable findImageWithId(@PathVariable("imageName") String imageName,@PathVariable("parentId") int parentId, @PathVariable("childTable") String childTable){
            System.out.println(childTable);

        ImageTable object = null;


        if (childTable.equals("page")){
            object = imageRepository.findPageImageWithName(imageName, parentId);
        }if (childTable.equals("review")){
            object = imageRepository.findReviewImageWithName(imageName, parentId);
        }if (childTable.equals("blog")){
            object = imageRepository.findBlogImageWithName(imageName, parentId);
        }

        return object;
    }

    //        GET PAGE IMAGE ------
    @GetMapping(path = {"/get/page/{pageid}/{imagename}"})
    public byte[] getPageImage(@PathVariable("pageid") int page_id, @PathVariable("imagename") String imageName) throws IOException{
        Optional<ImageTable> retrivedImage = imageRepository.findByPageNameAndImageName(page_id, imageName);
        System.out.println("IMAGE==========="+retrivedImage);
        ImageTable img = new ImageTable(retrivedImage.get().getName(), retrivedImage.get().getType(), compressService.decompressBytes(retrivedImage.get().getPicByte()));
        return img.getPicByte();
    }

//        GET REVIEW IMAGE ------
    @GetMapping(path = {"/get/{author}"})
    public byte[] getImage(@PathVariable("author") String author) throws IOException{
        Review review = reviewRepository.findByAuthor(author);
        System.out.println("REVIEW=========="+review);
        Optional<ImageTable> retrivedImage = imageRepository.findReviewById(review.getId());
        System.out.println("IMAGE==========="+retrivedImage);
        ImageTable img = new ImageTable(retrivedImage.get().getName(), retrivedImage.get().getType(), compressService.decompressBytes(retrivedImage.get().getPicByte()));
        return img.getPicByte();
    }

    //        GET BLOG IMAGE ------
    @GetMapping(path = {"/get/blog/{author}"})
    public byte[] getBlogImage(@PathVariable("author") String author) throws IOException{
        Blog blog = blogPageRepository.findByAuthor(author);
        System.out.println("BLOG=========="+blog);
        Optional<ImageTable> retrivedImage = imageRepository.findBlogById(blog.getId());
        System.out.println("IMAGE==========="+retrivedImage);
        ImageTable img = new ImageTable(retrivedImage.get().getName(), retrivedImage.get().getType(), compressService.decompressBytes(retrivedImage.get().getPicByte()));
        return img.getPicByte();
    }

    //    GET IMAGE ------
//    @GetMapping(path = {"/get/{imageName}"})
//    public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException{
//        final Optional<ImageTable> retrivedImage = imageRepository.findByName(imageName);
//        ImageTable img = new ImageTable(retrivedImage.get().getName(), retrivedImage.get().getType(), compressService.decompressBytes(retrivedImage.get().getPicByte()));
//        return img.getPicByte();
//    }
}
