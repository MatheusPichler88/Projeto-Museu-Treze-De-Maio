package com.ufn.projetomuseutreze.controller;

import com.ufn.projetomuseutreze.model.Categoria;
import com.ufn.projetomuseutreze.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Tela de Listagem de categorias
    @GetMapping
    public String listar(Model model){
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    // Tela de Cadastro de categorias
    @GetMapping("/novo")
    public String novaCategoria(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    // Tela para salvar categoria
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria){
        categoriaService.salvar(categoria);
        return "redirect:/categorias";
    }

    // Tela para Editar categoria
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("categoria", categoriaService.buscarPorId(id));
        return "categorias/formulario";
    }

    // Rota para Desativar categoria
    @GetMapping("/desativar/{id}")
    public String desativar(@PathVariable Long id){
        categoriaService.ativarDesativar(id);
        return "redirect:/categorias";
    }

}
