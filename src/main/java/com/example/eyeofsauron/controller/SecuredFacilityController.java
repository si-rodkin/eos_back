package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.entity.SecuredFacility;
import com.example.eyeofsauron.service.SecuredFacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(SecuredFacilityController.uri)
class SecuredFacilityController {
    public final static String uri = "/api/securedFacilities";

    private final SecuredFacilityService securedFacilityService = new SecuredFacilityService();

    @GetMapping
    public List<SecuredFacility> getAll() { return securedFacilityService.getAll(); }

    @GetMapping("/{id}")
    public Optional<SecuredFacility> get(@PathVariable Long id) { return securedFacilityService.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SecuredFacility securedFacility) { securedFacilityService.create(securedFacility); }

    @PutMapping("/{id}")
    public void update(@RequestBody SecuredFacility securedFacility) { securedFacilityService.update(securedFacility); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { securedFacilityService.deleteById(id); }
}