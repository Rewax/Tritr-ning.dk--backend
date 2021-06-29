package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Arrays;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "image_table")
public class ImageTable {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_name")
    private String name;
    @Column(name = "type")
    private String type;
        //image bytes can have large lengths so we specify a value
        //which is more than the default length for picByte column
    @Column(name = "picByte", length = 10000000)
    private byte[] picByte;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private Pages pages;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "about_id", referencedColumnName = "id")
    private About about;

    public ImageTable() {
        super();
    }

    public ImageTable(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "ImageTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", picByte=" + Arrays.toString(picByte) +
                ", review=" + review +
                ", pages=" + pages +
                ", blog=" + blog +
                ", about=" + about +
                '}';
    }
}
