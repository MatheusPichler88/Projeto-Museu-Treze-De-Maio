package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "livro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_patrimonio", unique = true, length = 50)
    private String codigoPatrimonio;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 255)
    private String subtitulo;

    @Column(length = 255)
    private String autor;

    @Column(length = 255)
    private String organizador;

    @Column(length = 20)
    private String isbn;

    @Column(length = 100)
    private String editora;

    @Column(length = 100)
    private String cidade;

    @Column(length = 50)
    private String edicao;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    private Integer quantidade = 1;

    @Column(columnDefinition = "TEXT")
    private String referencia;

    private Boolean ativo = true;

    //Junção das tabelas e configuração do relacionamento
    @ManyToMany
    @JoinTable(
            name = "livro_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

}
