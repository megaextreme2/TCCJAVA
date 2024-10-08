package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import com.fiebtcc.barbersclub.barbersclub.repository.BarbeiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BarbeiroServiceImpl implements BarbeiroService{

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroServiceImpl(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    @Override
    @Transactional
    public Barbeiro salvarBarbeiro(Barbeiro barbeiro) {
        if (!barbeiro.validarBarbeiro()) {
            throw new BadRequest(barbeiro.getMensagemErro());
        }
        return barbeiroRepository.save(barbeiro);
    }

    @Override
    public List<Barbeiro> listarTodosBarbeiro() {
        return barbeiroRepository.findAll();
    }

    @Override
    public Barbeiro listarBarbeiroPorId(Long id) {
        try {
            return barbeiroRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Barbeiro com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarBarbeiro(Long id) {
        if (barbeiroRepository.existsById(id)) {
            barbeiroRepository.deleteById(id);
        } else {
            throw new NotFound("Barbeiro com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Barbeiro atualizarBarbeiro(Barbeiro barbeiro, Long id) {
        try {
            if (!barbeiro.validarBarbeiro()) {
                Barbeiro barbeiroBD = barbeiroRepository.findById(id).get();
                barbeiroBD.setCodStatus(barbeiro.isCodStatus());
                return barbeiroRepository.save(barbeiroBD);
            } else {
                throw new BadRequest(barbeiro.getMensagemErro());
            }
        }catch (Exception ex){
            throw new NotFound("Barbeiro com o id " + id + " não encontrado");
        }
    }

    @Override
    public Barbeiro deletarLogicBarbeiro(Barbeiro barbeiro, Long id) {
        try {
            if (!barbeiro.validarBarbeiro()){
                throw new BadRequest(barbeiro.getMensagemErro());
            }
            Barbeiro barbeiroBD = barbeiroRepository.findById(id).get();
            barbeiroBD.setCodStatus(barbeiro.isCodStatus());
            return barbeiroRepository.save(barbeiroBD);
        }catch (Exception ex){
            throw new NotFound("Barbeiro com o id " + id + " não encontrado");
        }
    }

    @Override
    public Barbeiro listarBarbeirosPorIdAtivos(Long id) {
            Barbeiro barbeiro = barbeiroRepository.listarBarbeirosPorIdAtivos(id);
            if(barbeiro == null){
                throw new NotFound("Barbeiro com o id " + id + " não encontrado");
            }
            return barbeiro;
        }

    @Override
    public List<Barbeiro> listarTodosBarbeirosAtivos() {
        return barbeiroRepository.listarTodosBarbeirosAtivos();
    }


}
