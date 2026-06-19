package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.Categoria;
import com.ufn.projetomuseutreze.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }

        return categoria.get();
    }

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void ativarDesativar(Long id){
        Categoria categoria = buscarPorId(id);
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}