package com.projII_grafo;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;

// FORGET
// 1. GET VERTICE QUANTITY AND SET VERTICE QUANTITY NOT SYNCHRONIZED
public class TADGrafo {
  public LinkedList<String> topologia;
  public ArrayList<String> visited;
  public LinkedList<String> deadVertices;

  public StrategyStructure grafo;

  public TADGrafo(StrategyStructure representacao) {
	this.topologia = new LinkedList<String>();
    this.grafo = representacao;
  }
  public LinkedList<String> getTopologia(){
	return this.topologia;
  }
//   public void setTopologia(topo){
// 	this.topologia = 
//   }
  
  /** 
   * @return ArrayList<String>
   */
  public ArrayList<String> getConjuntoVertices() {
    return this.grafo.getAllVertices();
  }
  
  
  /** 
   * @return String
   */
  public String getVerticeArestaQuantity() {
    return Integer.toString(this.grafo.getVerticeQuantity()) + ", " + Integer.toString(this.grafo.getArestaQuantity());
  }

  
  /** 
   * @param v_inicial
   * @return String
   */
  public String classificarAresta(String v_inicial) {
		String api_string = "";
		byte white = 0; byte grey = 1; byte black = 2; // Cores,
		ArrayList<String> vertices = this.getConjuntoVertices(); // Lista com todos os vertices
		ArrayList<String> visitados = new ArrayList<String>(); // Lista com vértices ja percorridos

		int vertices_vizinhos_visitados[] = new int[vertices.size()]; // Vetor com o total de adjacencias ja visitadas
		int vertices_vizinhos_total[] = new int[vertices.size()]; // Vetor com o total de adjacencias de cada vertice

		for (int i = 0; i < vertices.size(); i++) {
			vertices_vizinhos_total[i] = this.grafo.getVerticeAdjacencia(vertices.get(i)).size();
		}

		int vertices_chegada[] = new int[vertices.size()]; // Vetor que indicam primeira vez que vertice foi visitado
		int vertices_morte[] = new int[vertices.size()]; // Vetor que indica ultima expansao do vertice
		byte vertices_cor[] = new byte[vertices.size()]; // Inicializando cor branca

		ArrayList<String> ordem_visita = new ArrayList<String>(); // Fila de prioridade
		ordem_visita.add(v_inicial); 

		int contador = 1;

		while (vertices.size() != visitados.size()) { // Enquanto existir vertice pra visitar
			if (ordem_visita.size() == 0) { // Grafo Desconexo
				for (String vertice : vertices) {
					if (!visitados.contains(vertice)) {
						ordem_visita.add(vertice);
						break; // Apenas um dos vertices desconexos sao adicionados por vez
					}
				}	
			}
			String current = ordem_visita.remove(0); // Vertice atual sendo percorrido
			int current_index = vertices.indexOf(current);
			int fila_java_fix = 0;
			ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(current); // Adjacencia do vertice sendo percorrido
			ArrayList<String> neighbors_not_visited = new ArrayList<String>();
			
			if ((vertices_cor[current_index] == white) || (vertices_cor[current_index] == grey)) { // Se vertice nao estiver morto
				if (vertices_cor[current_index] == white) {
					vertices_chegada[current_index] = contador++;
					vertices_cor[current_index] = grey;
				}
				// Classificacao de Aresta
				if (vertices_vizinhos_visitados[current_index] != vertices_vizinhos_total[current_index]) { // Entao tem aresta nao percorrida
					String neighbor = neighbors.get(vertices_vizinhos_visitados[current_index]); // E voce pega o destino
					vertices_vizinhos_visitados[current_index] += 1; 
					int n_index = vertices.indexOf(neighbor);
	

					// E classifica aresta correspondente
					String aresta_type = "";
					if (vertices_cor[n_index] == white) { aresta_type = "ARESTA_DE_ARVORE"; }

					else if (vertices_cor[n_index] == grey) { aresta_type = "ARESTA_DE_RETORNO"; }

					else if ((vertices_cor[n_index] == black) 
					&& (vertices_chegada[current_index] < vertices_chegada[n_index])) { aresta_type = "ARESTA_DE_AVANCO"; }

					else if ((vertices_cor[n_index] == black)
					&& (vertices_chegada[current_index] > vertices_chegada[n_index])) { aresta_type = "ARESTA_DE_CRUZAMENTO"; }
					
					api_string += current; api_string += " "; api_string += neighbor;
					api_string += " ";  api_string += aresta_type; api_string += "\n";

					System.out.println(current + " to " + neighbor + "(" + aresta_type + ")");
					
					// Percorrido a aresta, decide se vai percorrer o vertice
					if (vertices_cor[n_index] == white) {
						ordem_visita.add(fila_java_fix++, neighbor);
						neighbors_not_visited.add(neighbor);
					}
				}
				
			}
			// Torna o vertice preto, se ele nao tiver mais vizinho para percorrer
			if ((neighbors_not_visited.size() == 0) && (vertices_cor[current_index] == grey)) {
					if (vertices_vizinhos_visitados[current_index] != vertices_vizinhos_total[current_index]) {
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
  
  /** 
   * @param v_inicial
   * @return ArrayList<Object>
   * @throws Exception
   */
//   public LinkedList<String> ordenacaoTopologica(String v_inicial) throws Exception {
//     //if (this.grafo.isDigrafo() && !this.hasCiclo(v_inicial) && this.isConexo()) {
// 	if (this.grafo.isDigrafo() && !this.hasCiclo(v_inicial)) {
// 		System.out.println("ASDASDDWADA");
//       	return this.DFSFromVertice(v_inicial , true);  
//     }
// 	else{
// 		throw new Exception("Ordenação Topológica não pode ser utilizado em grafos não-orientados, que possuam ciclos ou que são conexos");

// 	}
//   }
  

  
  /** 
   * @return ArrayList<Object>
   */
  public LinkedList<String> getComponentesFortes() {
    return this.DFS(false, false, true, false);
  }

  
  /** 
   * @param v_inicial
   * @return boolean
   */
  public boolean hasCiclo(String v_inicial) {
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
				if (many_visited[current_index] != vertices_vizinhos[current_index]) {
					String neighbor = neighbors.get(many_visited[current_index]); 
					many_visited[current_index] += 1;
					int n_index = vertices.indexOf(neighbor);

					if (vertices_cor[n_index] == grey) { return true; } // Aresta de Retorno (Ciclo)
										
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
	 	return false;  
	}

  
  /** 
   * @return boolean
   */
//   public boolean isConexo() {
	
//     return this.DFS(false, true);
//   }
//   public boolean isConexo() {
// 	int V = this.grafo.getVerticeQuantity();
// 		int times[] = new int[V];
		
// 		ArrayList<String> vertices = this.grafo.getAllVertices();
// 	// byte black = 2;
// 	// LinkedList<String> colors = this.DFS(false, false, false, true);
	
// 	// for(String color: colors){
// 	// 	if(Integer.parseInt(color) != black){ //Se todos n estao visitados
// 	// 		return false;
// 	// 	}
// 	// }
//     return isBFSConex();
	
//   }
  

  
  
  /** 
   * @return ArrayList<Object>
   */
  //public ArrayList<Object> DFS(boolean aresta, boolean topologia, boolean force) {
	
	public LinkedList<String> DFS(boolean aresta, boolean topologia, boolean force, boolean conexidade) {
		//ArrayList<Object> dfs = new ArrayList<>();
		LinkedList<String> deadVerticesTopologia = new LinkedList<String>();
		LinkedList<String> timesDeath = new LinkedList<String>();
		LinkedList<String> colors = new LinkedList<String>();
		LinkedList<String> fortes = new LinkedList<String>();

		// byte white = 0;
		// byte grey = 1;
		// byte black = 2;
		// int time = 0;
		

		byte white = 0; byte grey = 1; byte black = 2;
		int time = 0;
		int deadVerticeIndex = 0;

		int V = this.grafo.getVerticeQuantity();
		int times[] = new int[V];
		
		ArrayList<String> vertices = this.grafo.getAllVertices();
		System.out.println("Vertices: " + vertices);
		int color[] = new int[V];
		
		int predecessor[] = new int[V];

		int dists[] = new int[V];

		for(int u = 0; u < V; u++){
			color[u] = white;
			predecessor[u] = -1;
		}
		for(int u = 0; u < V; u++){
			if(color[u] == white){
				
				time = DFS(u, time, color, vertices, dists, predecessor, times);
				timesDeath.add(((Integer) time).toString());
				if(topologia){
					deadVerticeIndex = DFSLastDeadVerticeIndex(u, time, color, vertices, dists, predecessor, times);
					deadVerticesTopologia.add(vertices.get(deadVerticeIndex));
				}
				if(force){
					deadVerticeIndex = DFSLastDeadVerticeIndex(u, time, color, vertices, dists, predecessor, times);
					StrategyStructure repre = this.grafo.getTransposto();
					TADGrafo grafoTrans = new TADGrafo(repre);

					//fortes.add();
				}
				// if(conexidade){
				// 	System.out.println("Conexidade: ");
				// 	DFSVisited(u, time, color, vertices, dists, predecessor, times);
				// 	//System.out.println("Color1: " + color);
				// }
			}
		}

		
		if(topologia){
			return deadVerticesTopologia;
		}
		if(force){
			return fortes;
		}
		// if(conexidade){

		// 	for(int i = 0; i < color.length; i++){
		// 		System.out.println("Color: " + color[i]);
		// 		colors.add(((Integer)color[i]).toString());
		// 	}
		// 	return colors;
			
		// }
		
		
		return timesDeath;
	}
	
	
	/** 
	 * @param u
	 * @param time
	 * @param color[]
	 * @param vertices
	 * @param dists[]
	 * @param predecessor[]
	 * @param times[]
	 * @return int
	 */
	public int DFS(int u, int time, int color[], ArrayList<String> vertices, int dists[], int predecessor[], int times[]) {
		//time = DFS
		byte white = 0; byte grey = 1; byte black = 2;
		color[u] = grey;
		this.visited.add(vertices.get(u));//Add aos visitados
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
		//Adiciona os vertices mortos
		System.out.println("topoDead: "  + vertices.get(u));
		topologia.addFirst(vertices.get(u));
		//topologia.add(vertices.get(u));
		return time;
  }
	public LinkedList<String> DFSFromVertice(String origemU){
		byte white = 0; byte grey = 1; byte black = 2;
		ArrayList<String> vertices = this.getConjuntoVertices();
		int V = this.grafo.getVerticeQuantity();    
		//ArrayList<String> visited = new ArrayList<String>(); 
		this.visited = new ArrayList<String>();
		ArrayList<String> visitedOrder = new ArrayList<String>(); 
		LinkedList<String> deadVertices = new LinkedList<String>();

		int times[] = new int[V];
		int dists[] = new int[V];

		LinkedList<String> deadVerticesTopologia = new LinkedList<String>();
		//Topologia
		this.topologia = new LinkedList<String>();
		
		int time = 0;
		ArrayList<String> ordem_visita = new ArrayList<String>();

		int colors[] = new int[V];

		int visitedAdj[] = new int[V];
		int predecessor[] = new int[V];
		
		for (int u = 0; u < V; u++) {
			predecessor[u] = -1;
			colors[u] = white;
		}
		ordem_visita.add(origemU); 

		while(V != this.visited.size()){//Enquanto não visitar todos
			if (ordem_visita.size() == 0) { // Grafo Desconexo
				for (String vertice : vertices) {
					if (!this.visited.contains(vertice)) {
						ordem_visita.add(vertice);
						break; // Apenas um dos vertices desconexos sao adicionados por vez
					}
				}	
			}
			String current = ordem_visita.remove(0); // Vertice atual sendo percorrido
			int current_indexU = vertices.indexOf(current);

			if(colors[current_indexU] == white){
				time = DFS(current_indexU, time, colors, vertices, dists, predecessor, times);
				System.out.println("Time: " + time);

			}
			
		
		}
		//topologia.add("ABS");
		System.out.println(this.topologia);
		return this.topologia;
	}
//   public LinkedList<String> DFSFromVertice(String origemU, boolean topologia) {
// 	//ArrayList<Object> dfs = new ArrayList<>();
// 	this.topologica = new LinkedList<String>();
// 	LinkedList<String> deadVerticesTopologia = new LinkedList<String>();
// 	LinkedList<String> timesDeath = new LinkedList<String>();
// 	LinkedList<String> colors = new LinkedList<String>();
// 	LinkedList<String> fortes = new LinkedList<String>();

// 	// byte white = 0;
// 	// byte grey = 1;
// 	// byte black = 2;
// 	// int time = 0;
	

// 	byte white = 0; byte grey = 1; byte black = 2;
// 	int time = 0;
// 	int deadVerticeIndex = 0;

// 	int V = this.grafo.getVerticeQuantity();
// 	int times[] = new int[V];
	
// 	ArrayList<String> vertices = this.grafo.getAllVertices();
// 	System.out.println("Vertices: " + vertices);
// 	int color[] = new int[V];
	
// 	int predecessor[] = new int[V];

// 	int dists[] = new int[V];

// 	for(int u = 0; u < V; u++){
// 		color[u] = white;
// 		predecessor[u] = -1;
// 	}
// 	int u = vertices.indexOf(origemU);
// 	for(int u = 0; u < V){

// 	}
// 	if(color[u] == white){
			
// 		time = DFS(u, time, color, vertices, dists, predecessor, times);
// 		timesDeath.add(((Integer) time).toString());

// 		if(topologia){
// 			System.out.println("Topologia:");
// 			this.DFSLastDeadVerticeIndex(u, time, color, vertices, dists, predecessor, times);
// 			return topologica;
// 		}
			
// 	}
	

// 	return timesDeath;
// }

  
  /** 
   * @param ciclo
   * @param conexidade
   * @return boolean
   */
  public boolean DFS(boolean ciclo, boolean conexidade) {
	
    throw new UnsupportedOperationException("Not implemented yet");
  }
//   public stronglyConnected(){

//   }
  public int DFSLastDeadVerticeIndex(int u, int time, int color[], ArrayList<String> vertices, int dists[], int predecessor[], int times[]) {
	//time = DFS
	byte white = 0; byte grey = 1; byte black = 2;
	color[u] = grey;
	dists[u] = ++time;
	ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));;

	if(!neighbors.isEmpty()){
		String verticeEdge = neighbors.get(0);
		neighbors.remove(0);
		while(verticeEdge != null){
			// String verticeEdge = neighbors.get(0);
			// neighbors.remove(0);

			int v = vertices.indexOf(verticeEdge);

			if(color[v] == white){
				predecessor[v] = u;

				time = DFSLastDeadVerticeIndex(v, time, color, vertices, dists, predecessor, times);
			
			}
			if(!neighbors.isEmpty()){
				verticeEdge = neighbors.get(0);
				neighbors.remove(0);
			}
			else{
				verticeEdge = null;
			}
			
			
		}
	}
	color[u] = black;
	
	times[u] = ++time;
	System.out.println("toposV: "  + vertices.get(u));
	this.topologia.add(vertices.get(u));
	return time;
  }

  public int DFSVisited(int u, int time, int color[], ArrayList<String> vertices, int dists[], int predecessor[], int times[]) {
	//time = DFS
	byte white = 0; byte grey = 1; byte black = 2;
	color[u] = grey;
	dists[u] = ++time;
	ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));;

	
	
	while(true){
		if(!neighbors.isEmpty()){
			String verticeEdge = neighbors.get(0);
		
			neighbors.remove(0);

			int v = vertices.indexOf(verticeEdge);

			if(color[v] == white){
				predecessor[v] = u;

				time = DFSVisited(v, time, color, vertices, dists, predecessor, times);
			
			}

		}
		
		else{
			if(predecessor[u] == -1){
				System.out.println("Desconexo: " +vertices.get(u));
			}

		}
		break;
	}	

	
	color[u] = black;

	times[u] = ++time;
	
	return time;
  }

  
  /** 
   * @return ArrayList<ArrayList<String>>
   */
  protected ArrayList<ArrayList<String>> DFSTransposto() {
    throw new UnsupportedOperationException("Not implemented yet");
  }


/** 
 * @return ArrayList<String>
 */

//BFS geral
//   private void visitBfs(int V, int color[]) {
// 	color[u] = grey;
// 	dis
//   }
  public boolean BFSPathExist() {
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
				System.out.println("Uout: " + vertices.get(u));
				neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
				System.out.println("neighbors: " + neighbors);
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
	System.out.println("PREDS: " + predecessor);
	for(int i = 0; i < predecessor.length; i++){
		System.out.println("PREDS: " + predecessor[i]);
		if((int)predecessor[i] != -1){
			System.out.println("I: " +i);
			System.out.println("Vertices: " + vertices.get(i));
			System.out.println("Pred: " + vertices.get((int)predecessor[i]));
		}
		else{
			System.out.println("I: " +i);
		}
		

	}

	return this.isBfsConex(predecessor, vertices);
  }


//BFS a partir de uma origem até um destino
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
	
	int u = vertices.indexOf(origemU);
	
	System.out.println("U: " + u);
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
				System.out.println("Uout: " + vertices.get(u));
				neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
				System.out.println("neighbors: " + neighbors);
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
		
	

	System.out.println("Colors: " + color);
	for(int i = 0; i < predecessor.length; i++){
		if((int)predecessor[i] != -1){
			System.out.println("I: " +i);
			System.out.println("Vertices: " + vertices.get(i));
			System.out.println("Pred: " + vertices.get((int)predecessor[i]));
		}
		else{
			System.out.println("I: " +i);
		}
		

	}
	//System.out.println("destinoV: " + destinoV);
	printBFS(origemU, destinoV, predecessor, vertices);
	//System.out.println("destinoV: " + destinoV);
	
    return vertices;
}
  
