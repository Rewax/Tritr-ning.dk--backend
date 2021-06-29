//package com.example.demo.model;
//
//import javax.persistence.*;
//
//@Entity
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//    //...
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    private Address address;
//
//    public User() {
//    }
//
//    public User(Address address) {
//        this.address = address;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//}
