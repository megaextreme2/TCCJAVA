package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import com.fiebtcc.barbersclub.barbersclub.repository.ServicosRepository;
import com.fiebtcc.barbersclub.barbersclub.repository.TelefoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneServiceImpl implements TelefoneService{

    private final TelefoneRepository telefoneRepository;

    public TelefoneServiceImpl(TelefoneRepository telefoneRepository ) {
        this.telefoneRepository = telefoneRepository;
    }


    @Override
    public Telefone salvarTelefone(Telefone telefone) {
        if (!telefone.validarTelefone()) {
            throw new BadRequest(telefone.getMensagemErro());
        }
        return telefoneRepository.save(telefone);
    }

    @Override
    public List<Telefone> listarTodosTelefones() {
        return telefoneRepository.findAll();
    }

    @Override
    public Telefone listarTelefonesPorId(Long id) {
        try {
            return telefoneRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarTelefones(Long id) {
        if (telefoneRepository.existsById(id)) {
            telefoneRepository.deleteById(id);
        } else {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Telefone atualizarTelefones(Telefone telefone, Long id) {
        try {
            if (!telefone.validarTelefone()) {
                throw new BadRequest(telefone.getMensagemErro());
            }
            Telefone telefoneBd = telefoneRepository.findById(id).get();
            telefoneBd.setNumero(telefone.getNumero());
            return telefoneRepository.save(telefoneBd); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public Telefone deletarLogicTelefone(Telefone telefone, Long id) {
        try {
            if (!telefone.validarTelefone()){
                throw new BadRequest(telefone.getMensagemErro());
            }
            Telefone telefoneBd = telefoneRepository.findById(id).get();
            telefoneBd.setCodStatus(telefone.isCodStatus());
            return telefoneRepository.save(telefoneBd);
        }catch (Exception ex){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public List<Telefone> listarTodosTelefonesAtivos() {
        return telefoneRepository.listarTodosTelefonesAtivos();
    }

    @Override
    public Telefone listarTelefonesPorIdAtivos(Long id) {
        Telefone telefone = telefoneRepository.listarTelefonesPorIdAtivos(id);
        if(telefone == null){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return telefone;
    }
}
