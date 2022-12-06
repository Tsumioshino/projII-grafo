package com.projII_grafo;

import java.util.ArrayList;

public class Main {
	
	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		//Testing...
		StrategyStructure repre = new StrategyAdjMatrix();
		//StrategyStructure repre = new StrategyAdjList();

		TADGrafo TADgrafo = new TADGrafo(repre);
		
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String[]> arestas = new ArrayList<String[]>();
		
	
		
		vertices.add("a");
		
		vertices.add("b");
		
		vertices.add("c");
		
		vertices.add("d");
		
		String a1[] = {"b", "a", "2"};
		arestas.add(a1);
		String b1[] = {"c", "b", "4"}; 
		arestas.add(b1);
		String c1[] = {"d", "c", "5"}; 
		arestas.add(c1);

		String a[] = {"a", "b", "2"};
		arestas.add(a);
		
		String b[] = {"b", "c", "4"}; 
		arestas.add(b);
		
		String c[] = {"c", "d", "5"}; 
		arestas.add(c);
		
		TADgrafo.grafo.criarGrafo(vertices, arestas);

		//System.out.println("ArestaValue1: " +TADgrafo.grafo.getArestaValue("1", "2"));
		//System.out.println(TADgrafo.getConjuntoVertices());
		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("2"));
		//TADgrafo.Dijkstra("1");
		
		//TADgrafo.Prim("1");
		System.out.println(TADgrafo.grafo.isDigrafo());
		System.out.println(TADgrafo.grafo.toString());
		System.out.println(TADgrafo.grafo.getVerticeAdjacencia("a"));
		//TADgrafo.BFS("d", "a");	
		//System.out.println(TADgrafo.grafo.toString());
	}

}
