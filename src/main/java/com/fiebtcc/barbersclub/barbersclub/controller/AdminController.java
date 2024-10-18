package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Object> salvarAdmin(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.salvarAdmin(admin));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Admin>> listarTodasAdmin(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.listarTodasAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> listarAdminPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.listarAdminPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Admin> deletarLogicAdmin(@RequestBody Admin admin, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(adminService.deletarLogicAdmin(admin, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAdmin(@RequestBody Admin admin, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.atualizarAdmin(admin, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAdmin(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.deletarAdmin(Long.parseLong(id)));
    }
}