  /** 
   * @param Origem
   * @param V
   * @param predecessor[]
   * @param vertices
   */
  public void printBFS(String Origem, String V, Integer predecessor[], ArrayList<String> vertices){
	
	int origem = vertices.indexOf(Origem);
	Integer v = vertices.indexOf(V);
	
	if (origem == v){
		System.out.println("Vertice: " +Origem);
	}
	else if(predecessor[v] == -1){
		System.out.println("Não existe caminho entre " + origem + " e " + v);
	}
	else{
		
		printBFS(Origem, vertices.get(predecessor[v]), predecessor, vertices);
		System.out.println("Vertice: " + vertices.get(v));
		}
	
		
  }
  public boolean isBfsConex(Integer predecessor[], ArrayList<String> vertices){
	for(int u = 0; u < predecessor.length; u++){
		if((int)predecessor[u] != -1){
			System.out.println("u: " +u);
			System.out.println("Vertices: " + vertices.get(u));
			System.out.println("Pred: " + vertices.get((int)predecessor[u]));
		}
		//Se o vertice não tiver antecessores e nem adjacencias então ele é desconexo
		else if (this.grafo.getVerticeAdjacencia(vertices.get(u)).isEmpty()){
			
			System.out.println("Desconexo: " + vertices.get(u));
			//System.out.println("Pred: " + vertices.get((int)predecessor[u]));
			return false;
		}
		

	}
	return true;
  }
  
  /** 
   * @param origin
   * @return ArrayList<String>
   */
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

  
  
  /** 
   * @param dists[]
   * @param V
   * @param visited[]
   * @return int
   */
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
  
  
  /** 
   * @param origin
   * @return ArrayList<String>
   */
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
