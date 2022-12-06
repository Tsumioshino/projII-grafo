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
import com.projII_grafo.model.ClassificacaoAresta;
import com.projII_grafo.model.EdgeModel;
import com.projII_grafo.model.GrafoModel;
import com.projII_grafo.model.NodeModel;

@RestController
public class MatrizController {

	private TADGrafo tadGrafo;

	
	/** 
	 * @return String
	 */
	@GetMapping("/")
	public String home(){
		return "oi";
	}

	
	/** 
	 * @param teste
	 */
	@PostMapping("/")
	public void retornaAll(@RequestBody String teste){
		System.out.println(teste);
	}
    
	
	/** 
	 * @param grafo
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarAresta/")
    public boolean verificarAresta(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		return this.tadGrafo.grafo.arestaExists(grafo.getOrigem(), grafo.getDestino());
    }

	
	/** 
	 * @param grafo
	 * @return ArrayList<String>
	 */
	@PostMapping(value = "/matriz/obterListaAdj/")
    public ArrayList<String> obterListaAdj(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		return this.tadGrafo.grafo.getVerticeAdjacencia(grafo.getOrigem());
    }

	
	/** 
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/quantidadeVerticesArestas/")
    public GrafoModel quantidadeVerticesArestas(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		grafo.setQuantidadeAresta(this.tadGrafo.grafo.getArestaQuantity());
		grafo.setQuantidadeVertice(this.tadGrafo.grafo.getVerticeQuantity());
		return grafo;
    }

	
	/** 
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/buscaLargura/")
    public GrafoModel buscaLargura(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		tadGrafo.BFS(grafo.getOrigem(), grafo.getDestino());
		grafo.setQuantidadeAresta(this.tadGrafo.grafo.getArestaQuantity());
		grafo.setQuantidadeVertice(this.tadGrafo.grafo.getVerticeQuantity());
		return grafo;
    }

	
	/** 
	 * @param grafo
	 * @return int
	 */
	@PostMapping(value = "/matriz/obterGrauVertice/")
    public int obterGrauVertice(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		return this.tadGrafo.grafo.getGrau(grafo.getOrigem());
    }
	

	
	/** 
	 * @param grafo
	 * @return GrafoModel
	 */
	@PostMapping(value = "/matriz/obterClassificacaoAresta/")
    public GrafoModel obterClassificacaoAresta(@RequestBody GrafoModel grafo){
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
					break;
				}
			}
		}
		return grafo;
    }

	
	/** 
	 * Verifica ciclo
	 * @param grafo
	 * @return boolean
	 */
	@PostMapping(value = "/matriz/verificarCiclo/")
    public boolean verificarCiclo(@RequestBody GrafoModel grafo){
		converteFront(grafo);
		return this.tadGrafo.hasCiclo(grafo.getOrigem());
    }


	/*
	 * Verifica ord
	 */
	//@PostMapping(value = "/matriz/obterOrdenacaoTopologica/")
    //public String obterOrdenacaoTopologica(@RequestBody GrafoModel grafo){
	//	converteFront(grafo);
	//	return this.tadGrafo.ordenacaoTopologica(grafo.getOrigem());
    //}
	

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
