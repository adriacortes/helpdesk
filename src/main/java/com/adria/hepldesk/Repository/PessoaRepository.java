package com.adria.hepldesk.Repository;

import com.adria.hepldesk.domain.Pessoa;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
