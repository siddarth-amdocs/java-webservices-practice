package com.animasugit.rest.webservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.animasugit.rest.webservices.entity.User;
import com.animasugit.rest.webservices.exception.UserNotFoundException;
import com.animasugit.rest.webservices.service.UserDaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserDaoService service;

    //retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
        
        EntityModel<User> resource = EntityModel.of(user);
        Link link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(link);
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
       User savedUser = service.save(user);
       
       URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
       return ResponseEntity.created(location).build();
    }
}
