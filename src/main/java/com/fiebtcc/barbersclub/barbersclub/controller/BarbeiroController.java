package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeiroService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/barbeiro")
public class BarbeiroController {

    // criação do objeto de serviço
    final BarbeiroService barbeiroService;

    // Injeção de Dependência
    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarBarbeiro(@RequestBody Barbeiro barbeiro){
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeiroService.salvarBarbeiro(barbeiro));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Barbeiro>> listarTodosBarbeiro(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(barbeiroService.listarTodosBarbeiro());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbeiro> listarBarbeiroPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(barbeiroService.listarBarbeiroPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Barbeiro> deletarLogicBarbeiro(@RequestBody Barbeiro barbeiro, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(barbeiroService.deletarLogicBarbeiro(barbeiro, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarBarbeiro(@RequestBody Barbeiro barbeiro, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(barbeiroService.atualizarBarbeiro(barbeiro, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarBarbeiro(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(barbeiroService.deletarBarbeiro(Long.parseLong(id)));
    }
}
