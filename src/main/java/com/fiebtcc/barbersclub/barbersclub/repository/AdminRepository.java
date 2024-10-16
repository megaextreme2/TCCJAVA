package com.fiebtcc.barbersclub.barbersclub.repository;

import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository
        extends JpaRepository<Admin,Long> {
    Admin findAllById(long id);
}
