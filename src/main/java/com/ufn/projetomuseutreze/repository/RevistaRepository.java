package com.ufn.projetomuseutreze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufn.projetomuseutreze.model.Revista;
import java.util.List;

public interface RevistaRepository extends JpaRepository<Revista, Long> {
    List<Revista> findByAtivoTrue();
}
