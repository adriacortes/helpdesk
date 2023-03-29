package com.adria.hepldesk.resources;

import com.adria.hepldesk.domain.Cliente;
import com.adria.hepldesk.dtos.ClienteDTO;
import com.adria.hepldesk.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listaCliente = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaCliente);

    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDto){
        Cliente cliente = new Cliente(clienteDto);
        cliente.setId(null);
        Cliente cliente1 = clienteService.create(cliente);
        ClienteDTO novoCliente = new ClienteDTO(cliente1);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(novoCliente.getId())
                  .toUri();
        return ResponseEntity.created(uri).build(); /*Retornar o ID do objeto criado - Boa pratica*/
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDto){
        Cliente obj = this.clienteService.update(id,objDto);
        return ResponseEntity.ok().body(new ClienteDTO(obj));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
