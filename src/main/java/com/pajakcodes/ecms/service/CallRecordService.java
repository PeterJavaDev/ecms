package com.pajakcodes.ecms.service;

import com.pajakcodes.ecms.dto.AddCallRecordRequest;
import com.pajakcodes.ecms.dto.CallRecordDto;
import com.pajakcodes.ecms.dto.CallRecordsRequest;
import com.pajakcodes.ecms.dto.UpdateCallRecordStatusRequest;
import com.pajakcodes.ecms.enums.CallStatusTypeEnum;
import com.pajakcodes.ecms.model.CallRecord;
import com.pajakcodes.ecms.repository.CallRecordRepository;
import com.pajakcodes.ecms.repository.CallStatusTypeRepository;
import com.pajakcodes.ecms.repository.IncidentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class CallRecordService {

    private final CallRecordRepository callRecordRepository;
    private final CallStatusTypeRepository callStatusTypeRepository;
    private final IncidentTypeRepository incidentTypeRepository;

    public Page<CallRecordDto> getCallRecords(
            @PageableDefault(size = 2) Pageable pageable,
            Long incidentTypeId,
            Long callStatusTypeId) {
        Page<CallRecord> callRecordList = callRecordRepository.getCallRecords(
                pageable,
                incidentTypeId,
                callStatusTypeId);

        return callRecordList.map(callRecord -> new CallRecordDto(
                callRecord.getId(),
                callRecord.getTitle(),
                callRecord.getMessageText()
        ));
    }

    public void addCallRecrod(AddCallRecordRequest request) {
        CallRecord callRecord = CallRecord.builder()
                .title(request.title())
                .messageText(request.messageText())
                .incidentType(incidentTypeRepository.getReferenceById(request.incidentTypeId()))
                .callStatusType(callStatusTypeRepository.getReferenceById(CallStatusTypeEnum.CREATED.getId()))
                .createTime(ZonedDateTime.now())
                .build();
        callRecordRepository.save(callRecord);
    }

    public void updateCallRecordStatus(UpdateCallRecordStatusRequest request) {
        CallRecord callRecord = callRecordRepository.getCallRecordById(request.callRecordId());
        callRecord.setCallStatusType(callStatusTypeRepository.getReferenceById(request.callStatusTypeId()));
        callRecordRepository.save(callRecord);
    }

    public void deleteCallRecord(Long callRecordId) {
        callRecordRepository.deleteById(callRecordId);
    }

}
