package com.pajakcodes.ecms.repository;

import com.pajakcodes.ecms.model.CallRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRecordRepository extends JpaRepository<CallRecord, Long> {

    @Query("SELECT c FROM CallRecord c " +
            "WHERE (:incidentTypeId IS NULL OR c.incidentType.id = :incidentTypeId) " +
            "AND (:callStatusTypeId IS NULL OR c.callStatusType.id = :callStatusTypeId)")
    Page<CallRecord> getCallRecords(Pageable pageable,
                                    @Param("incidentTypeId") Long incidentTypeId,
                                    @Param("callStatusTypeId") Long callStatusTypeId);

    CallRecord getCallRecordById(Long id);

}
