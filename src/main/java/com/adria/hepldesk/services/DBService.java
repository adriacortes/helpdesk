package com.adria.hepldesk.services;

import com.adria.hepldesk.Repository.ChamadoRepository;
import com.adria.hepldesk.Repository.ClienteRepository;
import com.adria.hepldesk.Repository.PessoaRepository;
import com.adria.hepldesk.Repository.TecnicoRepository;
import com.adria.hepldesk.domain.Chamado;
import com.adria.hepldesk.domain.Cliente;
import com.adria.hepldesk.domain.Tecnico;
import com.adria.hepldesk.enums.Perfil;
import com.adria.hepldesk.enums.Prioridade;
import com.adria.hepldesk.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public void instanciaDB(){
        Tecnico tecnico = new Tecnico(null,"Adria Aline Castro",
                "03312168171","adria.aline@gmail.com","123");
        tecnico.addPerfis(Perfil.ADMIN);

        Cliente cliente = new Cliente(null,"Diógenes Rafael Cortes",
                "03407025165","diogerafael@gmail.com","123");

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
                "Dúvidas em entrada de mercadoria","Como lançar NFe compra",tecnico,cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(chamado));
    }
}
