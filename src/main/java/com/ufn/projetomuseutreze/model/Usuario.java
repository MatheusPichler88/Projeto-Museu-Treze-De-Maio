package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private PerfilUsuario perfil = PerfilUsuario.VISITANTE;

    private Boolean ativo = true;
}