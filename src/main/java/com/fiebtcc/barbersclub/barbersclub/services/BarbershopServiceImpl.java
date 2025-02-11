package com.fiebtcc.barbersclub.barbersclub.services;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.exceptions.NotFound;
import com.fiebtcc.barbersclub.barbersclub.model.BarberShop;
import com.fiebtcc.barbersclub.barbersclub.repository.BarberShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BarbershopServiceImpl implements BarbeshopService{
    private final BarberShopRepository barberShopRepository;

    public BarbershopServiceImpl(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public BarberShop salvarBarberShop(BarberShop barberShop) {
        if (!barberShop.validarBarberShop()) {
            barberShop.setCod_status(true);
            throw new BadRequest(barberShop.getMensagemErro());
        }
        return barberShopRepository.save(barberShop);
    }

    @Override
    public List<BarberShop> listarTodosBarberShop() {
        return barberShopRepository.findAll();
    }

    @Override
    public BarberShop listarBarberShopPorId(Long id) {
        try {
            return barberShopRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Barbearia com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarBarberShop(Long id) {
        if (barberShopRepository.existsById(id)) {
            barberShopRepository.deleteById(id);
        } else {
            throw new NotFound("Barbearia com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public BarberShop atualizarBarberShop(BarberShop barberShop, Long id) {
        try {
            if (!barberShop.validarBarberShop()) {
                BarberShop barberShopBD = barberShopRepository.findById(id).get();
                barberShopBD.setNome(barberShop.getNome());
                barberShopBD.setQtd_barbeiro(barberShop.getQtd_barbeiro());
                barberShopBD.setCod_status(true);
                return barberShopRepository.save(barberShopBD);
            } else {
                throw new BadRequest(barberShop.getMensagemErro());
            }
        }catch (Exception ex){
            throw new NotFound("Barbearia com o id " + id + " não encontrado");
        }
    }

    @Override
    public BarberShop deletarLogicBarberShop(BarberShop barberShop, Long id) {
        try {
            if (!barberShop.validarBarberShop()){
                throw new BadRequest(barberShop.getMensagemErro());
            }
            BarberShop barberShopBD = barberShopRepository.findById(id).get();
            barberShopBD.setCod_status(barberShop.isCod_status());
            return barberShopRepository.save(barberShopBD);
        }catch (Exception ex){
            throw new NotFound("Barbearia com o id " + id + " não encontrado");
        }
    }

    @Override
    public BarberShop listarBarberShopPorIdAtivos(Long id) {
        BarberShop barberShop = barberShopRepository.listarBarberShopPorIdAtivos(id);
        if(barberShop == null){
            throw new NotFound("BarBarbeariabeiro com o id " + id + " não encontrado");
        }
        return barberShop;
    }

    @Override
    public List<BarberShop> listarTodosBarberShopAtivos() {
        return barberShopRepository.listarTodasBarbeariasAtivos();
    }
}
