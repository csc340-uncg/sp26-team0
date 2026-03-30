package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.SysAdmin;
import com.csc340.local_harvest_hub.repository.SysAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SysAdminService {
    
    @Autowired
    private SysAdminRepository sysAdminRepository;
    
    public SysAdmin createSysAdmin(SysAdmin sysAdmin) {
        return sysAdminRepository.save(sysAdmin);
    }
    
    public Optional<SysAdmin> getSysAdminById(Long id) {
        return sysAdminRepository.findById(id);
    }
    
    public List<SysAdmin> getAllSysAdmins() {
        return sysAdminRepository.findAll();
    }
    
    public SysAdmin updateSysAdmin(Long id, SysAdmin sysAdminDetails) {
        return sysAdminRepository.findById(id).map(sysAdmin -> {
            sysAdmin.setEmail(sysAdminDetails.getEmail());
            sysAdmin.setStatus(sysAdminDetails.getStatus());
            return sysAdminRepository.save(sysAdmin);
        }).orElseThrow(() -> new RuntimeException("SysAdmin not found"));
    }
    
    public void deleteSysAdmin(Long id) {
        sysAdminRepository.deleteById(id);
    }
    
    public SysAdmin getSysAdminByEmail(String email) {
        return sysAdminRepository.findByEmail(email);
    }
}
