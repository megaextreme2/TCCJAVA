package com.fiebtcc.barbersclub.barbersclub.repository;


import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarberShopRepository extends JpaRepository<BarberShop,Long> {
    @Query(value = "SELECT * FROM Barbeshop bs WHERE bs.codStatus='1'", nativeQuery = true)
    public List<BarberShop> listarTodasBarbeariasAtivos();

    @Query(value = "SELECT * FROM Barbershop bs WHERE bs.id=?1 AND bs.codStatus='1'", nativeQuery = true)
    public BarberShop listarBarberShopPorIdAtivos(Long id);
}
