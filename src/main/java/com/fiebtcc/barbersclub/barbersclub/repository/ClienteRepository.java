package com.fiebtcc.barbersclub.barbersclub.repository;


import com.fiebtcc.barbersclub.barbersclub.model.Cliente;
import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "SELECT * FROM Cl cl WHERE cl.codStatus='1'", nativeQuery = true)
    public List<Cliente> listarTodosClientesAtivos();

    @Query(value = "SELECT * FROM Cl cl WHERE cl.id=?1 AND cl.codStatus='1'", nativeQuery = true)
    public Cliente listarClientesPorIdAtivos(Long id);
}
