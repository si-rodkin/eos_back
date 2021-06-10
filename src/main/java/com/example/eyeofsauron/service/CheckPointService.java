package com.example.eyeofsauron.service;

import com.example.eyeofsauron.entity.CheckPoint;
import com.example.eyeofsauron.repository.CheckPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с контрольными точками
 *
 * @author rodkinsi
 */
@Service
public class CheckPointService {
    private CheckPointRepository checkPointRepository;

    public List<CheckPoint> getAll() { return checkPointRepository.findAll(); }

    public Optional<CheckPoint> getById(Long id) { return checkPointRepository.findById(id); }

    public void create(CheckPoint checkPoint) { checkPointRepository.save(checkPoint); }

    public void update(CheckPoint checkPoint) { checkPointRepository.save(checkPoint); }

    public void delete(CheckPoint checkPoint) { checkPointRepository.delete(checkPoint); }

    public void deleteById(Long id) { checkPointRepository.deleteById(id); }
}