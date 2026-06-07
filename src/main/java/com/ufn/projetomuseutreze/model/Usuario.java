package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String senha;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADMINISTRADOR', 'FUNCIONARIO', 'VISITANTE')")
    private PerfilUsuario perfil = PerfilUsuario.VISITANTE;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

}
