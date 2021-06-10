package com.example.eyeofsauron.controller;

import com.example.eyeofsauron.entity.MarkerReader;
import com.example.eyeofsauron.service.MarkerReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с устройствами считывания меток {@link MarkerReader}
 * @author rodkinsi
 */
@RestController
@RequestMapping(MarkerReaderController.uri)
class MarkerReaderController {
    public final static String uri = "/api/markerReaders";

    private final MarkerReaderService markerReaderService = new MarkerReaderService();

    @GetMapping
    public List<MarkerReader> getAll() { return markerReaderService.getAll(); }

    @GetMapping("/{id}")
    public Optional<MarkerReader> get(@PathVariable Long id) { return markerReaderService.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MarkerReader markerReader) { markerReaderService.create(markerReader); }

    @PutMapping("/{id}")
    public void update(@RequestBody MarkerReader markerReader) { markerReaderService.update(markerReader); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { markerReaderService.deleteById(id); }
}