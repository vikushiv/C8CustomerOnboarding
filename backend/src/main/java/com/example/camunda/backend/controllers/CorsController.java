package com.example.camunda.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cors-test")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class CorsController {

    @GetMapping
    public String testCors() {
        return "CORS is working!";
    }
}