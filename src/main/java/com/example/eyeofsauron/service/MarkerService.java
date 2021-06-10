package com.example.eyeofsauron.service;

import com.example.eyeofsauron.entity.Marker;
import com.example.eyeofsauron.repository.MarkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkerService {
    private MarkerRepository markerRepository;

    public List<Marker> getAll() { return markerRepository.findAll(); }

    public Optional<Marker> getById(Long id) { return markerRepository.findById(id); }

    public List<Marker> getFreeOrRouteMarkers(Long id) { return markerRepository.findByRouteIdOrFree(id); }

    public void create(Marker marker) { markerRepository.save(marker); }

    public void update(Marker marker) { markerRepository.save(marker); }

    public void delete(Marker marker) { markerRepository.delete(marker); }

    public void deleteById(Long id) { markerRepository.deleteById(id); }
}