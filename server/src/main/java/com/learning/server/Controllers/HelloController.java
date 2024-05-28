package com.learning.server.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String helloController(){
        return "Hello This is Backend API using Spring boot By SHABIT TAJ S...";
    }
}
