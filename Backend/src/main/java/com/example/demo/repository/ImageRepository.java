package com.example.demo.repository;

import com.example.demo.model.ImageTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository <ImageTable, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM image_table WHERE blog_id = ?1", nativeQuery = true)
    void deleteByBlogId(int id);

    @Modifying
    @Transactional
    @Query(value = " FROM image_table WHERE page_id = ?1", nativeQuery = true)
    void deleteByPageId(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM image_table WHERE review_id = ?1", nativeQuery = true)
    void deleteByReviewId(int id);

    @Query(value = "SELECT * FROM image_table WHERE page_id = ?1 AND image_name = ?2", nativeQuery = true)
    Optional<ImageTable> findByPageNameAndImageName(int id, String imageName);

    @Query("FROM ImageTable WHERE review.id = ?1")
    Optional<ImageTable> findReviewById(int id);

    @Query("FROM ImageTable WHERE blog.id = ?1")
    Optional<ImageTable> findBlogById(int id);

//    ====== GET ALL REVIEW-IMAGE DATA =======
    @Query(value = "SELECT * FROM image_table WHERE review_id IS NOT NULL", nativeQuery = true)
    List <ImageTable> findAllByReview();

//    ====== GET ALL BLOG-IMAGE DATA =======
    @Query(value = "SELECT * FROM image_table WHERE blog_id IS NOT NULL", nativeQuery = true)
    List <ImageTable> findAllByBlogs();

    //    ====== GET ONE BLOG-IMAGE DATA =======
    @Query(value = "SELECT * FROM image_table WHERE blog_id = ?1", nativeQuery = true)
    ImageTable findOneByBlog(int blog_id);

    //    ====== GET ONE PAGE-IMAGE DATA =======
    @Query(value = "SELECT * FROM image_table WHERE page_id = ?1", nativeQuery = true)
    ImageTable findOneByPage(int page_id);

    Optional<ImageTable> findByName(String name);
    @Query("FROM ImageTable WHERE review.id = ?1")
//    @Query(value = "SELECT * FROM image_table WHERE reference_id = ?1",nativeQuery = true)
    ImageTable findByActivityId(int id);

    @Query("FROM ImageTable WHERE pages.id = ?1 AND name = ?2")
    ImageTable findByPagesId(int id, String name);


//    LOOK FOR IMAGES FOR CHILD TABLE ==========
    @Query("FROM ImageTable WHERE name = ?1 and pages.id = ?2")
    ImageTable findPageImageWithName(String name, int id);

    @Query("FROM ImageTable WHERE name = ?1 and review.id = ?2")
    ImageTable findReviewImageWithName(String name, int id);

    @Query("FROM ImageTable WHERE name = ?1 and blog.id = ?2")
    ImageTable findBlogImageWithName(String name, int id);

//    LOOK FOR IMAGES FOR PARENT TABLE ==========
    @Query("FROM ImageTable WHERE id = ?1 and pages.id = ?2")
    ImageTable findPagesImageWithId(Long image_id, int pages_id);

    @Query("FROM ImageTable WHERE id = ?1 and review.id = ?2")
    ImageTable findReviewImage(Long image_id, int review_id);

    @Query("FROM ImageTable WHERE id = ?1 and blog.id = ?2")
    ImageTable findBlogImageWithId(Long image_id, int blog_id);


}
