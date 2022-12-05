package com.projII_grafo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projII_grafo.StrategyAdjMatrix;
import com.projII_grafo.StrategyStructure;
import com.projII_grafo.TADGrafo;
import com.projII_grafo.model.EdgeModel;
import com.projII_grafo.model.GrafoModel;
import com.projII_grafo.model.NodeModel;

@RestController
public class MatrizController {

	private TADGrafo tadGrafo;

	@GetMapping("/")
	public String home(){
		return "oi";
	}

	@PostMapping("/")
	public void retornaAll(@RequestBody String teste){
		System.out.println(teste);
	}
    
	@PostMapping(value = "/matriz/verificarAresta/")
    public boolean verificarAresta(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		//System.out.println(this.grafo.grafo.toString());
		return this.tadGrafo.grafo.arestaExists(grafo.getOrigem(), grafo.getDestino());
    }

	private void converteFront(GrafoModel grafoModel){
		Map<Integer, String> verticesDict = new HashMap<>();
		StrategyStructure repre = new StrategyAdjMatrix();
		this.tadGrafo = new TADGrafo(repre);
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String[]> arestas = new ArrayList<String[]>();
		for (NodeModel node : grafoModel.getNodes()) {
			vertices.add(node.getLabel());
			verticesDict.put(node.getId(), node.getLabel());
		}
		for (EdgeModel edgeModel : grafoModel.getEdges()) {
			String a[] = {verticesDict.get(edgeModel.getFrom()), verticesDict.get(edgeModel.getTo()), edgeModel.getValue()};
			arestas.add(a);
		}
		this.tadGrafo.grafo.criarGrafo(vertices, arestas);
	}
}
