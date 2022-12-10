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
		
	
		
		// vertices.add("a");
		
		// vertices.add("b");
		
		// vertices.add("c");
		
		// vertices.add("d");

		// //vertices.add("f");

		// String a1[] = {"a", "b", "2"};
		// arestas.add(a1);
		// String b1[] = {"b", "c", "4"}; 
		// arestas.add(b1);
		// String c1[] = {"c", "d", "5"}; 
		// arestas.add(c1);

		// String c2[] = {"d", "c", "5"}; 
		// arestas.add(c2);


		vertices.add("a");
		
		vertices.add("b");
		
		vertices.add("c");
		
		vertices.add("d");

		vertices.add("e");

		//vertices.add("f");
		//a0 b1 c2 d3 e4
		String a1[] = {"a", "b", "3"};
		arestas.add(a1);
		
		String b1[] = {"b", "c", "4"}; 
		arestas.add(b1);

		String c1[] = {"d", "e", "5"}; 
		arestas.add(c1);

		// String a[] = {"a", "b", "2"};
		// arestas.add(a);
		
		// String b[] = {"b", "c", "4"}; 
		// arestas.add(b);
		
		// String c[] = {"c", "d", "5"}; 
		// arestas.add(c);
		
		TADgrafo.grafo.criarGrafo(vertices, arestas);
		System.out.println(TADgrafo.grafo.toString());

		TADGrafo TADgrafo2 = new TADGrafo(TADgrafo.grafo.getTransposto());
		System.out.println(TADgrafo2.grafo.toString());
		
		//System.out.println("ArestaValue1: " +TADgrafo.grafo.getArestaValue("1", "2"));
		//System.out.println(TADgrafo.getConjuntoVertices());
		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("2"));
		//System.out.println(TADgrafo.Prim("a"));
		
		//TADgrafo.Prim("1");
		//System.out.println(TADgrafo.grafo.isDigrafo());
		//System.out.println(TADgrafo.grafo.toString());
		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("a"));
		//System.out.println("Topo");
		//System.out.println(TADgrafo.ordenacaoTopologica("b"));
		// System.out.println(TADgrafo.BFS("b", "d"));
		// System.out.println(TADgrafo.respostaBFS);

		System.out.println(TADgrafo.ordenacaoTopologica("a"));

		//System.out.println(TADgrafo.DFSFromVertice("b"));
		//System.out.println(TADgrafo.isConexo());
		//TADgrafo.ordenacaoTopologica("b");	
		//System.out.println(TADgrafo.grafo.toString());
		//System.out.println(TADgrafo.DFStrongyConnected("a"));

	}

}
