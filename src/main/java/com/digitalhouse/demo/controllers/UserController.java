package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    //public ResponseEntity<User> findAll(){
    //    return ResponseEntity.ok().body(User);
    //}
}
