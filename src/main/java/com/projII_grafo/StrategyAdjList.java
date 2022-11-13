package com.projII_grafo;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.LinkedList;


// DO NOT FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.

public class StrategyAdjList implements StrategyStructure {

  private class VerticeAresta {
    String vertice;
    String peso_aresta;
  
    public VerticeAresta(String vertice, String peso) {
      this.vertice = vertice;
      this.peso_aresta = peso;
    }
  }

  ArrayList<LinkedList<VerticeAresta>> adjList;

  public StrategyAdjList() {
    this.adjList = new ArrayList<LinkedList<VerticeAresta>>();
  }

  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
    if (this.adjList.size() == 0) {
      this.inserirConjuntoVertices(vertices);

      if (arestas != null) {
        this.inserirConjuntoArestas(arestas);
      }
    }
  }
  
  public void resetarGrafo() {
    this.adjList = null;
  }

  public int getVerticeValue(int row, int col) {
    throw new UnsupportedOperationException("Not implemented yet"); 
  }

  public void inserirVertice(String vertice) {
    VerticeAresta v_inicial = new VerticeAresta(vertice, null);
    LinkedList<VerticeAresta> celula = new LinkedList<VerticeAresta>();
    celula.add(v_inicial);
    this.adjList.add(celula);  
  }

  public void inserirConjuntoVertices(ArrayList<String> vertices) {
    for (String vertice : vertices) {
      this.inserirVertice(vertice);
    } 
  }

  public void inserirAresta(String n1, String n2, int peso) {
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      if (this.arestaExists(n1, n2)) {
        throw new UnsupportedOperationException("Simple Graph doesn't accept more than 1 edge"); 
      }
      int index1 = this.adjList.indexOf(n1);
      this.adjList.get(index1).add(n2); 
    } 
  } 

  public void inserirConjuntoArestas(ArrayList<String[]> arestas) { 
    for (String[] aresta : arestas) {
      this.inserirAresta(aresta[0], aresta[1], Integer.valueOf(aresta[2]));
    }
  } 

  public void removerAresta(String n1, String n2) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public boolean verticeExists(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public boolean arestaExists(String n1, String n2) {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getVerticeQuantity() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getArestaQuantity() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getGrau(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getGrauGeralND(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getGrauEntradaD(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public int getGrauSaidaD(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  public boolean isDigrafo() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public String toString() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 
}