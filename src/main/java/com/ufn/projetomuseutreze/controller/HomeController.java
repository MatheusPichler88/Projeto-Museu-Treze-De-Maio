package com.ufn.projetomuseutreze.controller;
import com.ufn.projetomuseutreze.service.CategoriaService;
import com.ufn.projetomuseutreze.service.ItemAcervoService;
import com.ufn.projetomuseutreze.service.UsuarioService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ItemAcervoService itemAcervoService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public HomeController(ItemAcervoService itemAcervoService, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.itemAcervoService = itemAcervoService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication auth) {
        model.addAttribute("totalAcervo", itemAcervoService.listarTodos().size());
        model.addAttribute("totalCategorias", categoriaService.listarTodas().size());

        // O Spring Security cria um usuário anônimo para quem não fez login
        boolean loggedIn = auth != null
                && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);
        model.addAttribute("loggedIn", loggedIn);

        if (loggedIn) {
            // Busca o usuário logado pelo username do Spring Security
            usuarioService.buscarPorUsername(auth.getName()).ifPresent(u -> {
                model.addAttribute("currentUser", u);
                model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
            });
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}