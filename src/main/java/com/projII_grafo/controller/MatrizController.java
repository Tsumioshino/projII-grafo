package com.projII_grafo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
	 * @param grafoModel
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarAresta/")
	public boolean verificarAresta(@RequestBody GrafoModel grafoModel) { // ja fiz
		converteFront(grafoModel);
		return this.tadGrafo.grafo.arestaExists(grafoModel.getOrigem(), grafoModel.getDestino());
	}

	/**
	 * @param grafoModel
	 * @return ArrayList<String>
	 */
	@PostMapping(value = "/matriz/obterListaAdj/") // ja fiz
	public ArrayList<String> obterListaAdj(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		return this.tadGrafo.grafo.getVerticeAdjacencia(grafoModel.getOrigem());
	}

	/**
	 * @param grafoModel
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/quantidadeVerticesArestas/") // fiz
	public GrafoModel quantidadeVerticesArestas(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		grafoModel.setQuantidadeAresta(this.tadGrafo.grafo.getArestaQuantity());
		grafoModel.setQuantidadeVertice(this.tadGrafo.grafo.getVerticeQuantity());
		return grafoModel;
	}

	/**
	 * @param grafoModel
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/buscaLargura/") // fiz
	public ArrayList<String> buscaLargura(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		tadGrafo.BFS(grafoModel.getOrigem(), grafoModel.getDestino());
		return tadGrafo.respostaBFS;
	}

	/**
	 * @param grafoModel
	 * @return int
	 */
	@PostMapping(value = "/matriz/obterGrauVertice/") // fiz
	public int obterGrauVertice(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		return this.tadGrafo.grafo.getGrau(grafoModel.getOrigem());
	}

	/**
	 * @param grafoModel
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/obterClassificacaoAresta/") // ja fiz
	public GrafoModel obterClassificacaoAresta(@RequestBody GrafoModel grafoModel) {
		Map<Integer, String> verticesDict = new HashMap<>();
		converteFront(grafoModel);
		for (NodeModel node : grafoModel.getNodes()) {
			verticesDict.put(node.getId(), node.getLabel());
		}
		String[] classificacoes = this.tadGrafo.classificarAresta(grafoModel.getOrigem()).split("\n");
		for (EdgeModel edgeModel : grafoModel.getEdges()) {
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
		return grafoModel;
	}

	/**
	 * Verifica ciclo
	 * 
	 * @param grafoModel
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarCiclo/")
	public boolean verificarCiclo(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		return this.tadGrafo.hasCiclo(grafoModel.getOrigem());
	}

	@PostMapping(value = "/matriz/prim/")
	public ArrayList<String> obterAGM(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		return this.tadGrafo.Prim(grafoModel.getOrigem());
	}
	
	@PostMapping(value = "/matriz/dijkstraPesosAtt/")
	public GrafoModel obterDijkstraPesosAtt(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		Map<Integer, String> verticesDict = new HashMap<>();
		for (NodeModel node : grafoModel.getNodes()) {
			verticesDict.put(node.getId(), node.getLabel());
		}
		for (String	resposta : this.tadGrafo.Dijkstra(grafoModel.getOrigem())) {
			String[] respostas = resposta.split(" ");
			for (EdgeModel edgeModel : grafoModel.getEdges()) {
				if (edgeModel.getTo() == edgeModel.getFrom()) {
					grafoModel.getEdges().remove(edgeModel);
					break;
				}
				if (verticesDict.get(edgeModel.getFrom()).equals(respostas[0]) && 
				verticesDict.get(edgeModel.getTo()).equals(respostas[1])) {
					edgeModel.setLabel(respostas[2]);
					edgeModel.setValue(Double.parseDouble(respostas[2]));
					break;
				}
			}
		}
		return grafoModel;
	}

	@PostMapping(value = "/matriz/dijkstra/")
	public ArrayList<String> obterDijkstra(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		return this.tadGrafo.Dijkstra(grafoModel.getOrigem());
	}

	@PostMapping(value = "/matriz/fortementeConexo/")
	public GrafoModel obterFortementeConexo(@RequestBody GrafoModel grafoModel) {
		converteFront(grafoModel);
		ArrayList<ArrayList<String>> grupos = this.tadGrafo.DFStrongyConnected(grafoModel.getOrigem());
		Set<String> coresExistentes = new HashSet<>();
		for (ArrayList<String> grupo : grupos) {
			System.out.println("O GRUPO TEM TAMANHO: " + grupo.size());
			String cor = generateColor();
			while(coresExistentes.contains(cor)){
				cor = generateColor();
			}
			coresExistentes.add(cor);
			for (String label : grupo) {
				for (NodeModel nodeModel : grafoModel.getNodes()) {
					if(nodeModel.getLabel().equals(label)){
						nodeModel.setColor(cor);
					}
				}
			}
		}
		return grafoModel;
	}

	@PostMapping(value = "/matriz/ordenacaoTopologica/")
	public LinkedList<String> obterOrdenacaoTopologica(@RequestBody GrafoModel grafoModel) {
		return this.tadGrafo.DFSFromVertice(grafoModel.getOrigem()); // ordenacaoTopologica
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

	private String generateColor(){
		Random random = new Random();

		// create a big random number - maximum is ffffff (hex) = 16777215 (dez)
		int nextInt = random.nextInt(0xffffff + 1);
		
		// format it as hexadecimal string (with hashtag and leading zeros)
		return String.format("#%06x", nextInt);
	}
}
