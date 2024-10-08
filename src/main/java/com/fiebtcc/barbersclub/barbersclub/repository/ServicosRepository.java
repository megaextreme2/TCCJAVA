package com.fiebtcc.barbersclub.barbersclub.repository;

import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicosRepository extends JpaRepository<Servicos,Long> {
    @Query(value = "SELECT * FROM Servicos se WHERE se.codStatus='1'", nativeQuery = true)
    public List<Servicos> listarTodosServicosAtivos();

    @Query(value = "SELECT * FROM Servicos se WHERE se.id=?1 AND se.codStatus='1'", nativeQuery = true)
    public Servicos listarServicosPorIdAtivos(Long id);
}
