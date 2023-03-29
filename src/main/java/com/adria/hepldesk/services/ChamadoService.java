package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.ChamadoRepository;
import com.adria.hepldesk.domain.Chamado;
import com.adria.hepldesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com o id: "+id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }
}
