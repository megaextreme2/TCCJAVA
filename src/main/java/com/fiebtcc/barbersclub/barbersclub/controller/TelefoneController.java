package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import com.fiebtcc.barbersclub.barbersclub.services.TelefoneService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/telefone")
public class TelefoneController {

    private final TelefoneService telefoneService;

    public TelefoneController(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @GetMapping("/telefone")
    public ResponseEntity<List<Telefone>> listarTodosTelefones() {
        return ResponseEntity.ok().body(telefoneService.listarTodosTelefones());
    }

    @PostMapping("/telefone")
    @Transactional
    public ResponseEntity<Telefone> salvarTelefone(@RequestBody Telefone telefone) {
        telefone.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/telefone").toUriString());
        return ResponseEntity.created(uri).body(telefoneService.salvarTelefone(telefone));
    }

    @GetMapping("/telefone/{id}")
    public ResponseEntity<Telefone> listarTelefonesPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(telefoneService.listarTelefonesPorId(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @DeleteMapping("/telefone/{id}")
    public ResponseEntity<Object> deletarTelefones(@PathVariable(value = "id") String id) {
        try {
            if (telefoneService.deletarTelefones(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Telefone com o id " + id + " excluida com sucesso");
            }

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão do Telefone com o id" + id);

    }

    @PutMapping("/telefone/{id}")
    @Transactional
    public ResponseEntity<Telefone> atualizarTelefones(@RequestBody Telefone telefone, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(telefoneService.atualizarTelefones(telefone, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/deletlogic/telefone/{id}")
    @Transactional
    public ResponseEntity<Telefone> deletarLogicTelefone(@RequestBody Telefone telefone, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(telefoneService.deletarLogicTelefone( telefone, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
