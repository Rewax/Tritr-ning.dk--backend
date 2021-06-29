package com.example.demo.repository;

import com.example.demo.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository  extends JpaRepository <About, Integer>{
    About findById(int id);
    About deleteById(int id);
}
