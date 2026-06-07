package com.ufn.projetomuseutreze.repository;

import com.ufn.projetomuseutreze.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAtivoTrue();
}
