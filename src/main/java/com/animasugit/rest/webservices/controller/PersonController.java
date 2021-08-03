package com.animasugit.rest.webservices.controller;

import com.animasugit.rest.webservices.component.Name;
import com.animasugit.rest.webservices.entity.PersonV1;
import com.animasugit.rest.webservices.entity.PersonV2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    
    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
}
