package com.projII_grafo;

import java.util.ArrayList;

public class Main {
	
	
	/** 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
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

		//vertices.add("f");

		String a1[] = {"a", "b", "2"};
		arestas.add(a1);
		String b1[] = {"b", "c", "4"}; 
		arestas.add(b1);
		String c1[] = {"c", "d", "5"}; 
		arestas.add(c1);

		// String a[] = {"a", "b", "2"};
		// arestas.add(a);
		
		// String b[] = {"b", "c", "4"}; 
		// arestas.add(b);
		
		// String c[] = {"c", "d", "5"}; 
		// arestas.add(c);
		
		TADgrafo.grafo.criarGrafo(vertices, arestas);

		//System.out.println("ArestaValue1: " +TADgrafo.grafo.getArestaValue("1", "2"));
		//System.out.println(TADgrafo.getConjuntoVertices());
		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("2"));
		//TADgrafo.Dijkstra("1");
		
		//TADgrafo.Prim("1");
		//System.out.println(TADgrafo.grafo.isDigrafo());
		//System.out.println(TADgrafo.grafo.toString());
		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("a"));
		//System.out.println("Topo");
		//System.out.println(TADgrafo.topologia('b'));
		//System.out.println(TADgrafo.BFS("b", "d"));
		//System.out.println(TADgrafo.respostaBFS);
		//System.out.println(TADgrafo.Dijkstra("b"));
		//System.out.println(TADgrafo.isConexo());
		//TADgrafo.ordenacaoTopologica("b");	
		//System.out.println(TADgrafo.grafo.toString());
	}

}
