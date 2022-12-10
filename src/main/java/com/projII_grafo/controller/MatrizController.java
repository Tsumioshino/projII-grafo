package com.projII_grafo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projII_grafo.StrategyAdjMatrix;
import com.projII_grafo.StrategyStructure;
import com.projII_grafo.TADGrafo;
import com.projII_grafo.model.ClassificacaoAresta;
import com.projII_grafo.model.EdgeModel;
import com.projII_grafo.model.GrafoModel;
import com.projII_grafo.model.NodeModel;

@RestController
public class MatrizController {

	private TADGrafo tadGrafo;

	@GetMapping(value = "/teste")
	public ClassificacaoAresta getMethodName() {
		return ClassificacaoAresta.ARESTA_DE_ARVORE;
	}

	/**
	 * @param grafo
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarAresta/")
	public boolean verificarAresta(@RequestBody GrafoModel grafo) { // ja fiz
		converteFront(grafo);
		return this.tadGrafo.grafo.arestaExists(grafo.getOrigem(), grafo.getDestino());
	}

	/**
	 * @param grafo
	 * @return ArrayList<String>
	 */
	@PostMapping(value = "/matriz/obterListaAdj/") // ja fiz
	public ArrayList<String> obterListaAdj(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.grafo.getVerticeAdjacencia(grafo.getOrigem());
	}

	/**
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/quantidadeVerticesArestas/") // fiz
	public GrafoModel quantidadeVerticesArestas(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		grafo.setQuantidadeAresta(this.tadGrafo.grafo.getArestaQuantity());
		grafo.setQuantidadeVertice(this.tadGrafo.grafo.getVerticeQuantity());
		return grafo;
	}

	/**
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/buscaLargura/") // fiz
	public ArrayList<String> buscaLargura(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		tadGrafo.BFS(grafo.getOrigem(), grafo.getDestino());
		return tadGrafo.respostaBFS;
	}

	/**
	 * @param grafo
	 * @return int
	 */
	@PostMapping(value = "/matriz/obterGrauVertice/") // fiz
	public int obterGrauVertice(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.grafo.getGrau(grafo.getOrigem());
	}

	/**
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/obterClassificacaoAresta/") // ja fiz
	public GrafoModel obterClassificacaoAresta(@RequestBody GrafoModel grafo) {
		Map<Integer, String> verticesDict = new HashMap<>();
		converteFront(grafo);
		for (NodeModel node : grafo.getNodes()) {
			verticesDict.put(node.getId(), node.getLabel());
		}
		String[] classificacoes = this.tadGrafo.classificarAresta(grafo.getOrigem()).split("\n");
		for (EdgeModel edgeModel : grafo.getEdges()) {
			for (String classificacao : classificacoes) {
				String[] classif = classificacao.split(" ");
				if (verticesDict.get(edgeModel.getFrom()).equals(classif[0]) &&
						verticesDict.get(edgeModel.getTo()).equals(classif[1])) {
					edgeModel.setTipoAresta(ClassificacaoAresta.valueOf(classif[2]));
					edgeModel.setColor(ClassificacaoAresta.valueOf(classif[2]).getColor());
					break;
				}
			}
		}
		return grafo;
	}

	/**
	 * Verifica ciclo
	 * 
	 * @param grafo
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarCiclo/")
	public boolean verificarCiclo(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.hasCiclo(grafo.getOrigem());
	}

	@PostMapping(value = "/matriz/prim/")
	public ArrayList<String> obterAGM(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.Prim(grafo.getOrigem());
	}

	@PostMapping(value = "/matriz/dijkstra/")
	public ArrayList<String> obterDijkstra(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.Dijkstra(grafo.getOrigem());
	}

	@PostMapping(value = "/matriz/ordenacaoTopologica/")
	public LinkedList<String> obterOrdenacaoTopologica(@RequestBody GrafoModel grafo) {
		converteFront(grafo);
		return this.tadGrafo.DFSFromVertice(grafo.getOrigem());
	}

	private void converteFront(GrafoModel grafoModel) {
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
			String a[] = { verticesDict.get(edgeModel.getFrom()), verticesDict.get(edgeModel.getTo()),
					Double.toString(edgeModel.getValue()) };
			arestas.add(a);
		}
		this.tadGrafo.grafo.criarGrafo(vertices, arestas);
	}

}
