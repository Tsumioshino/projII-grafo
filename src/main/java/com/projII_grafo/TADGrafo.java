package com.projII_grafo;

import java.util.Collections;
import java.util.LinkedList;
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
  
  public String getVerticeArestaQuantity() {
    return Integer.toString(this.grafo.getVerticeQuantity()) + ", " + Integer.toString(this.grafo.getArestaQuantity());
  }

  public String classificarAresta(String v_inicial) {
		String api_string = "";
		byte white = 0; byte grey = 1; byte black = 2; // Cores,
		ArrayList<String> vertices = this.getConjuntoVertices(); // Lista com todos os vertices
		ArrayList<String> visitados = new ArrayList<String>(); // Lista com vértices ja percorridos

		int many_visited[] = new int[vertices.size()]; // Vetor que indicam primeira vez que vertice foi visitado
		int vertices_vizinhos[] = new int[vertices.size()]; // Vetor que indicam primeira vez que vertice foi visitado

		int vertices_chegada[] = new int[vertices.size()]; // Vetor que indicam primeira vez que vertice foi visitado
		int vertices_morte[] = new int[vertices.size()]; // Vetor que indica ultima expansao do vertice
		byte vertices_cor[] = new byte[vertices.size()]; // Inicializando cor branca

		ArrayList<String> ordem_visita = new ArrayList<String>(); // Fila de prioridade
		ordem_visita.add(v_inicial); 

		int contador = 1;

		for (int i = 0; i < vertices.size(); i++) {
			vertices_vizinhos[i] = this.grafo.getVerticeAdjacencia(vertices.get(i)).size();
		}

		while (vertices.size() != visitados.size()) {
			if (ordem_visita.size() == 0) { // Grafo Desconexo
				for (String vertice : vertices) {
					if (!visitados.contains(vertice)) {
						ordem_visita.add(vertice);
						break;		
					}
				}	
			}
			System.out.println(ordem_visita);
			String current = ordem_visita.remove(0);
			int current_index = vertices.indexOf(current);
			int fila_java_fix = 0;
			ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(current);
			ArrayList<String> neighbors_not_visited = new ArrayList<String>();
			
			if ((vertices_cor[current_index] == white) || (vertices_cor[current_index] == grey)) {
				if (vertices_cor[current_index] == white) {
					vertices_chegada[current_index] = contador++;
					vertices_cor[current_index] = grey;
				}
				// Classificacao de Aresta
				if (many_visited[current_index] != vertices_vizinhos[current_index]) {
					String neighbor = neighbors.get(many_visited[current_index]); 
					many_visited[current_index] += 1;
					int n_index = vertices.indexOf(neighbor);
	
					String aresta_type = "";
				//	if (vertices_morte[n_index] > 0) {
				//		continue;
				//	}
					if (vertices_cor[n_index] == white) { aresta_type = "ARESTA_DE_ARVORE"; }

					else if (vertices_cor[n_index] == grey) { aresta_type = "ARESTA_DE_RETORNO"; }

					else if ((vertices_cor[n_index] == black) 
					&& (vertices_chegada[current_index] < vertices_chegada[n_index])) { aresta_type = "ARESTA_DE_AVANCO"; }

					else if ((vertices_cor[n_index] == black)
					&& (vertices_chegada[current_index] > vertices_chegada[n_index])) { aresta_type = "ARESTA_DE_CRUZAMENTO"; }
					
					api_string += current; api_string += " "; api_string += neighbor;
					api_string += " ";  api_string += aresta_type; api_string += "\n";

					System.out.println(current + " to " + neighbor + "(" + aresta_type + ")");
					
					// Adiciona vizinhos para percorrer, caso nao percorridos
					if (vertices_cor[n_index] == white) {
						ordem_visita.add(fila_java_fix++, neighbor);
						neighbors_not_visited.add(neighbor);
					}
				}
				
			}
			// Torna o vertice preto, se ele nao tiver mais vizinho para percorrer
			if ((neighbors_not_visited.size() == 0) && (vertices_cor[current_index] == grey)) {
					if (many_visited[current_index] != vertices_vizinhos[current_index]) {
						ordem_visita.add(vertices.get(current_index));
						continue;
					}
				vertices_morte[current_index] = contador++;
				vertices_cor[current_index] = black;
				visitados.add(current);

				continue;
			}
			if (vertices_cor[current_index] == black) {
				continue;
			}
			ordem_visita.add(fila_java_fix, current);
			
		}
	 	return api_string;
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
		byte white = 0; byte grey = 1; byte black = 2;
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
		byte white = 0; byte grey = 1; byte black = 2;
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
	byte white = 0;	byte grey = 1; byte black = 2;
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
			System.out.println("A1");
			while(!queue.isEmpty()){
				System.out.println("A");
				Integer firstOut = queue.pop();
				
				u = firstOut;
				neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
				if(!neighbors.isEmpty()){
					System.out.println("B");
					//String verticeEdge = neighbors.get(0);
					
					//while(verticeEdge != null){
					
					//!neighbors.isEmpty()
					while(neighbors.size() !=  0){
						String verticeEdge = neighbors.get(0);
						neighbors.remove(0);
						//int v = a.v2();
						//String vAdj = this.grafo.getVerticeAdjacencia(verticeEdge);
						int v = vertices.indexOf(verticeEdge);
						System.out.println("verticeEdge" + verticeEdge);
						System.out.println("v " + v);
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
	for(int i = 0; i < predecessor.length; i++){
		System.out.println("Pred: " + predecessor[i]);

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
			System.out.println("Vertice: " + v);
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
	  double dists[] = new double[V];
	  
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
				double arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

				if (visited[vertices.indexOf(v)] == false 
						&& arestaValue != 0 
						&& arestaValue < dists[vertices.indexOf(v)]) {
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
  private int minDistIndex(double dists[], int V, Boolean visited[]) {
	  int min = Integer.MAX_VALUE;
		double minD = min;
	  int minI = -1;
	  for(int i = 0; i < V; i++) {
		  //Talvez <= minD?:??????
		  //Talvez checar já visitados
		  if(visited[i] == false && dists[i]<= minD) {
			  minD = dists[i];
			  minI = i;
		  }
	  }
	  return minI;
  }
  
  public ArrayList<String> Dijkstra(String origin) {
	  ArrayList<Double> dist = new ArrayList<Double>();
	  
	  //PriorityQueue<Integer> queue= new PriorityQueue<Integer>();
	 
	  
	  int V = this.grafo.getVerticeQuantity();
	  Boolean visited[] = new Boolean[V];
	  //Lista com todos os vertices
	  ArrayList<String> vertices = this.grafo.getAllVertices();
	  
	  //Lista para retirar um vertice de cada vez
	  //Talvez matar o stack? e usar só o vertices sem dar pop
	  ArrayList<String> stack  = this.grafo.getAllVertices();
	  
	  
	  //Lista com as distancias de cada vertice até a origem
	  double dists[] = new double[V];
	  
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
			int u = minDistIndex(dists, V, visited);
			visited[u] = true;
			//System.out.println("ArestaValue: " + arestaValue);
			neighbr = this.grafo.getVerticeAdjacencia(vertices.get(u));

			for (String v : neighbr) {
			
			// if(visited[vertices.indexOf(v)] ==false && arestaValue != 0 && dists[u] != Integer.MAX_VALUE && dists[u] + arestaValue < dists[v]) {
			// 	//relax(u,v,w)
			// 	dists[vertices.indexOf(v)] = dists[u] + arestaValue;
				// }
				double arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

				if (visited[vertices.indexOf(v)] == false 
				&& arestaValue != 0 
				&& dists[u] != Integer.MAX_VALUE 
				&& dists[u] + arestaValue < dists[vertices.indexOf(v)]) {
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
