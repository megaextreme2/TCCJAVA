package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import com.fiebtcc.barbersclub.barbersclub.services.ServicosService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/servicos")
public class ServicosController {

    // criação do objeto de serviço
    final ServicosService ServicosService ;

    // Injeção de Dependência
    public ServicosController(ServicosService ServicosService) {
        this.ServicosService = ServicosService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarServicos(@RequestBody Servicos servicos){
        return ResponseEntity.status(HttpStatus.CREATED).body(ServicosService.salvarServicos(servicos));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Servicos>> listarTodosServicos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServicosService.listarTodosServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicos> listarServicosPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(ServicosService.listarServicosPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Servicos> deletarLogicServicos(@RequestBody Servicos servicos, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(ServicosService.deletarLogicServicos(servicos, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarServicos(@RequestBody Servicos servicos, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServicosService.atualizarServicos(servicos, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarServicos(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServicosService.deletarServicos(Long.parseLong(id)));
    }
}
