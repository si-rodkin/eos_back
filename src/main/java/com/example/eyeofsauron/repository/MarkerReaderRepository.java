package com.example.eyeofsauron.repository;

import com.example.eyeofsauron.entity.MarkerReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerReaderRepository extends JpaRepository<MarkerReader, Long>, JpaSpecificationExecutor<MarkerReader> {
}
