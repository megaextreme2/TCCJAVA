package com.fiebtcc.barbersclub.barbersclub.repository;


import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
    @Query(value = "SELECT * FROM Telefone te WHERE te.codStatus='1'", nativeQuery = true)
    public List<Telefone> listarTodosTelefonesAtivos();

    @Query(value = "SELECT * FROM Telefone te WHERE te.id=?1 AND te.codStatus='1'", nativeQuery = true)
    public Telefone listarTelefonesPorIdAtivos(Long id);
}
