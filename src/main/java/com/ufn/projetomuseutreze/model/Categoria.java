package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "codigo_classificacao", length = 20)
    private String codigoClassificacao;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    private Boolean ativo = true;

    //Uma categoria pode ter muitas subcategorias
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    //Lista de subcategorias
    @OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL)
    private List<Categoria> subcategorias = new ArrayList<>();

    @ManyToMany(mappedBy = "categorias")
    private List<Livro> livros = new ArrayList<>();

    //Lista de revistas
    @ManyToMany(mappedBy = "categorias")
    private List<Revista> revistas = new ArrayList<>();
}
