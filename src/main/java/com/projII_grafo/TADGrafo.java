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

//   private void visitBfs(int V, int color[]) {
// 	color[u] = grey;
//   }
//   public ArrayList<String> BFS(boolean shortest) {
// 	byte white = 0;
// 	byte grey = 1;
// 	byte black = 2;

// 	int V = this.grafo.getVerticeQuantity();

// 	int color[] = new int[V];
	
// 	int dists[] = new int [V];

// 	int predecessor[] = new int[V];

// 	for(int u = 0; u < V; u++){
// 		if(color[u] == white){
// 			this.visitBfs(u color);
// 		}
// 	}

//     throw new UnsupportedOperationException("Not implemented yet");
//   }

  public ArrayList<String> Prim(String origin) {
	  
	  //Lista com todos os vertices
	  ArrayList<String> vertices = this.grafo.getAllVertices();
	  
	  int V = this.grafo.getVerticeQuantity();
	  
	  Boolean visited[] = new Boolean[V];
	  
	  //Guarda os vertices da MST
	  int parenTree[] = new int[V];
	  
	  //String parentTree[] = new String[V];
	  
	  //Lista com as distancias locais de cada vertice
	  int dists[] = new int[V];
	  
	  parenTree[0] = -1;
	  
	  //dists[vertices.indexOf(origin)] = 0;
	  
	  for(int i = 0; i< V; i++) {
		  dists[i] = Integer.MAX_VALUE;  
	  }
	  
	  dists[vertices.indexOf(origin)] = 0;
	 

	  for(int i = 0; i <= V; i++) {
		  
		  int u = minDistIndex(dists, V, visited);
		  
		  for(int v = 0; v < V; v++) {
			  int arestaValue = this.grafo.getArestaValue(u, v);
			  if(visited[v] == false && arestaValue != 0 && arestaValue < dists[v]) {
				  parenTree[v] = u;
				  dists[v] = arestaValue;
			  }
		  }
	  }
	  
	  System.out.println("\nPrim:");
	  String nodeName = "";
	  
	  for(int i =0 ; i< V; i++) {
		  nodeName = vertices.get(i);
		  
		  System.out.println("Aresta: " + parenTree[i] + "->" + nodeName + "Weight:" + this.grafo.getArestaValue(i, parenTree[i]));
	  }
	  
	  throw new UnsupportedOperationException("Not implemented yet");
  }

  
  //Essa versão n usa Prio Queue, mas sim a distancia minima retirada da matrix
  private int minDistIndex(int dists[], int V, Boolean visited[]) {
	  int min = Integer.MAX_VALUE;
	  int minI = -1;
	  for(int i = 0; i < V; i++) {
		  //Talvez <= min?:??????
		  //Talvez checar já visitados
		  if(visited[i] == false && dists[i]<= min) {
			  min = dists[i];
			  minI = i;
		  }
	  }
	  return minI;
  }
  
  public ArrayList<String> Dijkstra(String origin) {
	  ArrayList<Integer> dist = new ArrayList<Integer>();
	  
	  //PriorityQueue<Integer> queue= new PriorityQueue<Integer>();
	 
	  
	  int V = this.grafo.getVerticeQuantity();
	  Boolean visited[] = new Boolean[V];
	  //Lista com todos os vertices
	  ArrayList<String> vertices = this.grafo.getAllVertices();
	  
	  //Lista para retirar um vertice de cada vez
	  //Talvez matar o stack? e usar só o vertices sem dar pop
	  ArrayList<String> stack  = this.grafo.getAllVertices();
	  
	  
	  //Lista com as distancias de cada vertice até a origem
	  int dists[] = new int[V];
	  
	  for(int i = 0; i< V; i++) {
		  dists[i] = Integer.MAX_VALUE;
		  visited[i] = false;
	  }
	  
	  dists[vertices.indexOf(origin)] = 0;
	  
	  //
	  
	  //while(stack.isEmpty() != false) {
	//	  i = stack.pop
	 // }
	  
	  //Talvez usar a função getVerticeAdj
	  for(int i = 0; i < V-1; i++) {
		  
		  int u = minDistIndex(dists, V, visited);
		  visited[u] = true;
		  
		  for(int v = 0; v < V; v++) {
			  //Criar metodo para navegar ou acessar a matrix do grafo
			  //Get aresta value e update !!!
			  
			  Integer iU = u;
			  Integer iV = v;
			  System.out.println("u: " + iU.toString());
			  System.out.println("v: " + iV.toString());
			  
			  int arestaValue = this.grafo.getArestaValue(iU.toString(), iV.toString());
			  
			  System.out.println("ArestaValue: " + arestaValue);
			  if(visited[v] ==false && arestaValue != 0 && dists[u] != Integer.MAX_VALUE && dists[u] + arestaValue < dists[v]) {
				  //relax(u,v,w)
				  dists[v] = dists[u] + arestaValue;
			  }
		  }
	  }
	  //Print Dists
	  System.out.println("\nDijkstra:");
	  String nodeName = "";
	  
	  for(int i =0 ; i< V; i++) {
		  nodeName = vertices.get(i);
		  System.out.println("\nDistance From: " + origin);
		  System.out.println("\nNode:" + nodeName + "Dist: " + dists[i] );
	  }
	  
	  
	  
	  
    throw new UnsupportedOperationException("Not implemented yet");
  }

	  
	  
}
