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


    // Rota para a Página Inicial (index.html) — Agora com suporte aos dados do painel!
    @GetMapping("/")
    public String index(Model model) {

        // 1. Simulação de Login (Substitua pela sua lógica do Spring Security depois!)
        // Por enquanto, fingimos que não há usuário logado para testar a visão de visitante.
        boolean loggedIn = false;
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("currentUser", null);

        // 2. Busca de dados reais para as estatísticas (Count do banco)
        long totalAcervo = itemAcervoRepository.count();
        model.addAttribute("totalAcervo", totalAcervo);

        // Se tiver os repositórios, use .count(). Caso contrário, deixamos um valor fixo de teste:
        long totalCategorias = 5; // categoriaRepository.count();
        model.addAttribute("totalCategorias", totalCategorias);

        long totalUsuarios = 3; // usuarioRepository.count();
        model.addAttribute("totalUsuarios", totalUsuarios);

        // 3. Notícias (Se você não tiver uma tabela de notícias ainda, enviamos null ou vazio
        // para que o HTML use o bloco estático padrão que você programou perfeitamente)
        model.addAttribute("noticias", null);

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
        // Busca todos os itens cadastrados no banco de dados
        List<ItemAcervo> listaItens = itemAcervoRepository.findAll();

        // Envia a lista para o arquivo HTML reconhecer na tag th:each="item : ${itens}"
        model.addAttribute("itens", listaItens);

        return "acervo"; // Procura por src/main/resources/templates/acervo.html
    }
}