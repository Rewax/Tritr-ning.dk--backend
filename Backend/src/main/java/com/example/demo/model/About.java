package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String img;

    @JsonBackReference
    @OneToOne(mappedBy = "about", fetch = FetchType.LAZY)
    private ImageTable imageTable;

    public About() {
    }

    public About(String title, String description, String img, ImageTable imageTable) {
        this.title = title;
        this.description = description;
        this.img = img;
//        this.imageTable = imageTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

//    public ImageTable getImageTable() {
//        return imageTable;
//    }
//
//    public void setImageTable(ImageTable imageTable) {
//        this.imageTable = imageTable;
//    }
}


