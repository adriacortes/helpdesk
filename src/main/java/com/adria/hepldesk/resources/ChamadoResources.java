package com.adria.hepldesk.resources;

import com.adria.hepldesk.domain.Chamado;
import com.adria.hepldesk.dtos.ChamadoDto;
import com.adria.hepldesk.services.ChamadoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResources {
    @Autowired
    private ChamadoService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return  ResponseEntity.ok().body(new ChamadoDto(obj));
    }
}
