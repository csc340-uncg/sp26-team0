package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
   @Query(value = "SELECT a.* FROM auditLogs a WHERE a.admin_id = :adminId", nativeQuery = true)
    List<AuditLog> findByAdminId(Long adminId);
    List<AuditLog> findByEntityType(String entityType);
}
