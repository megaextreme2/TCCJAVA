package com.fiebtcc.barbersclub.barbersclub.repository;

import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query(value = "SELECT * FROM Admin ad WHERE ad.codStatus='1'", nativeQuery = true)
    public List<Admin> listarTodosAdminsAtivos();

    @Query(value = "SELECT * FROM Admin ad WHERE ad.id=?1 AND ad.codStatus='1'", nativeQuery = true)
    public Admin listarAdminsPorIdAtivas(Long id);

    Admin findByEmail(String email); // Método para encontrar Admin pelo email

}
