package com.ufn.projetomuseutreze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Boolean ativo = true;

    // Relacionamento: Uma categoria pode ter muitos itens vinculados
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ItemAcervo> itens;
}
