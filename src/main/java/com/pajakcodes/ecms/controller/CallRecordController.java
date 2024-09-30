package com.pajakcodes.ecms.controller;

import com.pajakcodes.ecms.dto.AddCallRecordRequest;
import com.pajakcodes.ecms.dto.CallRecordDto;
import com.pajakcodes.ecms.dto.CallRecordsRequest;
import com.pajakcodes.ecms.dto.UpdateCallRecordStatusRequest;
import com.pajakcodes.ecms.service.CallRecordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("callrecord")
@AllArgsConstructor
public class CallRecordController {

    private final CallRecordService callRecordService;

    @GetMapping("listCallRecords")
    @PreAuthorize("hasAuthority('list_calls')")
    @Operation(
            summary = "List calls",
            description = "List pageable calls by incidentTypeId and callStatusTypeId")
    public ResponseEntity<Page<CallRecordDto>> getCallRecords(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) @Range(min = 1, max = 4) Long incidentTypeId,
            @RequestParam(required = false) @Range(min = 1, max = 3) Long callStatusTypeId) throws RuntimeException {
        Page<CallRecordDto> callList = callRecordService.getCallRecords(pageable, incidentTypeId, callStatusTypeId);
        return new ResponseEntity<>(callList, HttpStatus.OK);
    }

    @PostMapping("addCallRecrod")
    @PreAuthorize("hasAuthority('create_calls')")
    @Operation(
            summary = "Add call",
            description = "Add new call in created status")
    public ResponseEntity<Void> addCallRecrod(@RequestBody @Valid AddCallRecordRequest request) throws RuntimeException {
        callRecordService.addCallRecrod(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updateCallRecordStatus")
    @PreAuthorize("hasAuthority('update_calls')")
    @Operation(
            summary = "Update call status",
            description = "Update call status to given callStatusTypeId ")
    public ResponseEntity<Page<CallRecordDto>> updateCallRecordStatus(@RequestBody @Valid UpdateCallRecordStatusRequest request) throws RuntimeException {
        callRecordService.updateCallRecordStatus(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteCallRecord")
    @PreAuthorize("hasAuthority('delete_calls')")
    @Operation(
            summary = "Delete call",
            description = "Delete call permanently by id")
    public ResponseEntity<Page<CallRecordDto>> deleteCallRecord(@RequestParam @NotNull Long callRecordId) throws RuntimeException {
        callRecordService.deleteCallRecord(callRecordId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
