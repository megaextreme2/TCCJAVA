package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Avaliacao;
import com.fiebtcc.barbersclub.barbersclub.services.AvaliacaoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    // criação do objeto de serviço
    final AvaliacaoService avaliacaoService;

    // Injeção de Dependência
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarAvalicao(@RequestBody Avaliacao avaliacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.salvarAvaliacao(avaliacao));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodasAvaliacaoAtivas(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(avaliacaoService.listarTodasAvaliacaoAtivas());
    }

    @PutMapping
    public ResponseEntity<Object> atualizarAvalicao(@RequestBody Avaliacao avaliacao, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.atualizarAvalicao(avaliacao, Long.parseLong(id)));
    }
    @PutMapping("/delete")
    @Transactional
    public ResponseEntity<Avaliacao> deletarLogicAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(avaliacaoService.deletarLogicAvaliacao(avaliacao, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}


