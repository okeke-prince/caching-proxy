package org.example.controller;

import org.example.util.Cache;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class MainController {

    private final Cache cache = new Cache();

    @GetMapping("/products")
    public ResponseEntity<String> reroute() throws IOException, InterruptedException {
        String baseUrl = "https://dummyjson.com";
        String url = baseUrl + "/products";
        return cache.check(url);

    }
}
