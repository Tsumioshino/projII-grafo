package com.projII_grafo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projII_grafo.StrategyAdjList;
import com.projII_grafo.StrategyAdjMatrix;
import com.projII_grafo.StrategyStructure;
import com.projII_grafo.TADGrafo;

@RestController
public class Controller {
    
    @GetMapping("/")
    public String teste(){
        StrategyStructure repre = new StrategyAdjList();
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

		//System.out.println(TADgrafo.grafo.getVerticeAdjacencia("2"));
		TADgrafo.Dijkstra("1").toString();
		//
		//TADgrafo.Prim("1");
		//
		//TADgrafo.BFS("1", "4");

        return TADgrafo.Dijkstra("1").toString(); 
    }
}
