package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.ChamadoRepository;
import com.adria.hepldesk.domain.Chamado;
import com.adria.hepldesk.domain.Cliente;
import com.adria.hepldesk.domain.Tecnico;
import com.adria.hepldesk.dtos.ChamadoDto;
import com.adria.hepldesk.enums.Prioridade;
import com.adria.hepldesk.enums.Status;
import com.adria.hepldesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com o id: "+id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDto objDto) {
        return repository.save(newChamado(objDto));
    }

    private Chamado newChamado(ChamadoDto obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado  chamado = new Chamado();

        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());

        return chamado;

    }
}
