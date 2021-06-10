package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.entity.Route;
import com.example.eyeofsauron.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RouteController.uri)
class RouteController {
    public final static String uri = "/api/routes";

    private final RouteService routeService = new RouteService();

    @GetMapping("/api/secured_facilities/{objectId}/routes")
    public List<Route> getAll() { return routeService.getAll(); }

    @GetMapping("/{id}")
    public Optional<Route> get(@PathVariable Long id) { return routeService.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Route route) { routeService.create(route); }

    @PutMapping("/{id}")
    public void update(@RequestBody Route route) { routeService.update(route); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { routeService.deleteById(id); }
}