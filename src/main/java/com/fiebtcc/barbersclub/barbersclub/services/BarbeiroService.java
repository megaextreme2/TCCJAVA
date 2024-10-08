package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.model.Barbeiro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarbeiroService {
    public Barbeiro salvarBarbeiro(Barbeiro barbeiro);
    public List<Barbeiro> listarTodosBarbeiro();
    public Barbeiro listarBarbeiroPorId(Long id);
    public boolean deletarBarbeiro(Long id);
    public Barbeiro atualizarBarbeiro(Barbeiro barbeiro, Long id);
    public Barbeiro deletarLogicBarbeiro(Barbeiro barbeiro, Long id);
    public Barbeiro listarBarbeirosPorIdAtivos(Long id);
    public List<Barbeiro> listarTodosBarbeirosAtivos();
}
