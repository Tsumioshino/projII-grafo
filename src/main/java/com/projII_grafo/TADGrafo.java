package com.projII_grafo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

// FORGET
// 1. GET VERTICE QUANTITY AND SET VERTICE QUANTITY NOT SYNCHRONIZED
public class TADGrafo {
  public StrategyStructure grafo;

  public TADGrafo(StrategyStructure representacao) {
    this.grafo = representacao;
  }

  public ArrayList<String> getConjuntoVertices() {
    return this.grafo.getAllVertices();
  }

  public LinkedList<Object> getVerticeListAdj() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  }

  public String getVerticeArestaQuantity() {
    return Integer.toString(this.grafo.getVerticeQuantity()) + ", " + Integer.toString(this.grafo.getArestaQuantity());
  }

  public ArrayList<Object> classificarAresta() {
    return this.DFS(true, false, false);
  }

  public ArrayList<Object> ordenacaoTopologica() throws Exception {
    if (this.grafo.isDigrafo() && !this.hasCiclo() && this.isConexo()) {
      return this.DFS(false, true, false);  
    }
    throw new Exception("Ordenação Topológica não pode ser utilizado em grafos não-orientados, que possuam ciclos ou que são conexos");
  }

  public ArrayList<Object> getComponentesFortes() {
    return this.DFS(false, false, true);
  }

  public boolean hasCiclo() {
    return this.DFS(true, false);
  }

  public boolean isConexo() {
    return this.DFS(false, true);
  }

  public ArrayList<Object> DFS(boolean aresta, boolean topologia, boolean force) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public boolean DFS(boolean ciclo, boolean conexidade) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  protected ArrayList<ArrayList<String>> DFSTransposto() {
    throw new UnsupportedOperationException("Not implemented yet");
  }


  public ArrayList<String> BFS(boolean shortest) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public ArrayList<String> Prim() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public ArrayList<String> Dijkstra() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
