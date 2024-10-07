package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeiroService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionario")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    @GetMapping("/barbeiro")
    public ResponseEntity<List<Barbeiro>> listarTodosbarbeiros() {
        return ResponseEntity.ok().body(barbeiroService.listarTodosBarbeiro());
    }

    @PostMapping("/barbeiro")
    @Transactional
    public ResponseEntity<Barbeiro> salvarBarbeiro(@RequestBody Barbeiro barbeiro) {
        barbeiro.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/barbeiro").toUriString());
        return ResponseEntity.created(uri).body(barbeiroService.salvarBarbeiro(barbeiro));
    }

    @GetMapping("/barbeiro/{id}")
    public ResponseEntity<Barbeiro> listarbarbeiroPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(barbeiroService.listarBarbeiroPorId(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @DeleteMapping("/barbeiro/{id}")
    public ResponseEntity<Object> deletarBarbeiro(@PathVariable(value = "id") String id) {
        try {
            if (barbeiroService.deletarBarbeiro(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Barbeiro com o id " + id + " excluida com sucesso");
            }

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da barbeiro com o id" + id);

    }

    @PutMapping("/barbeiro/{id}")
    @Transactional
    public ResponseEntity<Barbeiro> atualizarBarbeiro(@RequestBody Barbeiro barbeiro, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(barbeiroService.atualizarBarbeiro(barbeiro, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/deletlogic/barbeiro/{id}")
    @Transactional
    public ResponseEntity<Barbeiro> deletarLogicBarbeiro(@RequestBody Barbeiro barbeiro, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(barbeiroService.deletarLogicBarbeiro(barbeiro, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
