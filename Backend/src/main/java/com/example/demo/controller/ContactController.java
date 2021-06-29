package com.example.demo.controller;


import com.example.demo.service.JavaEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ContactController {

    @Autowired
    private JavaEmailService javaEmailService;

    @PostMapping(value = "/contact", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.OK)
     public void getMailData(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("fromMail") String fromMail, @RequestParam("topic") String topic, @RequestParam("message") String message) throws MessagingException{
        javaEmailService.send(
                "daniel.lorenzen@hotmail.com",
                "Besked fra Tritræning.dk: " + topic,
                "<p> <b>" +  "Besked sendt fra:</b> "+ firstname + " " + lastname + "<br>" + "</p>"+
                        "<p> <b>" + "Fra E-mail:</b> " + fromMail + "<br>" + "</p>"+
                        "<p> <b>" + "Besked:</b> " + message + "<br>" + "</p>"
        );
    }



//    @RequestMapping("/send-mail")
//    public void sendMail() throws MessagingException{
//        javaEmailService.send("daniel.lorenzen@hotmail.com",'' , "Besked sendt fra"+);
//    }

//    kim@xn--tritrning-k3a.dk
//    kim@tritræning.dk
}
