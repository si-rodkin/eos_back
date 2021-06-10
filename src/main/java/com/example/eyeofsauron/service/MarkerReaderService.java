package com.example.eyeofsauron.service;

import com.example.eyeofsauron.entity.MarkerReader;
import com.example.eyeofsauron.repository.MarkerReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkerReaderService {
    private MarkerReaderRepository markerReaderRepository;

    public List<MarkerReader> getAll() { return markerReaderRepository.findAll(); }

    public Optional<MarkerReader> getById(Long id) { return markerReaderRepository.findById(id); }

    public void create(MarkerReader markerReader) { markerReaderRepository.save(markerReader); }

    public void update(MarkerReader markerReader) { markerReaderRepository.save(markerReader); }

    public void delete(MarkerReader markerReader) { markerReaderRepository.delete(markerReader); }

    public void deleteById(Long id) { markerReaderRepository.deleteById(id); }
}
