package com.projII_grafo.model;

/**
 * ClassificacaoAresta
 */

public enum ClassificacaoAresta {
    ARESTA_DE_ARVORE("#36FF00"),
    ARESTA_DE_AVANCO("#FF8700"),
    ARESTA_DE_CRUZAMENTO("#000FFF"),
    ARESTA_DE_RETORNO("#FF2D00");
    
    private String color;

    private ClassificacaoAresta(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}