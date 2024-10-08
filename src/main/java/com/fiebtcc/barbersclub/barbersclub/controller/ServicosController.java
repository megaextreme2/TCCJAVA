package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import com.fiebtcc.barbersclub.barbersclub.services.ServicosService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionario")
public class ServicosController {

    private final ServicosService servicosService;

    public ServicosController(ServicosService servicosService) {
        this.servicosService = servicosService;
    }

    @GetMapping("/servicos")
    public ResponseEntity<List<Servicos>> listarTodosServicos() {
        return ResponseEntity.ok().body(servicosService.listarTodosServicos());
    }

    @PostMapping("/servicos")
    @Transactional
    public ResponseEntity<Servicos> salvarServicos(@RequestBody Servicos servicos) {
        servicos.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/servicos").toUriString());
        return ResponseEntity.created(uri).body(servicosService.salvarServicos(servicos));
    }

    @GetMapping("/servicos/{id}")
    public ResponseEntity<Servicos> listarServicosPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(servicosService.listarServicosPorId(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @DeleteMapping("/servicos/{id}")
    public ResponseEntity<Object> deletarServicos(@PathVariable(value = "id") String id) {
        try {
            if (servicosService.deletarServicos(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Serviço com o id " + id + " excluida com sucesso");
            }

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da serviço com o id" + id);

    }

    @PutMapping("/servicos/{id}")
    @Transactional
    public ResponseEntity<Servicos> atualizarServicos(@RequestBody Servicos servicos, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(servicosService.atualizarServicos(servicos, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/deletlogic/servicos/{id}")
    @Transactional
    public ResponseEntity<Servicos> deletarlogicServicos(@RequestBody Servicos servicos, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(servicosService.deletarLogicServicos(servicos, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
