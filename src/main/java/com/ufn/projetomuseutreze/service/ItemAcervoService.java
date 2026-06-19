package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.ItemAcervo;
import com.ufn.projetomuseutreze.repository.ItemAcervoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemAcervoService {

    private final ItemAcervoRepository itemAcervoRepository;

    public ItemAcervoService(ItemAcervoRepository itemAcervoRepository) {
        this.itemAcervoRepository = itemAcervoRepository;
    }

    public List<ItemAcervo> listarTodos() {
        return itemAcervoRepository.findAll();
    }

    public ItemAcervo buscarPorId(Long id) {
        Optional<ItemAcervo> item = itemAcervoRepository.findById(id);

        if (item.isEmpty()) {
            throw new RuntimeException("Item não encontrado com o ID: " + id);
        }

        return item.get();
    }

    public ItemAcervo salvar(ItemAcervo item) {
        return itemAcervoRepository.save(item);
    }

    public void ativarDesativar(Long id) {
        ItemAcervo item = buscarPorId(id);
        item.setAtivo(false);
        itemAcervoRepository.save(item);
    }
}