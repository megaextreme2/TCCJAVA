package com.fiebtcc.barbersclub.barbersclub.services;



import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarbeshopService {
    public BarberShop salvarBarberShop(BarberShop barberShop);
    public List<BarberShop> listarTodosBarberShop();
    public BarberShop listarBarberShopPorId(Long id);
    public boolean deletarBarberShop(Long id);
    public BarberShop atualizarBarberShop(BarberShop barberShop, Long id);
    public BarberShop deletarLogicBarberShop(BarberShop barberShop, Long id);
    public BarberShop listarBarberShopPorIdAtivos(Long id);
    public List<BarberShop> listarTodosBarberShopAtivos();
}
