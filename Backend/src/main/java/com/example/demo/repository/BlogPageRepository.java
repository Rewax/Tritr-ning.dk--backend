package com.example.demo.repository;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BlogPageRepository extends JpaRepository<Blog, Integer> {
  List<Blog> findAllById(int id);
  Blog findByAuthor(String author);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM blog WHERE id = ?1", nativeQuery = true)
  void deleteById(int id);
  Blog findByTitle(String title);
  Blog findById(int id);
}
