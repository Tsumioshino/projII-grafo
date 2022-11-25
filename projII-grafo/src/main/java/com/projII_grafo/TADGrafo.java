package com.projII_grafo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

  //public static void teste() {
	  //System.out.println(grafo.getAllVertices());
 // }
  public ArrayList<String> Dijkstra(String Origin) {
	  
	  PriorityQueue<Integer> queue= new PriorityQueue<Integer>();
	  //add a origem
	  
	  ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(Origin);
	  
	  while(queue.isEmpty() != false) {
		  //pop a cabeça
		  //cabeça
		  //neighbors = this.grafo.getVerticeAdjacencia(cabeca);
		  //na matrix verifcar pesos e relaxar se preciso
		  
	  }
//	  this.grafo
//	  ListaPrioridade;//Implementar um priority queue com heap 
//	  
//	  
//	  while(ListaPrioridade.isEmpty) {
//		  Object shortest = ListaPrioridade.popMax();
//		  
//		  Object.
//		  
//	  }
	  //Implementaçao nel
	  //Escolher o modo de representação TADgrafo(matrixAdj)
	  
	  //Todas as arestas com custo 'infinito'
	  for (int row = 0; row < this.grafo.getVerticeQuantity(); row++) {
	      for (int col = 0; col < this.grafo.getVerticeQuantity(); col++) {
	    	  updateAresta();
	    	  
	       
	      }
	  }
	  
//	 
    throw new UnsupportedOperationException("Not implemented yet");
  }
  
  public static void main(String[] args) {
	  System.out.println("A");
	  
  }
}
