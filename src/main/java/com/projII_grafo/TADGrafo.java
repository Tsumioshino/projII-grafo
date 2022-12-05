package com.projII_grafo;

import java.util.ArrayList;
import java.util.LinkedList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// FORGET
// 1. GET VERTICE QUANTITY AND SET VERTICE QUANTITY NOT SYNCHRONIZED
@NoArgsConstructor
@Getter
@Setter
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

  //public ArrayList<Object> DFS(boolean aresta, boolean topologia, boolean force) {
	public ArrayList<Object> DFS(boolean aresta, boolean topologia, boolean force) {
		ArrayList<Object> dfs = new ArrayList<>();
		// byte white = 0;
		// byte grey = 1;
		// byte black = 2;
		// int time = 0;
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		int time = 0;
		int V = this.grafo.getVerticeQuantity();
		
		
		ArrayList<String> vertices = this.grafo.getAllVertices();
		System.out.println("Vertices: " + vertices);
		int color[] = new int[V];
		
		int dists[] = new int[V];

		int times[] = new int[V];

		Integer predecessor[] = new Integer[V];


		for(int u = 0; u < V; u++){
			color[u] = white;
			predecessor[u] = -1;
		}

		for(int u =0; u < V; u++){
			if(color[u] == white){
				
			}
		}

		return dfs;
	}
	
	public int DFS(int u, int time, int color[], ArrayList<String> vertices, int dists[], int predecessor[], int times[]) {
		//time = DFS
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		color[u] = grey;
		dists[u] = ++time;
		ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));;
	
		if(!neighbors.isEmpty()){
			

			while(!neighbors.isEmpty()){
				String verticeEdge = neighbors.get(0);
				neighbors.remove(0);

				int v = vertices.indexOf(verticeEdge);

				if(color[v] == white){
					predecessor[v] = u;

					time = DFS(v, time, color, vertices, dists, predecessor, times);
				}
			}
		}
		color[u] = black;
		times[u] = ++time;
		return time;
    }

  public boolean DFS(boolean ciclo, boolean conexidade) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  protected ArrayList<ArrayList<String>> DFSTransposto() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

