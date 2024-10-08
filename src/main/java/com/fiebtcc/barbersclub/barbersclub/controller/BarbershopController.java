package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeiroService;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeshopService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/barbershop")
public class BarbershopController {

    private final BarbeshopService barberShopService;

    public BarbershopController(BarbeshopService barberShopService) {
        this.barberShopService = barberShopService;
    }

    @GetMapping("/barbershop")
    public ResponseEntity<List<BarberShop>> listarTodosBarberShop() {
        return ResponseEntity.ok().body(barberShopService.listarTodosBarberShop());
    }

    @PostMapping("/barbershop")
    @Transactional
    public ResponseEntity<BarberShop> salvarBarberShop(@RequestBody BarberShop barberShop) {
        barberShop.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/barbershop").toUriString());
        return ResponseEntity.created(uri).body(barberShopService.salvarBarberShop(barberShop));
    }

    @GetMapping("/barbershop/{id}")
    public ResponseEntity<BarberShop> listarBarberShopPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(barberShopService.listarBarberShopPorId(Long.parseLong(id)));
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @DeleteMapping("/barbershop/{id}")
    public ResponseEntity<Object> deletarBarberShop(@PathVariable(value = "id") String id) {
        try {
            if (barberShopService.deletarBarberShop(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Barbearia com o id " + id + " excluida com sucesso");
            }

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da barbeiro com o id" + id);

    }

    @PutMapping("/barbershop/{id}")
    @Transactional
    public ResponseEntity<BarberShop> atualizarBarberShop(@RequestBody BarberShop barberShop, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(barberShopService.atualizarBarberShop(barberShop, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/deletlogic/barbershop/{id}")
    @Transactional
    public ResponseEntity<BarberShop> deletarLogicBarberShop(@RequestBody BarberShop barberShop, @PathVariable(value = "id") String id) {

        try {
            return ResponseEntity.ok().body(barberShopService.deletarLogicBarberShop(barberShop, Long.parseLong(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
