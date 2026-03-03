package com.csc340.local_harvest_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Table(name = "sys_admins")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "admin_id")
public class SysAdmin extends User {

  @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties("admin")
  private List<AuditLog> auditLogs;
}
