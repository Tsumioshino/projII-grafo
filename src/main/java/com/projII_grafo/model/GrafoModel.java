package com.projII_grafo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GrafoModel {
    private String origem;
    private String destino;
    private int quantidadeAresta;
    private int quantidadeVertice;
    private boolean digrafo;
    private List<NodeModel> nodes;
    private List<EdgeModel> edges;
}
