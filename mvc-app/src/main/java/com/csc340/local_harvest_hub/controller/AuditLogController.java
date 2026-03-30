package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.AuditLog;
import com.csc340.local_harvest_hub.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) {
        AuditLog createdAuditLog = auditLogService.createAuditLog(auditLog);
        return new ResponseEntity<>(createdAuditLog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        List<AuditLog> auditLogs = auditLogService.getAllAuditLogs();
        return new ResponseEntity<>(auditLogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable Long id) {
        Optional<AuditLog> auditLog = auditLogService.getAuditLogById(id);
        return auditLog.map(log -> new ResponseEntity<>(log, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByAdminId(@PathVariable Long adminId) {
        List<AuditLog> auditLogs = auditLogService.getAuditLogsByAdminId(adminId);
        return new ResponseEntity<>(auditLogs, HttpStatus.OK);
    }

    @GetMapping("/entity-type/{entityType}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByEntityType(@PathVariable String entityType) {
        List<AuditLog> auditLogs = auditLogService.getAuditLogsByEntityType(entityType);
        return new ResponseEntity<>(auditLogs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLog> updateAuditLog(@PathVariable Long id, @RequestBody AuditLog auditLogDetails) {
        try {
            AuditLog updatedAuditLog = auditLogService.updateAuditLog(id, auditLogDetails);
            return new ResponseEntity<>(updatedAuditLog, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
