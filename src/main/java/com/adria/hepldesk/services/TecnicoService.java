package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.TecnicoRepository;
import com.adria.hepldesk.domain.Tecnico;
import com.adria.hepldesk.dtos.TecnicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico>  objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com o id:"+id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(Tecnico Tecnico) {
        return repository.save(Tecnico);
    }
}
