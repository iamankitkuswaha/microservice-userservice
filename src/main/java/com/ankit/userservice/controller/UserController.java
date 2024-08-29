package com.ankit.userservice.controller;

import com.ankit.userservice.entity.User;
import com.ankit.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Throwable throwable){
        log.info("Fallback has executed because rating/hotel service is down: {}",throwable);
        return new ResponseEntity<>(User.builder().build(),HttpStatus.OK);
    }
}
