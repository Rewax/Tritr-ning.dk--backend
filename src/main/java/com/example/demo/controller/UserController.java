package com.example.demo.controller;

import com.example.demo.model.Auth;
import com.example.demo.model.Users;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;


//    ==================================================== GET USERS ===================================================

    //     === SELECT ALL USERS ===
    @GetMapping(value="/select/users")
    public List<Users> getAllUsers(){
        List <Users> user = userRepository.findAllUsers();

        System.out.println("USER FOUND==="+user);

        return user;
    }

    //    ====== SELECT ONE USER =====
    @GetMapping("/select/user/{id}")
    public Users getOneUserWID(@PathVariable int id){
        Users users = userRepository.findById(id);

        return users;
    }

//   ==================================================== POST USERS TO DB =============================================

    //     === INSERT USER ===
    @PostMapping(value = "/insert/user", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Auth insertUser(@RequestBody Users users) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncrypted = encoder.encode(users.getPassword());
        users.setPassword(passwordEncrypted);
        Users userSaved = userRepository.save(users);
        Auth auth = new Auth();
        auth.setRole("ROLE_ADMIN");
        auth.setUser_mail(users.getMail());
        auth.setUsers(userSaved);

        System.out.println("blabla" + userSaved);

        return authRepository.save(auth);
    }


    //    =======  EDIT USER =====

    @PutMapping(value="/edit/user", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Users editBlog(@RequestBody Users users){
        System.out.println(users.getId());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncrypted = encoder.encode(users.getPassword());

        Auth auth               = authRepository.findByUsers(users);
        Users objectToUpdate    = userRepository.findById(users.getId());

        auth.setUser_mail(users.getMail());

        objectToUpdate.setMail(users.getMail());
        objectToUpdate.setPassword(passwordEncrypted);

        System.out.println(users);

        return userRepository.save(objectToUpdate);

    }


//    ==================================================== DELETE USER ==============================================

    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable int id){
        System.out.println("ID=============="+id);
        Users users = userRepository.getOne(id);
//        try {
            authRepository.deleteById(id);
            userRepository.delete(users);
//        } catch (EmptyResultDataAccessException ex) {
//            System.out.println("FEJL i DELETE =" + ex.getMessage());
//        }
    }


}