package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.entity.Marker;
import com.example.eyeofsauron.service.MarkerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(MarkerController.uri)
class MarkerController {
    public final static String uri = "/api/markers";

    private final MarkerService markerService = new MarkerService();

    @GetMapping
    public List<Marker> getAll() { return markerService.getAll(); }

    @GetMapping("/{id}")
    public Optional<Marker> get(@PathVariable Long id) { return markerService.getById(id); }

    @GetMapping("/free-or/{id}")
    public List<Marker> getFreeOrRouteMarkers(@PathVariable Long id) { return markerService.getFreeOrRouteMarkers(id); }

    @PostMapping
    public void create(@RequestBody Marker marker) { markerService.create(marker); }

    @PutMapping("/{id}")
    public void update(@RequestBody Marker marker) { markerService.update(marker); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { markerService.deleteById(id); }
}