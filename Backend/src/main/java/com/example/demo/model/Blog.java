package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(length=1000000)
    private String description;
    private String img;
    private String datetime;
    private String author;

    @JsonBackReference
    @OneToOne(mappedBy = "blog",  fetch = FetchType.LAZY)
    private ImageTable imageTable;

    public Blog() {
    }

    public Blog(String title, String description, String img, String datetime, String author) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.datetime = datetime;
        this.author = author;
    }

    public ImageTable getImageTable() {
        return imageTable;
    }

    public void setImageTable(ImageTable imageTable) {
        this.imageTable = imageTable;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BlogPage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", datetime='" + datetime + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
