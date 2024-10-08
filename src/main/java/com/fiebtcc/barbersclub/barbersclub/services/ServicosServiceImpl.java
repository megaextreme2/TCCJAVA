package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import com.fiebtcc.barbersclub.barbersclub.repository.ServicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicosServiceImpl implements ServicosService{

    private final ServicosRepository servicosRepository;

    public ServicosServiceImpl(ServicosRepository servicosRepository ) {
        this.servicosRepository = servicosRepository;
    }

    @Override
    @Transactional
    public Servicos salvarServicos(Servicos servicos) {
        if (!servicos.validarServicos()) {
            throw new BadRequest(servicos.getMensagemErro());
        }
        return servicosRepository.save(servicos);
    }

    @Override
    public List<Servicos> listarTodosServicos() {
        return servicosRepository.findAll();
    }

    @Override
    public Servicos listarServicosPorId(Long id) {
        try {
            return servicosRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Serviços com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarServicos(Long id) {
        if (servicosRepository.existsById(id)) {
            servicosRepository.deleteById(id);
        } else {
            throw new NotFound("Serviços com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Servicos atualizarServicos(Servicos servicos, Long id) {
        try {
            if (!servicos.validarServicos()) {
                Servicos servicosBD = servicosRepository.findById(id).get();
                servicosBD.setTipo(servicos.getTipo());
                servicosBD.setDescricao(servicos.getDescricao());
                servicosBD.setNome(servicos.getNome());
                return servicosRepository.save(servicosBD);
            } else {
                throw new BadRequest(servicos.getMensagemErro());
            }
        }catch (Exception ex){
            throw new NotFound("Serviços com o id " + id + " não encontrado");
        }
    }

    @Override
    public Servicos deletarLogicServicos(Servicos servicos, Long id) {
        try {
            if (!servicos.validarServicos()){
                throw new BadRequest(servicos.getMensagemErro());
            }
            Servicos servicosBD = servicosRepository.findById(id).get();
            servicosBD.setCodStatus(servicos.isCodStatus());
            return servicosRepository.save(servicosBD);
        }catch (Exception ex){
            throw new NotFound("Serviços com o id " + id + " não encontrado");
        }
    }

    @Override
    public Servicos listarServicosPorIdAtivos(Long id) {
            Servicos servicos = servicosRepository.listarServicosPorIdAtivos(id);
            if(servicos == null){
                throw new NotFound("Serviços com o id " + id + " não encontrado");
            }
            return servicos;
        }

    @Override
    public List<Servicos> listarTodosServicosAtivos() {
        return servicosRepository.listarTodosServicosAtivos();
    }


}
