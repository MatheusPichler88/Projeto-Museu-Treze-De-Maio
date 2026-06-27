package com.ufn.projetomuseutreze.controller.advice;

import com.ufn.projetomuseutreze.service.UsuarioService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Esta classe observa todas as páginas do sistema para descobrir quem está acessando
@ControllerAdvice
public class GlobalModelAttributes {

    private final UsuarioService usuarioService;

    public GlobalModelAttributes(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método executado automaticamente antes de carregar qualquer tela HTML
    @ModelAttribute
    public void adicionarUsuarioLogado(Model model, Authentication authentication) {

        // Verifica se quem está acessando é um usuário real logado ou apenas um visitante
        boolean loggedIn = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        // Envia para o HTML se o usuário está logado (true) ou se é visitante (false)
        model.addAttribute("loggedIn", loggedIn);

        // Se for um usuário logado, busca os dados dele no banco e envia para a tela
        if (loggedIn) {
            usuarioService.buscarPorUsername(authentication.getName())
                    .ifPresent(usuario -> model.addAttribute("currentUser", usuario));
        }
    }
}