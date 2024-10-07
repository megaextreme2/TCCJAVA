package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

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
            return adminRepository.save(admin);

    }

    @Override
    public List<Admin> listarTodasAdmin() {

        return adminRepository.findAll();
    }

    @Override
    public Admin listarAdminPorId(Long id) {
        try {
            return adminRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarAdmin(Long id) {

        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else {
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Admin atualizarAdmin(Admin admin, Long id) {

        try {
            if (!admin.validarAdmin()) {
                throw new BadRequest(admin.getMensagemErro());
            }
            Admin adminBd = adminRepository.findById(id).get();
            adminBd.setNome(admin.getNome());
            return adminRepository.save(adminBd); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
    }


    @Override
    public Admin deletarLogicAdmin(Admin admin, Long id){
        try {
            if (!admin.validarAdmin()){
                throw new BadRequest(admin.getMensagemErro());
            }
            Admin adminBD = adminRepository.findById(id).get();
            adminBD.setCodStatus(admin.isCodStatus());
            return adminRepository.save(adminBD);
        }catch (Exception ex){
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
    }

    @Override
    public List<Admin> listarTodosAdminsAtivos() {
        return adminRepository.listarTodosAdminsAtivos();
    }


    @Override
    public Admin listarAdminsPorIdAtivos(Long id) {
        Admin admin = adminRepository.listarAdminsPorIdAtivas(id);
        if(admin == null){
            throw new NotFound("Admin com o id " + id + " não encontrado");
        }
        return admin;
    }
}
