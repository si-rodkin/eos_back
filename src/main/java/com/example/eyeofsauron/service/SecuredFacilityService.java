package com.example.eyeofsauron.service;

import com.example.eyeofsauron.entity.SecuredFacility;
import com.example.eyeofsauron.repository.SecuredFacilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecuredFacilityService {
    private SecuredFacilityRepository securedFacilityRepository;

    public List<SecuredFacility> getAll() { return securedFacilityRepository.findAll(); }

    public Optional<SecuredFacility> getById(Long id) { return securedFacilityRepository.findById(id); }

    public void create(SecuredFacility securedFacility) { securedFacilityRepository.save(securedFacility); }

    public void update(SecuredFacility securedFacility) { securedFacilityRepository.save(securedFacility); }

    public void delete(SecuredFacility securedFacility) { securedFacilityRepository.delete(securedFacility); }

    public void deleteById(Long id) { securedFacilityRepository.deleteById(id); }
}
