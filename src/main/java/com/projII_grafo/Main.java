package com.projII_grafo;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		//Testing...
		StrategyStructure repre = new StrategyAdjMatrix();
		//StrategyStructure repre = new StrategyAdjList();

		TADGrafo TADgrafo = new TADGrafo(repre);
		
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String[]> arestas = new ArrayList<String[]>();
		
		
		TADgrafo.grafo.criarGrafo(vertices, arestas);
		
		
		TADgrafo.grafo.inserirVertice("1");
		
		TADgrafo.grafo.inserirVertice("2");
		
		TADgrafo.grafo.inserirVertice("3");
		
		TADgrafo.grafo.inserirVertice("4");
		
		TADgrafo.grafo.inserirAresta("1", "2", 2);
		
		TADgrafo.grafo.inserirAresta("2", "3", 4);
		
		TADgrafo.grafo.inserirAresta("3", "4", 5);
		
		System.out.println("ArestaValue1: " +TADgrafo.grafo.getArestaValue("1", "2"));
		System.out.println(TADgrafo.getConjuntoVertices());
		System.out.println(TADgrafo.grafo.getVerticeAdjacencia("2"));
		TADgrafo.Dijkstra("1");
		
		System.out.println(Integer.MAX_VALUE);
	}

}
