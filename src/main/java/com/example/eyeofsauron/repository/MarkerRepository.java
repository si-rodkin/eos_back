package com.example.eyeofsauron.repository;

import com.example.eyeofsauron.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long>, JpaSpecificationExecutor<Marker> {
    @Query(nativeQuery = true, value = "SELECT * FROM markers WHERE (:id > -1 AND route_id = :id) OR route_id is null")
    List<Marker> findByRouteIdOrFree(Long id);
}