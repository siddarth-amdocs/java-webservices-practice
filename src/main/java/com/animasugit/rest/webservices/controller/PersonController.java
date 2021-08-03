package com.animasugit.rest.webservices.controller;

import com.animasugit.rest.webservices.component.Name;
import com.animasugit.rest.webservices.entity.PersonV1;
import com.animasugit.rest.webservices.entity.PersonV2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    
    // URI PARAMETER
    @GetMapping("person/v1")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping("person/v2")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    // REQUEST PARAMETER
    @GetMapping(value = "person/param",params="version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/param",params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    // NORMAL HEADER
    @GetMapping(value = "person/header",headers="X_API_VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/param",headers="X_API_VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    // ACCEPT HEADER
    @GetMapping(value = "person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/produces",produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }


}
