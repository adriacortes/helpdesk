package com.adria.hepldesk.resources;

import com.adria.hepldesk.domain.Pessoa;
import com.adria.hepldesk.domain.Tecnico;
import com.adria.hepldesk.dtos.TecnicoDTO;
import com.adria.hepldesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = this.tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listaTecnico = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaTecnico);

    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        tecnico.setId(null);
        Tecnico tecnico1 = tecnicoService.create(tecnico);
        TecnicoDTO novoTecnico = new TecnicoDTO(tecnico1);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(novoTecnico.getId())
                  .toUri();
        return ResponseEntity.created(uri).build(); /*Retornar o ID do objeto criado - Boa pratica*/
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDto){
        Tecnico obj = this.tecnicoService.update(id,objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        this.tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
