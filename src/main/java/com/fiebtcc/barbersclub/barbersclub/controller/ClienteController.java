package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import com.fiebtcc.barbersclub.barbersclub.model.Cliente;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeshopService;
import com.fiebtcc.barbersclub.barbersclub.services.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/cliente")
public class ClienteController {

    // criação do objeto de serviço
    final ClienteService clienteService ;

    // Injeção de Dependência
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarClientes(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarClientes(cliente));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosClientes(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteService.listarTodosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientesPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientesPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Cliente> deletarLogicClientes(@RequestBody Cliente cliente, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(clienteService.deletarLogicClientes(cliente, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarClientes(@RequestBody Cliente cliente, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.atualizarClientes(cliente, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarClientes(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.deletarClientes(Long.parseLong(id)));
    }
}


