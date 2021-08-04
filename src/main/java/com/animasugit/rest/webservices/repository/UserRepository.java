package com.animasugit.rest.webservices.repository;

import com.animasugit.rest.webservices.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
