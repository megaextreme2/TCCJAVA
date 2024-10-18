package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import com.fiebtcc.barbersclub.barbersclub.services.BarbeshopService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/barbershop")
public class BarberShopController {

    // criação do objeto de serviço
    final BarbeshopService barbeshopService ;

    // Injeção de Dependência
    public BarberShopController(BarbeshopService barbeshopService) {
        this.barbeshopService = barbeshopService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarBarberShop(@RequestBody BarberShop barberShop){
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeshopService.salvarBarberShop(barberShop));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<BarberShop>> listarTodosBarberShop(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(barbeshopService.listarTodosBarberShop());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberShop> listarBarberShopPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(barbeshopService.listarBarberShopPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<BarberShop> deletarLogicBarberShop(@RequestBody BarberShop barberShop, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(barbeshopService.deletarLogicBarberShop(barberShop, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarBarberShop(@RequestBody BarberShop barberShop, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(barbeshopService.atualizarBarberShop(barberShop, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarBarberShop(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(barbeshopService.deletarBarberShop(Long.parseLong(id)));
    }
}
