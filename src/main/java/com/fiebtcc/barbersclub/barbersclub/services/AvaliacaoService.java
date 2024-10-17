package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.model.Avaliacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AvaliacaoService {
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao);
    public Avaliacao atualizarAvalicao(Avaliacao avaliacao, Long id);
    public List<Avaliacao> listarTodasAvaliacaoAtivas();
    public Avaliacao deletarLogicAvaliacao(Avaliacao avaliacao, Long id);

}
