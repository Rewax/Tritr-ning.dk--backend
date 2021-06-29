package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findById(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM review WHERE id = ?1", nativeQuery = true)
    void deleteById(int id);
    List<Review> findAll();
    Review findByAuthor(String author);

}
