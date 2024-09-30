package com.pajakcodes.ecms.repository;

import com.pajakcodes.ecms.model.CallStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallStatusTypeRepository extends JpaRepository<CallStatusType, Long> {
}
