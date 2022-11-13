package com.projII_grafo;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.LinkedList;



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

  @Override
  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
    if (this.adjList.size() == 0) {
      for (String vertice : vertices) {
        VerticeAresta v_inicial = new VerticeAresta(vertice, null);
        LinkedList<VerticeAresta> node = new LinkedList<VerticeAresta>();
        node.add(v_inicial);
        this.adjList.add(node);
      }  

      if (arestas != null) {
        this.inserirConjuntoArestas(arestas);
      }
    }
  }
  
  @Override
  public void resetarGrafo() {
    this.adjList = null;
  }

  public void inserirVertice(String vertice) {
    throw new UnsupportedOperationException("Not implemented yet"); 
  }

  public void inserirConjuntoVertices(ArrayList<String> vertices) {
    throw new UnsupportedOperationException("Not implemented yet"); 
  }


  @Override
  public void inserirAresta(String n1, String n2, int peso) {
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      if (this.arestaExists(n1, n2)) {
        throw new UnsupportedOperationException("Simple Graph doesn't accept more than 1 edge"); 
      }
      int index1 = this.adjList.indexOf(n1);
      this.adjList.get(index1).add(n2); 
    } 
  } 

  @Override
  public void inserirConjuntoArestas(ArrayList<String[]> arestas) { 
    for (String[] aresta : arestas) {
      this.inserirAresta(aresta[0], aresta[1], Integer.valueOf(aresta[2]));
    }
  } 

  @Override
  public void removerAresta(String n1, String n2) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public boolean verticeExists(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public boolean arestaExists(String n1, String n2) {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getVerticeQuantity() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getArestaQuantity() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getGrau(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getGrauGeralND(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getGrauEntradaD(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public int getGrauSaidaD(String n1) { 
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public boolean isDigrafo() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 

  @Override
  public String toString() {
    throw new UnsupportedOperationException("Not implemented yet"); 
  } 
}