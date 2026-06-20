package com.ufn.projetomuseutreze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ufn.projetomuseutreze.model.ItemAcervo;
import com.ufn.projetomuseutreze.repository.ItemAcervoRepository;

@Controller
public class HomeController {
    @Autowired
    private ItemAcervoRepository itemAcervoRepository;

    // Rota para a Página Inicial (index.html)
    @GetMapping("/")
    public String index() {
        return "index"; // Procura por src/main/resources/templates/index.html
    }

    // Rota para a Página de Login / Cadastro (login.html)
    @GetMapping("/login")
    public String login() {
        return "login"; // Procura por src/main/resources/templates/login.html
    }

    // Rota para a Listagem do Acervo (acervo.html)
    @GetMapping("/acervo")
    public String acervo(Model model) {
        // Busca todos os itens cadastrados no banco de dados (conforme seu script-db.sql)
        List<ItemAcervo> listaItens = itemAcervoRepository.findAll();
        
        // Envia a lista para o arquivo HTML reconhecer na tag th:each="item : ${itens}"
        model.addAttribute("itens", listaItens);
        
        return "acervo"; // Procura por src/main/resources/templates/acervo.html
    }
}
