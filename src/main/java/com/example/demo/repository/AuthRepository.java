package com.example.demo.repository;

import com.example.demo.model.Auth;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findById(int id);
    Auth findByUsers(Users users);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Auth WHERE user_id = ?1", nativeQuery = true)
    void deleteById(@Param("id")int id);
}
