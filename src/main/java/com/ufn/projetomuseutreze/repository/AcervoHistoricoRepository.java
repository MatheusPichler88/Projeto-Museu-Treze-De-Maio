package com.ufn.projetomuseutreze.repository;

import com.ufn.projetomuseutreze.model.AcervoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AcervoHistoricoRepository extends JpaRepository<AcervoHistorico, Long> {
    List<AcervoHistorico> findByAtivoTrue();
}
