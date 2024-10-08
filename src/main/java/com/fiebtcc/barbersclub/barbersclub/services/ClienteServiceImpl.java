package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Cliente;
import com.fiebtcc.barbersclub.barbersclub.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository ) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente salvarClientes(Cliente cliente) {
        if (!cliente.validarCliente()) {
            throw new BadRequest(cliente.getMensagemErro());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente listarClientesPorId(Long id) {
        try {
            return clienteRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarClientes(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Cliente atualizarClientes(Cliente cliente, Long id) {
        try {
            if (!cliente.validarCliente()) {
                throw new BadRequest(cliente.getMensagemErro());
            }
            Cliente clienteBD = clienteRepository.findById(id).get();
            clienteBD.setNome(cliente.getNome());
            return clienteRepository.save(clienteBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public Cliente deletarLogicClientes(Cliente cliente, Long id) {
        try {
            if (!cliente.validarCliente()){
                throw new BadRequest(cliente.getMensagemErro());
            }
            Cliente clienteBD = clienteRepository.findById(id).get();
            clienteBD.setCodStatus(cliente.isCodStatus());
            return clienteRepository.save(clienteBD);
        }catch (Exception ex){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public Cliente listarClientesPorIdAtivos(Long id) {
        Cliente cliente = clienteRepository.listarClientesPorIdAtivos(id);
        if(cliente == null){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarTodosClientesAtivos() {
        return clienteRepository.listarTodosClientesAtivos();
    }
}
