package com.projII_grafo;
import java.util.ArrayList;
import java.util.LinkedList;

import java.lang.UnsupportedOperationException;


public class StrategyAdjList implements StrategyStructure {
  ArrayList<LinkedList<Object>> adjList;

  public StrategyAdjList() {
    this.adjList = new ArrayList<LinkedList<Object>>();
  }


  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
    if (this.adjList.size() == 0) {
      for (String vertice : vertices) {
        LinkedList<Object> node = new LinkedList<Object>();
        node.add(vertice);
        this.adjList.add(node);
      }  

      if (arestas != null) {
        this.inserirConjuntoArestas(arestas);
      }
    }
  }
  
  public void inserirAresta(String n1, String n2, int peso) {
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      if (this.arestaExists(n1, n2)) {
        throw new UnsupportedOperationException("Simple Graph doesn't accept more than 1 edge"); 
      }
      else {
        int index1 = this.adjList.indexOf(n1);
        this.adjList.get(index1).add(n2); 
      }
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
