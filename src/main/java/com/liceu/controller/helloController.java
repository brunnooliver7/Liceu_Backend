package com.liceu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    
    @GetMapping(value = "/")
    public String getMethodName() {
        return "Olá mundo!";
    }

}