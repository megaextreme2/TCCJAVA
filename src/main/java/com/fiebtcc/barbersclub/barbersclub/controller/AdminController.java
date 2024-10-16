package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/admin")
public class AdminController {

    // criação do objeto de serviço
    final AdminService adminService;

    // Injeção de Dependência
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> saveAdmin(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(admin));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.findAll());
    }

    @PutMapping
    public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.update(admin));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAdmin(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.delete(admin));
    }
}


