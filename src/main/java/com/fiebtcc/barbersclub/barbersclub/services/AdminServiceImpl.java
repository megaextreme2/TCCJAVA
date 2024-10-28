package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public Admin salvarAdmin(Admin admin) {
        if (!admin.validarAdmin()) {
            throw new BadRequest(admin.getMensagemErro());
        }
        admin.setCod_status(true); // Define cod_status como true ao salvar
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> listarTodasAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin listarAdminPorId(Long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new NotFound("Admin com o id " + id + " não encontrado"));
    }

    @Override
    public Admin login(String email, String senha) {
        Admin admin = adminRepository.findByEmail(email); // Busca o administrador pelo email
        if (admin != null && admin.getSenha().equals(senha)) {
            if (Boolean.TRUE.equals(admin.isCod_status())) { // Verifica se cod_status é true
                return admin; // Retorna o administrador se as credenciais estiverem corretas
            }
        }
        return null; // Retorna null se as credenciais estiverem incorretas ou cod_status não for true
    }

    @Override
    public boolean deletarAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        } else {
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
    }

    @Override
    public Admin atualizarAdmin(Admin admin, Long id) {
        if (!admin.validarAdmin()) {
            throw new BadRequest(admin.getMensagemErro());
        }

        Admin adminBd = listarAdminPorId(id); // Usa o método para buscar por ID
        adminBd.setNome(admin.getNome());
        adminBd.setSenha(admin.getSenha());
        adminBd.setRelatorio(admin.getRelatorio());
        return adminRepository.save(adminBd);
    }

    @Override
    public Admin deletarLogicAdmin(Admin admin, Long id) {
        if (!admin.validarAdmin()) {
            throw new BadRequest(admin.getMensagemErro());
        }

        Admin adminBD = listarAdminPorId(id); // Usa o método para buscar por ID
        adminBD.setCod_status(false); // Define como false para deletar logicamente
        return adminRepository.save(adminBD);
    }

    @Override
    public List<Admin> listarTodosAdminsAtivos() {
        return adminRepository.listarTodosAdminsAtivos();
    }

    @Override
    public Admin listarAdminsPorIdAtivos(Long id) {
        Admin admin = adminRepository.listarAdminsPorIdAtivas(id);
        if (admin == null) {
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
        return admin;
    }
    public boolean redifinirSenha(String email, String novaSenha) {
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null) {
            admin.setSenha(novaSenha); // Atualiza a senha do administrador
            adminRepository.save(admin); // Salva as alterações
            return true; // Senha redefinida com sucesso
        }

        return false; // Email não encontrado
    }
}