package com.animasugit.rest.webservices.controller;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {
    //GET
    //URI - /hello-world
    //method - "Hello World"
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
