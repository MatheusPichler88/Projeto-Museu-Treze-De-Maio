package com.ufn.projetomuseutreze.security;

import com.ufn.projetomuseutreze.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// A anotação @Configuration indica que esta classe contém configurações do Spring.
@Configuration
// A anotação @EnableWebSecurity habilita a segurança da web no projeto.
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // Injeção da dependencia
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    // Define o filtro de segurança principal da aplicação.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Vincula explicitamente o provedor de autenticação customizado ao fluxo do Spring Security
                .authenticationProvider(authProvider())

                // Configura as autorizações das URLs do Museu
                .authorizeHttpRequests(auth -> auth
                        // Libera a página de login, a página de acesso negado e TODO o conteúdo
                        .requestMatchers("/login", "/acesso-negado", "/css/**", "/js/**", "/images/**").permitAll()

                        // Libera a página inicial para visitantes
                        .requestMatchers("/", "/index").permitAll()

                        // Libera apenas a consulta do acervo para visitantes, mas mantém cadastro/edição/desativação restritos a usuários autenticados.
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/itens").permitAll()
                        .requestMatchers("/itens/**").authenticated()

                        // Qualquer outra requisição exige login
                        .anyRequest().authenticated()
                )
                // Configura o formulário de login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                // Configura o logout
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/login?logout") // Joga para o login com aviso de saída
                        .permitAll()
                )
                // Redireciona para a tela de acesso negado quando um usuário autenticado tenta acessar algo sem permissão
                .exceptionHandling(ex -> ex.accessDeniedPage("/acesso-negado"));

        return http.build();
    }

    // Bean responsável por criptografar as senhas no padrão BCrypt antes de salvar no banco
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Componente que une seu serviço de banco de dados ao validador de senhas criptografadas
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Expõe o AuthenticationManager para o contexto do Spring
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}