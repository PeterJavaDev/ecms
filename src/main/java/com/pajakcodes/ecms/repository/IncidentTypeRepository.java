package com.pajakcodes.ecms.repository;

import com.pajakcodes.ecms.model.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentTypeRepository extends JpaRepository<IncidentType, Long> {
}
