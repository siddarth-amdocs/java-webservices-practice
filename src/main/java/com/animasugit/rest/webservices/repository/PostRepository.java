package com.animasugit.rest.webservices.repository;

import com.animasugit.rest.webservices.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{
    
}
