package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.PessoaRepository;
import com.adria.hepldesk.Repository.TecnicoRepository;
import com.adria.hepldesk.domain.Pessoa;
import com.adria.hepldesk.domain.Tecnico;
import com.adria.hepldesk.dtos.TecnicoDTO;
import com.adria.hepldesk.services.exceptions.DataIntegrityViolationException;
import com.adria.hepldesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico>  objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com o id:"+id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(Tecnico tecnico) {
        validaPorCpfEEmail(tecnico);
        return repository.save(tecnico);
    }

    private void validaPorCpfEEmail(Tecnico tecnico) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnico.getCpf());
        if(obj.isPresent() && obj.get().getId() != tecnico.getId() ){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! " +
                    "Por favor,informar!");
        }
        obj = pessoaRepository.findByEmail(tecnico.getEmail());
        if(obj.isPresent() && obj.get().getId() != tecnico.getId() ){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema! " +
                    "Por favor,informar outro!");
        }
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico obj = findById(id);
        validaPorCpfEEmail(obj);
        obj = new Tecnico(objDto);
        return repository.save(obj);
    }
}
