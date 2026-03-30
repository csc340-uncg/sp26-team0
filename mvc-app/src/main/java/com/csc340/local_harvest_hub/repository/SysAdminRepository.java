package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.SysAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRepository extends JpaRepository<SysAdmin, Long> {
    SysAdmin findByEmail(String email);
}
