package com.adria.hepldesk.Repository;

import com.adria.hepldesk.domain.Cliente;
import com.adria.hepldesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
