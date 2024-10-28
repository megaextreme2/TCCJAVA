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
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/admin")
public class AdminController {

    // Criação do objeto de serviço
    final AdminService adminService;

    // Injeção de Dependência
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {
        Admin loggedAdmin = adminService.login(admin.getEmail(), admin.getSenha());
        if (loggedAdmin != null) {
            return ResponseEntity.ok(loggedAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // ROTA POST para salvar um administrador
    @PostMapping
    public ResponseEntity<Object> salvarAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.salvarAdmin(admin));
    }

    // ROTA GET para listar todos os administradores
    @GetMapping
    public ResponseEntity<List<Admin>> listarTodasAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.listarTodasAdmin());
    }

    // ROTA GET para listar um administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> listarAdminPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.listarAdminPorId(id));
    }

    // ROTA PUT para deletar logicamente um administrador
    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Admin> deletarLogicAdmin(@RequestBody Admin admin, @PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(adminService.deletarLogicAdmin(admin, id));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro.");
        }
    }

    // ROTA PUT para atualizar um administrador
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAdmin(@RequestBody Admin admin, @PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.atualizarAdmin(admin, id));
    }

    // ROTA DELETE para remover um administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAdmin(@PathVariable(value = "id") Long id) {
        adminService.deletarAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    // ROTA POST para redefinir a senha
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String novaSenha) {
        boolean success = adminService.redifinirSenha(email, novaSenha);
        if (success) {
            return ResponseEntity.ok("Senha redefinida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email não encontrado.");
        }
    }
}