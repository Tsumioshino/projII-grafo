package com.projII_grafo.model;

/**
 * ClassificacaoAresta
 */

public enum ClassificacaoAresta {
    ARESTA_DE_ARVORE("green"),
    ARESTA_DE_AVANCO("blue"),
    ARESTA_DE_CRUZAMENTO("red"),
    ARESTA_DE_RETORNO("pink");
    
    private String color;

    private ClassificacaoAresta(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}