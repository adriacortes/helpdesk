package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.TecnicoRepository;
import com.adria.hepldesk.domain.Tecnico;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico>  objeto = tecnicoRepository.findById(id);
        return objeto.orElse(null);
    }
}
