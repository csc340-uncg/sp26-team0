package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.AuditLog;
import com.csc340.local_harvest_hub.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public AuditLog createAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    public Optional<AuditLog> getAuditLogById(Long id) {
        return auditLogRepository.findById(id);
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    public List<AuditLog> getAuditLogsByAdminId(Long adminId) {
        return auditLogRepository.findByAdminId(adminId);
    }

    public List<AuditLog> getAuditLogsByEntityType(String entityType) {
        return auditLogRepository.findByEntityType(entityType);
    }

    public AuditLog updateAuditLog(Long id, AuditLog auditLogDetails) {
        return auditLogRepository.findById(id).map(auditLog -> {
            auditLog.setAction(auditLogDetails.getAction());
            auditLog.setDetails(auditLogDetails.getDetails());
            return auditLogRepository.save(auditLog);
        }).orElseThrow(() -> new RuntimeException("AuditLog not found"));
    }

    public void deleteAuditLog(Long id) {
        auditLogRepository.deleteById(id);
    }
}
