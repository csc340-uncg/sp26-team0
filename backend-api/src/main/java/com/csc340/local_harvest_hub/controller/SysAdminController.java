package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.SysAdmin;
import com.csc340.local_harvest_hub.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sysadmins")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;

    @PostMapping
    public ResponseEntity<SysAdmin> createSysAdmin(@RequestBody SysAdmin sysAdmin) {
        SysAdmin createdSysAdmin = sysAdminService.createSysAdmin(sysAdmin);
        return new ResponseEntity<>(createdSysAdmin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SysAdmin>> getAllSysAdmins() {
        List<SysAdmin> sysAdmins = sysAdminService.getAllSysAdmins();
        return new ResponseEntity<>(sysAdmins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysAdmin> getSysAdminById(@PathVariable Long id) {
        Optional<SysAdmin> sysAdmin = sysAdminService.getSysAdminById(id);
        return sysAdmin.map(sa -> new ResponseEntity<>(sa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<SysAdmin> getSysAdminByEmail(@PathVariable String email) {
        SysAdmin sysAdmin = sysAdminService.getSysAdminByEmail(email);
        return sysAdmin != null ? new ResponseEntity<>(sysAdmin, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysAdmin> updateSysAdmin(@PathVariable Long id, @RequestBody SysAdmin sysAdminDetails) {
        try {
            SysAdmin updatedSysAdmin = sysAdminService.updateSysAdmin(id, sysAdminDetails);
            return new ResponseEntity<>(updatedSysAdmin, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSysAdmin(@PathVariable Long id) {
        sysAdminService.deleteSysAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
