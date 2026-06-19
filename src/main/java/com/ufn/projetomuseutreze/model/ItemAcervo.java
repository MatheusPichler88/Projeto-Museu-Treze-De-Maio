package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_acervo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemAcervo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_patrimonio", unique = true, nullable = false, length = 50)
    private String codigoPatrimonio;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(name = "autor_criador")
    private String autorCriador;

    @Column(name = "editora_origem", length = 100)
    private String editoraOrigem;

    @Column(name = "ano_registro")
    private Integer anoRegistro;

    private Integer quantidade = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_conservacao")
    private EstadoConservacao estadoConservacao;

    @Column(name = "localizacao_fisica", length = 150)
    private String localizacaoFisica;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Boolean ativo = true;

    // Relacionamento: Muitos itens podem pertencer a uma categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}