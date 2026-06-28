package com.ufn.projetomuseutreze.repository;

import com.ufn.projetomuseutreze.model.ItemAcervo;
import com.ufn.projetomuseutreze.model.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemAcervoRepository extends JpaRepository<ItemAcervo, Long> {
    Optional<ItemAcervo> findByCodigoPatrimonio(String codigoPatrimonio);
    List<ItemAcervo> findByAtivo(Boolean ativo);
    long countByTipoItem(TipoItem tipoItem);
    List<ItemAcervo> findByAtivoAndTituloContainingIgnoreCase(Boolean ativo, String titulo);
}