//   private void visitBfs(int V, int color[]) {
// 	color[u] = grey;
// 	dis
//   }
  public ArrayList<String> BFS(String origemU, String destinoV) {
	byte white = 0;
	byte grey = 1;
	byte black = 2;
	ArrayList<String> neighbors;
	int V = this.grafo.getVerticeQuantity();
	
	ArrayList<String> vertices = this.grafo.getAllVertices();
	System.out.println("Vertices: " + vertices);
	int color[] = new int[V];
	
	int dists[] = new int [V];

	Integer predecessor[] = new Integer[V];
	
	for(int u = 0; u < V; u++){
		color[u] = white;
		dists[u] = Integer.MAX_VALUE;
		predecessor[u] = -1;
	}
	
	for(int u = 0; u < V; u++){
		if(color[u] == white){
			//Visita BFS
			color[u] = grey;
			dists[u] = 0;

			//Queue = new 
			LinkedList<Integer> queue = new LinkedList<Integer>();
			//Queue.enfileira(new Integer u)
			queue.add(u);
			while(!queue.isEmpty()){
				Integer firstOut = queue.pop();
				
				u = firstOut;
				neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
				if(!neighbors.isEmpty()){
					//String verticeEdge = neighbors.get(0);
					
					//while(verticeEdge != null){
					
					//!neighbors.isEmpty()
					while(neighbors.size() !=  0){
						String verticeEdge = neighbors.get(0);
						neighbors.remove(0);
						//int v = a.v2();
						//String vAdj = this.grafo.getVerticeAdjacencia(verticeEdge);
						int v = vertices.indexOf(verticeEdge);
						System.out.println("verticeEdge " + verticeEdge);
						//System.out.println("v " + v);
						if(color[v] == white){
							color[v] = grey;
							dists[v] = dists[u] + 1;
							predecessor[v] = u;
							queue.add(v);
							//Queue(new Integer (v))
						}
						//verticeEdge = neighbors.get(0);
					}
				}
				color[u] = black;

			}
			//
		}
		
	}

	System.out.println("Colors: " + color);
	for(int i = 1; i < predecessor.length; i++){
		System.out.println("Pred: " + vertices.get((int )predecessor[i]));

	}
	System.out.println("destinoV: " + destinoV);
	printBFS(origemU, destinoV, predecessor, vertices);
	System.out.println("destinoV: " + destinoV);
	
    return vertices;
  }

  public void printBFS(String Origem, String V, Integer predecessor[], ArrayList<String> vertices){
	
	int origem = vertices.indexOf(Origem);
	Integer v = vertices.indexOf(V);
	// System.out.println("Vertices: " + vertices);
	// System.out.println("V: " + V);
	// System.out.println("IV: " + v);
	if (origem == v){
		System.out.println(origem);
	}
	else if(predecessor[v] == -1){
		System.out.println("Não existe caminho entre " + origem + " e " + v);

	}
	else{
		printBFS(Origem, vertices.get(predecessor[v]), predecessor, vertices);
		System.out.println("Vertice: " + vertices.get(v));
	}
	
  }
  public ArrayList<String> Prim(String origin) {
	  
	  //Lista com todos os vertices
	  ArrayList<String> vertices = this.grafo.getAllVertices();
	  
	  int V = this.grafo.getVerticeQuantity();
	  
	  Boolean visited[] = new Boolean[V];
	  
	  //Guarda os vertices da MST
	  String parenTree[] = new String[V];
	  
	  //String parentTree[] = new String[V];
	  
	  //Lista com as distancias locais de cada vertice
	  int dists[] = new int[V];
	  
	  parenTree[0] = origin;
	  
	  //dists[vertices.indexOf(origin)] = 0;
	  
	  for(int i = 0; i< V; i++) {
		  dists[i] = Integer.MAX_VALUE;
		  visited[i] = false;
	  }
	  
	  dists[vertices.indexOf(origin)] = 0;
	 

	  for(int i = 0; i < V - 1; i++) {
		  ArrayList<String> neighbr = new ArrayList<String>();
		  int u = minDistIndex(dists, V, visited);
		  

		  visited[u] = true;
		  //System.out.println("ArestaValue: " + arestaValue);
		  neighbr = this.grafo.getVerticeAdjacencia(vertices.get(u));

		  for(String v : neighbr){
			int arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

			if(visited[vertices.indexOf(v)] == false && arestaValue != 0 && arestaValue < dists[vertices.indexOf(v)]) {
				parenTree[vertices.indexOf(v)] = vertices.get(u);
				dists[vertices.indexOf(v)] = arestaValue;
			}
		  }
	  }
	  
	  System.out.println("\nPrim:");
	  String nodeName = "";
	  
	  for(int i =0 ; i< V -1; i++) {
		  nodeName = vertices.get(i);
		  //System.out.println("Weight?: " + dists[i]);
		  System.out.println("Aresta: " + parenTree[i] + "->" + nodeName + "Weight: " + dists[i]);
	  }
	  
	  return vertices;
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
	  
	  //getVerticeAdjacencia
	  
	  //while(stack.isEmpty() != false) {
	//	  i = stack.pop
	 // }
	  
	  //Talvez usar a função getVerticeAdj
	  //ArrayList<String> neighbr = new ArrayList<String>();

	  for(int i = 0; i < V-1; i++) {
		  ArrayList<String> neighbr = new ArrayList<String>();
		  Integer u = minDistIndex(dists, V, visited);
		  visited[u] = true;
		  //System.out.println("ArestaValue: " + arestaValue);
		  neighbr = this.grafo.getVerticeAdjacencia(vertices.get(u));

		  for(String v : neighbr){
			
			// if(visited[vertices.indexOf(v)] ==false && arestaValue != 0 && dists[u] != Integer.MAX_VALUE && dists[u] + arestaValue < dists[v]) {
			// 	//relax(u,v,w)
			// 	dists[vertices.indexOf(v)] = dists[u] + arestaValue;
		    // }
			int arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

			if(visited[vertices.indexOf(v)] ==false && arestaValue != 0 && dists[u] != Integer.MAX_VALUE && dists[u] + arestaValue < dists[vertices.indexOf(v)]) {
				//relax(u,v,w)
				dists[vertices.indexOf(v)] = dists[u] + arestaValue;
		    }

		  }
		  
		  
		//   for(int v = 0; v < V; v++) {
		// 	  //Criar metodo para navegar ou acessar a matrix do grafo
		// 	  //Get aresta value e update !!!
			  
		// 	  Integer iU = u;
		// 	  Integer iV = v;
		// 	  System.out.println("u: " + iU.toString());
		// 	  System.out.println("v: " + iV.toString());
			  
		// 	  //int arestaValue = this.grafo.getArestaValue(iU.toString(), iV.toString());
			  
		// 	  System.out.println("ArestaValue: " + arestaValue);
		// 	  if(visited[v] ==false && arestaValue != 0 && dists[u] != Integer.MAX_VALUE && dists[u] + arestaValue < dists[v]) {
		// 		  //relax(u,v,w)
		// 		  dists[v] = dists[u] + arestaValue;
		// 	  }
		//   }
	  }
	  //Print Dists
	  System.out.println("\nDijkstra:");
	  String nodeName = "";
	  
	  for(int i =0 ; i< V; i++) {
		  nodeName = vertices.get(i);
		  System.out.println("\nDistance From: " + origin);
		  System.out.println("\nNode:" + nodeName + "Dist: " + dists[i] );
	  }
	  
	  
	  
	  
	  return vertices;
  }

	  
	  
}
