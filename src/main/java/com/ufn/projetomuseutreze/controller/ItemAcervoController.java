package com.ufn.projetomuseutreze.controller;

import com.ufn.projetomuseutreze.model.ItemAcervo;
import com.ufn.projetomuseutreze.service.CategoriaService;
import com.ufn.projetomuseutreze.service.ItemAcervoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/itens")
public class ItemAcervoController {

    private final ItemAcervoService itemAcervoService;
    private final CategoriaService categoriaService;

    public ItemAcervoController(ItemAcervoService itemAcervoService, CategoriaService categoriaService) {
        this.itemAcervoService = itemAcervoService;
        this.categoriaService = categoriaService;
    }

    // Tela de listar por nome
    @GetMapping
    public String listarPorNome(@RequestParam(required = false) String nome, Model model) {
        List<ItemAcervo> itens;
        if (nome != null && !nome.isBlank()) {
            itens = itemAcervoService.buscarPorNome(nome);
        } else {
            itens = itemAcervoService.listarTodos();
        }
        model.addAttribute("itens", itens);
        model.addAttribute("nomeFiltro", nome);
        return "acervo/listar";
    }

    // Tela do formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("item", new ItemAcervo());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "acervo/cadastrar";
    }

    // Tela de salvar item
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ItemAcervo item) {
        itemAcervoService.salvar(item);
        return "redirect:/itens";
    }

    // Tela para editar item
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemAcervoService.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "acervo/cadastrar";
    }

    // Rota para desativar o item
    @GetMapping("/desativar/{id}")
    public String desativar(@PathVariable Long id) {
        itemAcervoService.ativarDesativar(id);
        return "redirect:/itens";
    }
}