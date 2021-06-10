package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.service.RouteBypassService;
import com.example.eyeofsauron.entity.RouteBypass;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RouteBypassController.uri)
class RouteBypassController {
    public final static String uri = "/api/routeBypasses";

    private final RouteBypassService routeBypassService = new RouteBypassService();

    @GetMapping("/api/secured_facilities/{objectId}/routeBypasses")
    public List<RouteBypass> getAll() { return routeBypassService.getAll(); }

    @GetMapping("/{id}")
    public Optional<RouteBypass> get(@PathVariable Long id) { return routeBypassService.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RouteBypass routeBypass) { routeBypassService.create(routeBypass); }

    @PutMapping("/{id}")
    public void update(@RequestBody RouteBypass routeBypass) { routeBypassService.update(routeBypass); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { routeBypassService.deleteById(id); }
}