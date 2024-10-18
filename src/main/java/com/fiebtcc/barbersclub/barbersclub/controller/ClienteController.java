package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Cliente;
import com.fiebtcc.barbersclub.barbersclub.services.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> listarTodosClientes(){
        return ResponseEntity.ok().body(clienteService.listarTodosClientes());
    }
    @PostMapping("/cliente")
    @Transactional
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        cliente.setCod_status(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/cliente").toUriString());
        return ResponseEntity.created(uri).body(clienteService.salvarClientes(cliente));
    }
    @GetMapping("/cliente/{id}")
    public  ResponseEntity<Cliente> listarClientePorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(clienteService.listarClientesPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Object> deletarAdmin(@PathVariable(value = "id") String id){
        try{
            if(clienteService.deletarClientes(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Cliente com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/cliente/{id}")
    @Transactional
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(clienteService.atualizarClientes(cliente , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/cliente/{id}")
    @Transactional
    public ResponseEntity<Cliente> deletarLogicCliente(@RequestBody Cliente cliente, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(clienteService.deletarLogicClientes(cliente, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}


