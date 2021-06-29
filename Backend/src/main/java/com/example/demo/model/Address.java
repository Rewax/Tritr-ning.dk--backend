//package com.example.demo.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "address")
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//    //...
//
//    @OneToOne(mappedBy = "address")
//    private User user;
//
//    public Address() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
