package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.repository.AdminRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {


    final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    // Método SELECT * FROM PRODUTO
    public List<Admin> findAll(){
        List<Admin> listarAdmin = adminRepository.findAll();
        return listarAdmin;
    }

    public Admin findAllById(long id){
        Admin adminEncontrado = adminRepository.findAllById(id);
        return adminEncontrado;
    }

    @Transactional
    public Admin update(Admin admin) {
        Admin adminEncontrado = adminRepository.findAllById(admin.getId());
        if(adminEncontrado.getId() > 0)
            return adminRepository.save(admin);
        else
            return new Admin();
    }

    @Transactional
    public boolean delete(Admin admin) {
        boolean sucesso = false;
        Admin adminEncontrado = adminRepository.findAllById(admin.getId());
        if(adminEncontrado.getId() > 0) {
            adminRepository.deleteById(adminEncontrado.getId());
            sucesso = true;
        }

        return sucesso;
    }
}
