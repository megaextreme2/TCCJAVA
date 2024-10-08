package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.model.Telefone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TelefoneService {
    public Telefone salvarTelefone(Telefone telefone);
    public List<Telefone> listarTodosTelefones();
    public Telefone listarTelefonesPorId(Long id);
    public boolean deletarTelefones(Long id);
    public Telefone atualizarTelefones(Telefone telefone, Long id);
    public Telefone deletarLogicTelefone(Telefone telefone, Long id);
    public Telefone listarTelefonesPorIdAtivos(Long id);
    public List<Telefone> listarTodosTelefonesAtivos();
}
