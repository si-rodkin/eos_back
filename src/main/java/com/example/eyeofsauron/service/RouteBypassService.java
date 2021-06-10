package com.example.eyeofsauron.service;

import com.example.eyeofsauron.repository.RouteBypassRepository;
import com.example.eyeofsauron.entity.RouteBypass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteBypassService {
    private RouteBypassRepository routeBypassRepository;

    public List<RouteBypass> getAll() { return routeBypassRepository.findAll(); }

    public Optional<RouteBypass> getById(Long id) { return routeBypassRepository.findById(id); }

    public void create(RouteBypass routeBypass) { routeBypassRepository.save(routeBypass); }

    public void update(RouteBypass routeBypass) { routeBypassRepository.save(routeBypass); }

    public void delete(RouteBypass routeBypass) { routeBypassRepository.delete(routeBypass); }

    public void deleteById(Long id) { routeBypassRepository.deleteById(id); }
}