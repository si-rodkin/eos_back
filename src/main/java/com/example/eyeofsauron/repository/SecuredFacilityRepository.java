package com.example.eyeofsauron.repository;

import com.example.eyeofsauron.entity.SecuredFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuredFacilityRepository extends JpaRepository<SecuredFacility, Long>, JpaSpecificationExecutor<SecuredFacility> {
}
