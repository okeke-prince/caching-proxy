package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/**")
    public ResponseEntity<String> reroute(HttpServletRequest request) throws IOException, InterruptedException {
        String baseUrl = "https://dummyjson.com";
        String url = baseUrl + request.getRequestURI();
        return cache.check(url);

    }
}
