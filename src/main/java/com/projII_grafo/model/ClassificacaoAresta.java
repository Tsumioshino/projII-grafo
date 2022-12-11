package com.projII_grafo.model;

/**
 * ClassificacaoAresta
 */

public enum ClassificacaoAresta {
    ARESTA_DE_ARVORE("#36FF00"), //verde
    ARESTA_DE_AVANCO("#FF8700"), //marrom
    ARESTA_DE_CRUZAMENTO("#000FFF"), //azul
    ARESTA_DE_RETORNO("#FF2D00"); //vermelho
    
    private String color;

    private ClassificacaoAresta(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}