package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Avaliacao;

import com.fiebtcc.barbersclub.barbersclub.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AvaliacaoServiceImpl  implements AvaliacaoService{
    private final AvaliacaoRepository avaliacaoRepository;
    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        if (!avaliacao.validarAvaliacao()) {
            throw new BadRequest(avaliacao.getMensagemErro());
        }
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public Avaliacao atualizarAvalicao(Avaliacao avaliacao, Long id) {
        try {
            if (!avaliacao.validarAvaliacao()) {
                Avaliacao avaliacaoBD = avaliacaoRepository.findById(id).get();
                avaliacaoBD.setMedia(avaliacao.getMedia());
                avaliacaoBD.setQuantiA(avaliacao.getQuantiA());
                return avaliacaoRepository.save(avaliacaoBD);
            } else {
                throw new BadRequest(avaliacao.getMensagemErro());
            }
        }catch (Exception ex){
            throw new NotFound("Avaliação com o id " + id + " não encontrado");
        }
    }

    public Avaliacao deletarLogicAvaliacao(Avaliacao avaliacao, Long id) {
        try {
            if (!avaliacao.validarAvaliacao()) {
                throw new BadRequest(avaliacao.getMensagemErro());
            }
            Avaliacao avaliacaoBD = avaliacaoRepository.findById(id).get();
            avaliacaoBD.setCod_status(avaliacao.isCod_status());
            return avaliacaoRepository.save(avaliacaoBD);
        } catch (Exception ex) {
            throw new NotFound("Barbeiro com o id " + id + " não encontrado");
        }
    }

    @Override
    public List<Avaliacao> listarTodasAvaliacaoAtivas() {
        return avaliacaoRepository.listarTodasAvaliacaoAtivas();
    }

}
