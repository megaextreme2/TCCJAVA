package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServicosService {
    public Servicos salvarServicos(Servicos servicos);
    public List<Servicos> listarTodosServicos();
    public Servicos listarServicosPorId(Long id);
    public boolean deletarServicos(Long id);
    public Servicos atualizarServicos(Servicos servicos, Long id);
    public Servicos deletarLogicServicos(Servicos servicos, Long id);
    public Servicos listarServicosPorIdAtivos(Long id);
    public List<Servicos> listarTodosServicosAtivos();
}
