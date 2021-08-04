package com.animasugit.rest.webservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.animasugit.rest.webservices.entity.Post;
import com.animasugit.rest.webservices.entity.User;
import com.animasugit.rest.webservices.exception.UserNotFoundException;
import com.animasugit.rest.webservices.repository.PostRepository;
import com.animasugit.rest.webservices.repository.UserRepository;

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
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    //retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsersJPA(){
        return userRepository.findAll();
    }

    //retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserJPA(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        
        EntityModel<User> resource = EntityModel.of(user.get());
        Link link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsersJPA()).withRel("all-users");
        resource.add(link);
        return resource;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserJPA(@PathVariable int id){
         userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUserJPA(@Valid @RequestBody User user){
       User savedUser = userRepository.save(user);
       
       URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
       return ResponseEntity.created(location).build();
    }


    // POSTS

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){
        Optional<User> uOptional = userRepository.findById(id);

        if(!uOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
          
        }  
        return uOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> uOptional = userRepository.findById(id);

        if(!uOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
          
        }  

        User user = uOptional.get();
        post.setUser(user);

        postRepository.save(post);
        URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
