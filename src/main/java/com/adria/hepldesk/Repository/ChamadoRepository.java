package com.adria.hepldesk.Repository;

import com.adria.hepldesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
