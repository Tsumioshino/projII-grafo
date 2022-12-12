package com.projII_grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


// FORGET
// 1. GET VERTICE QUANTITY AND SET VERTICE QUANTITY NOT SYNCHRONIZED
public class TADGrafo {
	public LinkedList<String> topologia;
	public ArrayList<ArrayList<String>> strongyConnected;
	public ArrayList<String> visited;
	public LinkedList<String> deadVertices = new LinkedList<>();
	public LinkedList<Integer> deathTimes = new LinkedList<>();
	public LinkedList<String> DFStack = new LinkedList<>();
	public ArrayList<String> respostaBFS = new ArrayList<>();

	public StrategyStructure grafo;

	public TADGrafo(StrategyStructure representacao) {
		this.topologia = new LinkedList<String>();
		this.grafo = representacao;
	}

	public LinkedList<String> getTopologia() {
		return this.topologia;
	}
	

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
		return Integer.toString(this.grafo.getVerticeQuantity()) + ", "
				+ Integer.toString(this.grafo.getArestaQuantity());
	}

	/**
	 * @param v_inicial
	 * @return String
	 */
	public String classificarAresta(String v_inicial) {
		String api_string = "";
		byte white = 0;
		byte grey = 1;
		byte black = 2; // Cores,
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
			ArrayList<String> neighbors = this.grafo.getVerticeAdjacencia(current); // Adjacencia do vertice sendo
																					// percorrido
			ArrayList<String> neighbors_not_visited = new ArrayList<String>();

			if ((vertices_cor[current_index] == white) || (vertices_cor[current_index] == grey)) { // Se vertice nao
																									// estiver morto
				if (vertices_cor[current_index] == white) {
					vertices_chegada[current_index] = contador++;
					vertices_cor[current_index] = grey;
				}
				// Classificacao de Aresta
				if (vertices_vizinhos_visitados[current_index] != vertices_vizinhos_total[current_index]) { // Entao tem
																											// aresta
																											// nao
																											// percorrida
					String neighbor = neighbors.get(vertices_vizinhos_visitados[current_index]); // E voce pega o
																									// destino
					vertices_vizinhos_visitados[current_index] += 1;
					int n_index = vertices.indexOf(neighbor);

					// E classifica aresta correspondente
					String aresta_type = "";
					if (vertices_cor[n_index] == white) {
						aresta_type = "ARESTA_DE_ARVORE";
					}

					else if (vertices_cor[n_index] == grey) {
						aresta_type = "ARESTA_DE_RETORNO";
					}

					else if ((vertices_cor[n_index] == black)
							&& (vertices_chegada[current_index] < vertices_chegada[n_index])) {
						aresta_type = "ARESTA_DE_AVANCO";
					}

					else if ((vertices_cor[n_index] == black)
							&& (vertices_chegada[current_index] > vertices_chegada[n_index])) {
						aresta_type = "ARESTA_DE_CRUZAMENTO";
					}

					api_string += current;
					api_string += " ";
					api_string += neighbor;
					api_string += " ";
					api_string += aresta_type;
					api_string += "\n";

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
	 * @return boolean
	 */
	public boolean hasCiclo(String v_inicial) {
		byte white = 0;
		byte grey = 1;
		byte black = 2; // Cores,
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

					if (vertices_cor[n_index] == grey) {
						return true;
					} // Aresta de Retorno (Ciclo)

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

	//Faz o DFS a partir de uma origem e retorna a ordem topologica
	public LinkedList<String> DFSFromVertice(String origemU) {
		byte white = 0;
		int time = 0;
		ArrayList<String> vertices = this.getConjuntoVertices();
		int V = this.grafo.getVerticeQuantity();

		this.visited = new ArrayList<String>();
		this.deadVertices = new LinkedList<String>();
		this.deathTimes = new LinkedList<Integer>();
		this.DFStack = new LinkedList<String>();
		// Topologia
		this.topologia = new LinkedList<String>();

		int times[] = new int[V];
		int dists[] = new int[V];
		int colors[] = new int[V];
		int predecessor[] = new int[V];

		ArrayList<String> ordem_visita = new ArrayList<String>();

		for (int u = 0; u < V; u++) {
			predecessor[u] = -1;
			colors[u] = white;
		}
		ordem_visita.add(origemU);

		while (V != this.visited.size()) {// Enquanto não visitar todos
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

			if (colors[current_indexU] == white) { // Se o vertice não foi visitado, visitar
				time = DFSV1(current_indexU, time, colors, vertices, dists, predecessor, times, this.grafo);
				System.out.println("Time: " + time); //Tempo ao não conseguir visitar mais profundamente

			}

		}
		
		System.out.println("Topologia: " + this.topologia);
		return this.topologia;
	}

	public LinkedList<String> ordenacaoTopologica(String v_inicial) throws Exception {
		if (this.grafo.isDigrafo() && !this.hasCiclo(v_inicial) && this.BFSPathExist()) {
			System.out.println("Topologia: ");
		  	return this.DFSFromVertice(v_inicial);
		}
		throw new Exception("Ordenação Topológica não pode ser utilizado em grafos não-orientados, que possuam ciclos ou que são conexos");
	}

	public ArrayList<ArrayList<String>> DFStrongyConnected(String origemU) {
		byte white = 0;
		int time = 0;
		int V = this.grafo.getVerticeQuantity();
		ArrayList<String> vertices = this.getConjuntoVertices();
		ArrayList<String> ordem_visita = new ArrayList<String>();
		//Cada componente forte é uma lista
		this.strongyConnected = new ArrayList<ArrayList<String>>();
		

		this.DFStack = new LinkedList<String>();
		this.visited = new ArrayList<String>();
		// Topologia
		this.topologia = new LinkedList<String>();
		

		int times[] = new int[V];
		int dists[] = new int[V];
		int colors[] = new int[V];
		int predecessor[] = new int[V];

		for (int u = 0; u < V; u++) {
			predecessor[u] = -1;
			colors[u] = white;
		}

		ordem_visita.add(origemU);

		while (V != this.visited.size()) {// Enquanto não visitar todos
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

			if (colors[current_indexU] == white) {
				// Gera deathTimes
				time = DFSV1(current_indexU, time, colors, vertices, dists, predecessor, times, this.grafo);

				System.out.println("Time: " + time);

			}

		}

		System.out.println("Grafo Normal: " + this.grafo.toString());
		StrategyStructure grafoTrans = this.grafo.getTransposto();
		System.out.println("GrafoTrans: " + grafoTrans.toString());

		for (int i = 0; i < deathTimes.size(); i++) {
			System.out.println("DeathTimeIs: " + deathTimes.get(i));
			System.out.println("DeadVertice: " + deadVertices.get(i));
		}

		
		
		for (int u = 0; u < V; u++) {
			predecessor[u] = -1;
			colors[u] = white;
		}
		vertices = grafoTrans.getAllVertices();
		this.DFStack = new LinkedList<String>();
		for (int i = 0; i < colors.length; i++) {
			System.out.println("Color1: " + colors[i]);
		}

		while (!this.deadVertices.isEmpty()) {
			int maxTimeAux = Collections.max(this.deathTimes); //Seleciona o maior tempo de morte

			System.out.println("maxTimeAux: " + maxTimeAux);
			int maxTimeIndex = this.deathTimes.indexOf(maxTimeAux); // Index do maior tempo
			System.out.println("maxTimeIndex: " + maxTimeIndex);
			
			String maxTimeVertice = deadVertices.get((maxTimeIndex)); // Vertice de maior tempo
			int maxTimeVerticeIndex = vertices.indexOf(maxTimeVertice);

			System.out.println("Raiz da proxima arvore: " + maxTimeVertice + " Time: " + maxTimeAux);

			ArrayList<String> strongComponent = new ArrayList<String>();
			
			//O max é removido da lista de vertices dentro de DFSV2
			strongComponent = DFSV2(maxTimeVerticeIndex, maxTimeAux, colors, vertices, dists, predecessor, times,
					grafoTrans, strongComponent); //Tenta explorar todos vertices a partir desse componente

			// Add a lista de componentes a strongyConnected
			this.strongyConnected.add(strongComponent);
			
			System.out.println("DEathtimes size222: " + this.deathTimes.size());

			

		}
		System.out.println("StrongsList: " + this.strongyConnected);
		System.out.println("Stack: " + this.DFStack);
		// topologia.add("ABS");
		System.out.println(this.topologia);
		return this.strongyConnected;
	}

	public int DFSV1(int u, int time, int color[], ArrayList<String> vertices, int dists[], int predecessor[],
			int times[], StrategyStructure grafoIn) {
		System.out.println("DFSV1");
		// time = DFS
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		color[u] = grey; // Marca como visitado

		this.visited.add(vertices.get(u));// Add aos visitados
		DFStack.add(vertices.get(u));

		dists[u] = ++time;
		ArrayList<String> neighbors = grafoIn.getVerticeAdjacencia(vertices.get(u));
		;

		for (String neighbor : neighbors) {
			int vIndex = vertices.indexOf(neighbor);
			if (color[vIndex] == white) {
				predecessor[vIndex] = u;
				time = DFSV1(vIndex, time, color, vertices, dists, predecessor, times, grafoIn);
			}
		}

		// this.DFStack.pop();
		System.out.println("DFSStackPOP1: " + this.DFStack.pop());

		color[u] = black;

		times[u] = ++time;

		// Adiciona os vertices e seus tempos
		System.out.println("topoDead: " + vertices.get(u));
		this.topologia.add(vertices.get(u));
		// Tempo de morte dos vertices
		this.deadVertices.add(vertices.get(u));
		this.deathTimes.add(time);

		return time;
	}

	// DFSV2 n adiciona novos tempos de morte apenas os remove da lista quando são visitados
	public ArrayList<String> DFSV2(int u, int time, int color[], ArrayList<String> vertices, int dists[],
			int predecessor[], int times[], StrategyStructure grafoIn, ArrayList<String> strongComponent) {
		
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		color[u] = grey; // Marca como visitado

		this.visited.add(vertices.get(u));// Add aos visitados
		DFStack.add(vertices.get(u));
		// Componente forte
		System.out.println("VerticeStrong: " + vertices.get(u));
		strongComponent.add(vertices.get(u));

		//Remove os visitados, ou seja, vão fazer parte do componente forte 
		this.deadVertices.remove(vertices.get(u)); // Remove o max da list
		this.deathTimes.remove(Collections.max(this.deathTimes));

		dists[u] = ++time;
		ArrayList<String> neighbors = grafoIn.getVerticeAdjacencia(vertices.get(u));
		;

		for (String neighbor : neighbors) {
			int vIndex = vertices.indexOf(neighbor);
			if (color[vIndex] == white) {
				predecessor[vIndex] = u;
				DFSV2(vIndex, time, color, vertices, dists, predecessor, times, grafoIn, strongComponent);
			}
		}

		System.out.println("DFSStackPOP: " + this.DFStack.pop());

		color[u] = black;

		times[u] = ++time;

		// Adiciona os vertices e seus tempos
		System.out.println("topoDead: " + vertices.get(u));
		this.topologia.add(vertices.get(u));

		return strongComponent;
	}

	/**
	 * @return ArrayList<String>
	 */


	//Verifica se existe algum caminho não conexo
	public boolean BFSPathExist() {
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		ArrayList<String> neighbors;
		int V = this.grafo.getVerticeQuantity();

		ArrayList<String> vertices = this.grafo.getAllVertices();
		System.out.println("Vertices: " + vertices);
		int color[] = new int[V];

		int dists[] = new int[V];

		Integer predecessor[] = new Integer[V];

		for (int u = 0; u < V; u++) {
			color[u] = white;
			dists[u] = Integer.MAX_VALUE;
			predecessor[u] = -1;
		}

		for (int u = 0; u < V; u++) {

			if (color[u] == white) {
				color[u] = grey;
				dists[u] = 0;

				//Fila FIFO
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.offer(u);

				while (!queue.isEmpty()) {
					Integer firstOut = queue.poll();
					u = firstOut;

					neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
					System.out.println("neighbors: " + neighbors);
					for (String neighbor : neighbors) {
						int v = vertices.indexOf(neighbor);
						if (color[v] == white) {
							color[v] = grey;
							dists[v] = dists[u] + 1;
							predecessor[v] = u;
							queue.add(v);
						}
					}
					
					color[u] = black;

				}
				//
			}

		}

		System.out.println("PREDS: " + predecessor);
		for (int i = 0; i < predecessor.length; i++) {
			System.out.println("PREDS: " + predecessor[i]);
			if ((int) predecessor[i] != -1) {
				System.out.println("I: " + i);
				System.out.println("Vertices: " + vertices.get(i));
				System.out.println("Pred: " + vertices.get((int) predecessor[i]));
			} else {
				System.out.println("I: " + i);
			}

		}

		return this.isBfsConex(predecessor, vertices);
	}

	// BFS a partir de uma origem até um destino
	public Map<String, String> BFS(String origemU, String destinoV) {
		byte white = 0;
		byte grey = 1;
		byte black = 2;
		ArrayList<String> neighbors;
		int V = this.grafo.getVerticeQuantity();

		ArrayList<String> vertices = this.grafo.getAllVertices();
		System.out.println("Vertices: " + vertices);
		int color[] = new int[V];

		int dists[] = new int[V];

		Integer predecessor[] = new Integer[V];

		for (int u = 0; u < V; u++) {
			color[u] = white;
			dists[u] = Integer.MAX_VALUE;
			predecessor[u] = -1;
		}

		int u = vertices.indexOf(origemU);

		if (color[u] == white) {
			// Visita BFS
			color[u] = grey;
			dists[u] = 0;

			//Fila FIFO
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.offer(u);

			while (!queue.isEmpty()) {
				Integer firstOut = queue.poll();
				u = firstOut;

				neighbors = this.grafo.getVerticeAdjacencia(vertices.get(u));
				System.out.println("neighbors: " + neighbors);
				for (String neighbor : neighbors) {
					int v = vertices.indexOf(neighbor);
					if (color[v] == white) {
						color[v] = grey;
						dists[v] = dists[u] + 1;
						predecessor[v] = u;
						queue.add(v);
					}
				}
				color[u] = black;

			}
			//
		}

		Map<String, String> predecessores = new HashMap<>();
		for (int i = 0; i < predecessor.length; i++) {
			if ((int) predecessor[i] != -1) {
				System.out.println("Vertices: " + vertices.get(i));
				System.out.println("Pred: " + vertices.get((int) predecessor[i]));
				predecessores.put(vertices.get(i), vertices.get((int) predecessor[i]));
			} 
			
		}

		printBFS(origemU, destinoV, predecessor, vertices);

		return predecessores;
	}

	/**
	 * @param Origem
	 * @param V
	 * @param predecessor[]
	 * @param vertices
	 */
	public void printBFS(String Origem, String V, Integer predecessor[], ArrayList<String> vertices) {

		int origem = vertices.indexOf(Origem);
		Integer v = vertices.indexOf(V);

		if (origem == v) {
			System.out.println("Vertice: " + Origem);
			respostaBFS.add(Origem);
		} else if (predecessor[v] == -1) {
			System.out.println("Não existe caminho entre " + origem + " e " + v);
		} else {

			printBFS(Origem, vertices.get(predecessor[v]), predecessor, vertices);
			System.out.println("Vertice: " + vertices.get(v));
			respostaBFS.add(vertices.get(v));

		}

	}
	//Verifica o grafo é conexo
	public boolean isBfsConex(Integer predecessor[], ArrayList<String> vertices) {
		for (int u = 0; u < predecessor.length; u++) {
			if ((int) predecessor[u] != -1) {
				System.out.println("u: " + u);
				System.out.println("Vertices: " + vertices.get(u));
				System.out.println("Pred: " + vertices.get((int) predecessor[u]));
			}
			// Se o vertice não tiver antecessores e nem adjacencias então ele é desconexo
			else if (this.grafo.getVerticeAdjacencia(vertices.get(u)).isEmpty()) {

				System.out.println("Desconexo: " + vertices.get(u));
				return false;
			}

		}
		return true;
	}

	/**
	 * @param origin
	 * @return double[]
	 */
	public ArrayList<String> Prim(String origin) {

		// Lista com todos os vertices
		ArrayList<String> vertices = this.grafo.getAllVertices();

		int V = this.grafo.getVerticeQuantity();

		Boolean visited[] = new Boolean[V];

		// Guarda os vertices da MST
		String parenTree[] = new String[V];


		// Lista com as distancias locais de cada vertice
		double dists[] = new double[V];

		// Dados do antecessor
		int predecessorIndex[] = new int[V];
		String[] predecessor = new String[V];
		double[] distPredecessor = new double[V];

		parenTree[0] = origin;

		for (int i = 0; i < V; i++) {
			predecessorIndex[i] = -1;
			predecessor[i] = "Null";
			dists[i] = Integer.MAX_VALUE;
			distPredecessor[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		dists[vertices.indexOf(origin)] = 0;

		for (int i = 0; i < V; i++) {
			ArrayList<String> neighbr = new ArrayList<String>();
			int u = minDistIndex(dists, V, visited);

			visited[u] = true;
			// System.out.println("ArestaValue: " + arestaValue);
			neighbr = this.grafo.getVerticeAdjacencia(vertices.get(u));

			for (String v : neighbr) {
				double arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

				if (visited[vertices.indexOf(v)] == false
						&& arestaValue != 0
						&& arestaValue < dists[vertices.indexOf(v)]) {
					parenTree[vertices.indexOf(v)] = vertices.get(u);
					dists[vertices.indexOf(v)] = arestaValue;

					int vIndex = vertices.indexOf(v);
					predecessorIndex[vIndex] = u;
					predecessor[vIndex] = vertices.get(u);
					distPredecessor[vIndex] = arestaValue;
				}
			}
		}

		System.out.println("\nPrim:");
		String nodeName = "";
		ArrayList<String> resposta = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			nodeName = vertices.get(i);
			resposta.add(parenTree[i] + " " + nodeName + " " + dists[i]);
			System.out.println("Aresta: " + parenTree[i] + "->" + nodeName + "Weight: " + dists[i]);
			System.out.println("Dist from Antecessor '" + predecessor[i] + "' : " + distPredecessor[i]);
		}

		// Retorna as min distancias dos caminhos entre a vertice de inicio e todos os
		// vertices

		System.out.println("Dists:");
		return resposta;
	}

	/**
	 * @param dists[]
	 * @param V
	 * @param visited[]
	 * @return int
	 */
	// Essa versão n usa Prio Queue, mas sim a distancia minima retirada da matrix
	private int minDistIndex(double dists[], int V, Boolean visited[]) {
		int min = Integer.MAX_VALUE;
		double minD = min;
		int minI = -1;
		for (int i = 0; i < V; i++) {
			if (visited[i] == false && dists[i] <= minD) {
				minD = dists[i];
				minI = i;
			}
		}
		return minI;
	}

	/**
	 * @param origin
	 * @return double[]
	 */
	public ArrayList<String> Dijkstra(String origin) {


		int V = this.grafo.getVerticeQuantity();
		Boolean visited[] = new Boolean[V];

		// Lista com todos os vertices

		ArrayList<String> vertices = this.grafo.getAllVertices();

		// Lista para retirar um vertice de cada vez

		// Lista com as distancias de cada vertice até a origem
		double dists[] = new double[V];

		// Dados do antecessor
		int predecessorIndex[] = new int[V];
		String[] predecessor = new String[V];
		double[] distPredecessor = new double[V];

		for (int i = 0; i < V; i++) {
			predecessorIndex[i] = -1;
			predecessor[i] = "Null";
			dists[i] = Integer.MAX_VALUE;
			distPredecessor[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		dists[vertices.indexOf(origin)] = 0;

		for (int i = 0; i < V; i++) {
			ArrayList<String> neighbr = new ArrayList<String>();
			int u = minDistIndex(dists, V, visited);
			visited[u] = true;
			// System.out.println("ArestaValue: " + arestaValue);
			neighbr = this.grafo.getVerticeAdjacencia(vertices.get(u));

			for (String v : neighbr) {

				double arestaValue = this.grafo.getArestaValue(vertices.get(u), v);

				if (visited[vertices.indexOf(v)] == false
						&& arestaValue != 0
						&& dists[u] != Integer.MAX_VALUE
						&& dists[u] + arestaValue < dists[vertices.indexOf(v)]) {
					// relax(u,v,w)
					dists[vertices.indexOf(v)] = dists[u] + arestaValue;
					int vIndex = vertices.indexOf(v);
					predecessorIndex[vIndex] = u;
					predecessor[vIndex] = vertices.get(u);
					distPredecessor[vIndex] = arestaValue;
				}

			}

		}
		// Print Dists
		System.out.println("\nDijkstra:");
		String nodeName = "";
		ArrayList<String> resposta = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			nodeName = vertices.get(i);

			System.out.println("\nNode: " + nodeName + "Dist from source '" + origin + "'' : " + dists[i]);
			System.out.println("Dist from Antecessor '" + predecessor[i] + "' : " + distPredecessor[i]);
			resposta.add(origin + " " + nodeName + " " + dists[i]);
			//resposta.add(predecessor[i] + " " + nodeName + " " + dists[i]); para atualizar os pesos do grafo q exibe.
		}

		// Retorna as min distancias dos caminhos entre a vertice de inicio e todos os

		return resposta;
	}
}
