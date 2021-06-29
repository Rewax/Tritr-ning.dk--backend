package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<Users> findAllUsers();
    Users findById(int id);
    Users findByMail(String mail);

}
