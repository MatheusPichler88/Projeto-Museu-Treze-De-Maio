package com.ufn.projetomuseutreze.model;

public enum TipoItem {
    LIVRO("L"),
    REVISTA("R"),
    OUTRO("O");

    private final String prefixo;

    TipoItem(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getPrefixo() {
        return prefixo;
    }
}