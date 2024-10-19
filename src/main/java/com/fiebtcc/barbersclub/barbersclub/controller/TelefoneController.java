package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import com.fiebtcc.barbersclub.barbersclub.services.TelefoneService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/telefone")
public class TelefoneController {

    // criação do objeto de serviço
    final TelefoneService telefoneService ;

    // Injeção de Dependência
    public TelefoneController(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarTelefone(@RequestBody Telefone telefone){
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneService.salvarTelefone(telefone));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Telefone>> listarTodosTelefones(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(telefoneService.listarTodosTelefones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> listarTelefonesPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.listarTelefonesPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Telefone> deletarLogicTelefone(@RequestBody Telefone telefone, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(telefoneService.deletarLogicTelefone(telefone, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTelefones(@RequestBody Telefone telefone, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(telefoneService.atualizarTelefones(telefone, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTelefones(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(telefoneService.deletarTelefones(Long.parseLong(id)));
    }
}
