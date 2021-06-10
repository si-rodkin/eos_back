package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.entity.CheckPoint;
import com.example.eyeofsauron.service.CheckPointService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CheckPointController.uri)
class CheckPointController {
    public final static String uri = "/api/checkPoints";

    private final CheckPointService checkPointService = new CheckPointService();

    @GetMapping
    public List<CheckPoint> getAll() { return checkPointService.getAll(); }

    @GetMapping("/{id}")
    public Optional<CheckPoint> get(@PathVariable Long id) { return checkPointService.getById(id); }

    @PostMapping
    public void create(@RequestBody CheckPoint checkPoint) { checkPointService.create(checkPoint); }

    @PutMapping("/{id}")
    public void update(@RequestBody CheckPoint checkPoint) { checkPointService.update(checkPoint); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { checkPointService.deleteById(id); }
}