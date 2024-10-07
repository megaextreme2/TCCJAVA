package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {
    public Admin salvarAdmin(Admin admin);
    public List<Admin> listarTodasAdmin();
    public Admin listarAdminPorId(Long id);
    public boolean deletarAdmin(Long id);
    public Admin atualizarAdmin(Admin admin, Long id);
    public Admin deletarLogicAdmin(Admin admin, Long id);
    public Admin listarAdminsPorIdAtivos(Long id);
    public List<Admin> listarTodosAdminsAtivos();
}
