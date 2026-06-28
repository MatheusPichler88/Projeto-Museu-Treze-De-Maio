package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.Categoria;
import com.ufn.projetomuseutreze.model.ItemAcervo;
import com.ufn.projetomuseutreze.model.TipoItem;
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
        return itemAcervoRepository.findByAtivo(true);
    }

    public ItemAcervo buscarPorId(Long id) {
        Optional<ItemAcervo> item = itemAcervoRepository.findById(id);

        if (item.isEmpty()) {
            throw new RuntimeException("Item não encontrado com o ID: " + id);
        }

        return item.get();
    }

    public ItemAcervo salvar(ItemAcervo item) {
        if (item.getId() == null) {
            item.setCodigoPatrimonio("TEMP");
            item = itemAcervoRepository.save(item);
            String codigo = String.format("%s%03d", item.getTipoItem().getPrefixo(), item.getId());
            item.setCodigoPatrimonio(codigo);
        }
        return itemAcervoRepository.save(item);
    }

    public void ativarDesativar(Long id) {
        ItemAcervo item = buscarPorId(id);
        item.setAtivo(!item.getAtivo());
        itemAcervoRepository.save(item);
    }

    private String gerarCodigoPatrimonio(TipoItem tipoItem) {
        long total = itemAcervoRepository.countByTipoItem(tipoItem);
        long proximoNumero = total + 1;
        return String.format("%s%03d", tipoItem.getPrefixo(), proximoNumero);
    }
    public List<ItemAcervo> buscarPorNome(String nome) {
        return itemAcervoRepository.findByAtivoAndTituloContainingIgnoreCase(true, nome);
    }

}