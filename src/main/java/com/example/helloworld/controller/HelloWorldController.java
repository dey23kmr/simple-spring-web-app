package com.example.helloworld.controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RestController
public class HelloWorldController {

    private final ResourceLoader resourceLoader;

    public HelloWorldController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/hello")
    public String sendGreetings() {
        return "Hello, Dev Kumar!";
    }

    @GetMapping("/home")
    @ResponseBody
    public String getHtmlPage() throws IOException {
        // Load HTML file from classpath
        Resource resource = resourceLoader.getResource("classpath:templates/pilot.html");

        // Read the HTML file content into a string
        return readResourceToString(resource);
    }

    // Method to read the content of a resource into a string
    private String readResourceToString(Resource resource) throws IOException {
        try (Scanner scanner = new Scanner(resource.getInputStream(), StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
