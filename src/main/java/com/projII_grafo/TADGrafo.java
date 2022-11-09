package com.projII_grafo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

// DO NOT FORGET
// 1. GET VERTICE QUANTITY AND SET VERTICE QUANTITY NOT SYNCHRONIZED
public class TADGrafo {
  public StrategyStructure grafo;

  public int verticeQuantity = 0;
  public int arestaQuantity = 0;

  public Set<Integer> conjuntoVertices = Collections.emptySet();

  public boolean digrafo = true;
  public boolean ponderado = false;
  public boolean simples = true;

  public TADGrafo(StrategyStructure representacao) {
    this.grafo = representacao;
  }

  public LinkedList<Object> getVerticeListAdj() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  }

  public String getVerticeArestaQuantity() {
    return Integer.toString(getVerticeQuantity()) + ", " + Integer.toString(getArestaQuantity());
  }

  public int getVerticeQuantity() {
    return this.verticeQuantity;
  }

  public int getArestaQuantity() {
    return this.arestaQuantity;
  }

  public boolean getDigrafo() {
    return this.digrafo;
  }

  public boolean getPonderado() {
    return this.ponderado;
  }

  public boolean getSimplesMulti() {
    return this.simples;
  }

  private void setVerticeQuantity() {
    this.verticeQuantity = grafo.getVerticeQuantity();
  }

  private void setArestaQuantity(int n_aresta) {
    this.arestaQuantity = n_aresta;
  }

  private void setConjuntoVertices() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  private void setDigrafo() {
    this.digrafo = !this.digrafo;
  }

  private void setPonderado() {
    this.ponderado = !this.ponderado;
  }

  private void setSimplesMulti() {
    this.simples = !this.simples;
  }

  public ArrayList<Object> classificarAresta() {
    return this.DFS(true, false, false);
  }

  public ArrayList<Object> ordenacaoTopologica() throws Exception {
    if (this.getDigrafo() && !this.hasCiclo() && this.isConexo()) {
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
