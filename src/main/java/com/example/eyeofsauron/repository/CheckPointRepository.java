package com.example.eyeofsauron.repository;

import com.example.eyeofsauron.entity.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckPointRepository extends JpaRepository<CheckPoint, Long>, JpaSpecificationExecutor<CheckPoint> {
}
