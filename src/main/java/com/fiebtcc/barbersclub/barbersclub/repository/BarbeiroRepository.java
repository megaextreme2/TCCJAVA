package com.fiebtcc.barbersclub.barbersclub.repository;

import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro,Long> {
    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.codStatus='1'", nativeQuery = true)
    public List<Barbeiro> listarTodosBarbeirosAtivos();

    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.id=?1 AND ba.codStatus='1'", nativeQuery = true)
    public Barbeiro listarBarbeirosPorIdAtivos(Long id);
}
