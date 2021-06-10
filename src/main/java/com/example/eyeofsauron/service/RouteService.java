package com.example.eyeofsauron.service;

import com.example.eyeofsauron.entity.Route;
import com.example.eyeofsauron.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private RouteRepository routeRepository;

    public List<Route> getAll() { return routeRepository.findAll(); }

    public Optional<Route> getById(Long id) { return routeRepository.findById(id); }

    public List<Route> getBySecuredFacility(Long id) { return routeRepository.getBySecuredFacilityId(id); }

    public void create(Route route) { routeRepository.save(route); }

    public void update(Route route) { routeRepository.save(route); }

    public void delete(Route route) { routeRepository.delete(route); }

    public void deleteById(Long id) { routeRepository.deleteById(id); }
}