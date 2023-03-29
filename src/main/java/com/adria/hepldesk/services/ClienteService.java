package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.PessoaRepository;
import com.adria.hepldesk.Repository.ClienteRepository;
import com.adria.hepldesk.domain.Pessoa;
import com.adria.hepldesk.domain.Cliente;
import com.adria.hepldesk.dtos.ClienteDTO;
import com.adria.hepldesk.services.exceptions.DataIntegrityViolationException;
import com.adria.hepldesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente>  objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com o id:"+id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(Cliente Cliente) {
        validaPorCpfEEmail(Cliente);
        return repository.save(Cliente);
    }
    public Cliente update(Integer id, ClienteDTO objDto) {
        objDto.setId(id);
        Cliente obj = findById(id);
        validaPorCpfEEmail(obj);
        obj = new Cliente(objDto);
        return repository.save(obj);
    }
    public void delete(Integer id) {

        Cliente Cliente = findById(id);
        verificaSeClienteTemOrdemDeServico(Cliente);
        repository.delete(Cliente);

    }
    private static void verificaSeClienteTemOrdemDeServico(Cliente Cliente) {
        if(Cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O cliente possui Ordens de serviço e não pode ser deletado!");
        }
    }
    private void validaPorCpfEEmail(Cliente Cliente) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(Cliente.getCpf());
        if(obj.isPresent() && obj.get().getId() != Cliente.getId() ){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! " +
                    "Por favor,informar outro!");
        }
        obj = pessoaRepository.findByEmail(Cliente.getEmail());
        if(obj.isPresent() && obj.get().getId() != Cliente.getId() ){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema! " +
                    "Por favor,informar outro!");
        }
    }
}
