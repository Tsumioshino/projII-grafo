package com.projII_grafo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EdgeModel {

    private int from;
    private int to;
    private String value;
    private ClassificacaoAresta tipoAresta;
}
