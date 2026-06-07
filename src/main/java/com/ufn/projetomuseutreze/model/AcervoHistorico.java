package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "acervo_historico")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcervoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_patrimonio", unique = true, length = 50)
    private String codigoPatrimonio;

    @Column(name = "nome_item", nullable = false, length = 150)
    private String nomeItem;

    @Column(name = "tipo_item", nullable = false, length = 50)
    private String tipoItem;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "ano_item")
    private Integer anoItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_conservacao", columnDefinition = "ENUM('EXCELENTE', 'BOM', 'REGULAR', 'RUIM', 'EM_RESTAURO')")
    private EstadoConservacao estadoConservacao = EstadoConservacao.BOM;

    @Column(name = "localizacao_fisica", length = 150)
    private String localizacaoFisica;

    @Column(name = "caminho_item", length = 500)
    private String caminhoItem;

    private boolean ativo = true;

    // Junção das tabelas e configuração do relacionamento
    @ManyToMany
    @JoinTable(
            name = "acervo_categoria",
            joinColumns = @JoinColumn(name = "acervo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();
}
