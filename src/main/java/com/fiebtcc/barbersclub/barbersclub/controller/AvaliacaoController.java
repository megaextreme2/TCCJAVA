package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.model.Avaliacao;
import com.fiebtcc.barbersclub.barbersclub.services.AdminService;
import com.fiebtcc.barbersclub.barbersclub.services.AvaliacaoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }
    @GetMapping("/avaliacao")
    public ResponseEntity<List<Avaliacao>> listarTodasAvaliacaoAtivas(){
        return ResponseEntity.ok().body(avaliacaoService.listarTodasAvaliacaoAtivas());
    }
    @PostMapping("/avaliacao")
    @Transactional
    public ResponseEntity<Avaliacao> salvarAvaliacao(@RequestBody Avaliacao avaliacao){
        avaliacao.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/avaliacao").toUriString());
        return ResponseEntity.created(uri).body(avaliacaoService.salvarAvaliacao(avaliacao));
    }


    @PutMapping("/avaliacao/{id}")
    @Transactional
    public ResponseEntity<Avaliacao> atualizarAvalicao(@RequestBody Avaliacao avaliacao, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(avaliacaoService.atualizarAvalicao(avaliacao , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/avaliacao/{id}")
    @Transactional
    public ResponseEntity<Avaliacao> deletarLogicAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(avaliacaoService.deletarLogicAvaliacao(avaliacao, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}


