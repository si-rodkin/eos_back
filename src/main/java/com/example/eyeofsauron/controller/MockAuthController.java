package com.example.eyeofsauron.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
public class MockAuthController {
    @PostMapping("/api/rest-auth/login")
    public Map<String, String> auth() { return singletonMap("key", "12345678980"); }
}
