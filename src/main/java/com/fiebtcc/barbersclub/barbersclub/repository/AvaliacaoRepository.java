package com.fiebtcc.barbersclub.barbersclub.repository;


import com.fiebtcc.barbersclub.barbersclub.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {
    @Query(value = "SELECT * FROM Avaliacao va WHERE va.codStatus='1'", nativeQuery = true)
    public List<Avaliacao> listarTodasAvaliacaoAtivas();

}
