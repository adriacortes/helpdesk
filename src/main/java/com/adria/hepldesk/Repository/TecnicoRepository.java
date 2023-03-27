package com.adria.hepldesk.Repository;

import com.adria.hepldesk.domain.Pessoa;
import com.adria.hepldesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
