package com.ufn.projetomuseutreze.security;

import com.ufn.projetomuseutreze.model.Usuario;
import com.ufn.projetomuseutreze.model.PerfilUsuario; // Importando o seu Enum real
import com.ufn.projetomuseutreze.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        //Cria o Administrador do sistema
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador do Museu");
            admin.setUsername("admin");
            admin.setSenha(passwordEncoder.encode("123456"));
            admin.setPerfil(PerfilUsuario.ADMINISTRADOR);
            admin.setAtivo(true);

            usuarioRepository.save(admin);
            System.out.println("-> [MUSEU] Usuário administrador inicial criado com sucesso.");
        }

        // 2. Cria o Funcionário comum
        if (usuarioRepository.findByUsername("user").isEmpty()) {
            Usuario user = new Usuario();
            user.setNome("Funcionário Padrão");
            user.setUsername("user");
            user.setSenha(passwordEncoder.encode("123456"));
            user.setPerfil(PerfilUsuario.FUNCIONARIO);
            user.setAtivo(true);

            usuarioRepository.save(user);
            System.out.println("-> [MUSEU] Usuário funcionário inicial criado com sucesso.");
        }
    }
}