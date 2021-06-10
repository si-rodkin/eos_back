package com.example.eyeofsauron.repository;

import com.example.eyeofsauron.entity.RouteBypass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteBypassRepository extends JpaRepository<RouteBypass, Long>, JpaSpecificationExecutor<RouteBypass> {
}
