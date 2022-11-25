package com.projII_grafo;
import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

// DO NOT FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.
public class StrategyAdjMatrix implements StrategyStructure {

  ArrayList<String> order;
  private ArrayList<ArrayList<Integer>> matrix;
  
  public ArrayList<ArrayList<Integer>> getMatrix() {
	  return matrix;
  }
  public void setMatrix(ArrayList<ArrayList<Integer>> matrix) {
	  this.matrix = matrix;
  }
  public StrategyAdjMatrix() {
	    this.matrix = new ArrayList<ArrayList<Integer>>();
	    this.order = new ArrayList<String>();
	  }

	  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
	    if (this.matrix.size() == 0 && this.order.size() == 0) {
	      this.inserirConjuntoVertices(vertices);
	      if (arestas != null) {
	        this.inserirConjuntoArestas(arestas);
	      }
	    }
	  }

	  public void resetarGrafo() {
	    this.matrix = null;
	    this.order = null;
	  }

	  public ArrayList<String> getAllVertices() {
	    return this.order;
	  }

	  public ArrayList<String> getVerticeAdjacencia(String vertice) {
	    ArrayList<String> adjacencia = new ArrayList<String>();
	    if (this.verticeExists(vertice)) {
	      int index1 = this.order.indexOf(vertice);
	      for (int i = 0; i < this.getVerticeQuantity(); i++) {
	        if (this.getArestaValue(Integer.toString(index1), Integer.toString(i)) != 0) { // se existir aresta
	          adjacencia.add(this.order.get(i));
	        }
	      }
	    } 
	    return adjacencia;
	  }


	  public int getArestaValue(String row, String col) {
	    return this.matrix.get(Integer.valueOf(row)).get(Integer.valueOf(col));
	  }

	  public void inserirVertice(String vertice) {
	    // Insere o vértice sem adicionar coluna
	    this.order.add(vertice);
	    ArrayList<Integer> row = new ArrayList<Integer>();
	    for (int i = 0; i < this.getVerticeQuantity()-1; i++) {
	      row.add(0);
	    }
	    this.matrix.add(row);
	    // Adiciona nova coluna para Matriz de Adjacência
	    for (int j = 0; j < this.getVerticeQuantity(); j++) {
	      this.matrix.get(j).add(0);
	    }
	  }

	  public void inserirConjuntoVertices(ArrayList<String> vertices) {
	    for (String vertice : vertices) {
	      this.inserirVertice(vertice);
	    }  
	  }

	  public void inserirAresta(String n1, String n2, int peso) {
	    if (this.verticeExists(n1) && this.verticeExists(n2)) {
	      if (this.arestaExists(n1, n2)) {
	        throw new UnsupportedOperationException("Simple Graph doesn't accept more than 1 edge"); 
	      }
	      else {
	        int index1 = this.order.indexOf(n1);
	        int index2 = this.order.indexOf(n2);
	        this.matrix.get(index1).set(index2, peso); 
	      }
	    } 
	  }

	  public void inserirConjuntoArestas(ArrayList<String[]> arestas) {
	    for (String[] aresta : arestas) {
	      this.inserirAresta(aresta[0], aresta[1], Integer.valueOf(aresta[2]));
	    }
	  } 
	  
	  public void removerAresta(String n1, String n2) {
	    if (this.arestaExists(n1, n2)) {
	      int index1 = this.order.indexOf(n1);
	      int index2 = this.order.indexOf(n2);
	      this.matrix.get(index1).set(index2, 0);
	    }
	  } 
	 
	  public boolean verticeExists(String n1) {
	    return (this.order.contains(n1))? true : false;
	  } 

	  public boolean arestaExists(String n1, String n2) {
	    if (this.verticeExists(n1) && this.verticeExists(n2)) {
	      String index1 = Integer.toString(this.order.indexOf(n1));
	      String index2 = Integer.toString(this.order.indexOf(n2));
	      return (this.getArestaValue(index1, index2) > 0)? true : false;
	    }
	    return false;
	  } 

	  public int getVerticeQuantity() {
	    return this.order.size();
	  } 

	  public int getArestaQuantity() {
	    int n_arestas = 0;
	    for (ArrayList<Integer> row : this.matrix) { // Percorre a coluna
	      for (int aresta : row) {
	        n_arestas += (aresta > 0)? 1 : 0;
	      }
	    } 
	    return n_arestas; 
	  } 

	  public int getGrau(String n1) {
	    if (this.verticeExists(n1)) {
	      if (this.isDigrafo()) {
	        return this.getGrauEntradaD(n1) + this.getGrauSaidaD(n1);
	      }
	      // Se nao for Digrafo, 
	      // entao pra toda de entrada tem sua saida mapeada.
	      // E entao o codigo acima repetiria aresta.
	      return this.getGrauGeralND(n1);
	    }   
	    return -1; // Maybe should return error instead
	  } 

	  public int getGrauGeralND(String n1) {
	    if (this.verticeExists(n1)) {
	      String index1 = Integer.toString(this.order.indexOf(n1));
	      int grau = 0;
	      for (int i = 0; i < this.getVerticeQuantity(); i++) {
	        int ida = this.getArestaValue(index1, Integer.toString(i));
	        int volta = this.getArestaValue(Integer.toString(i), index1); 
	        if (ida == 0) {
	          continue;
	        } 
	        if (ida == volta) {
	          grau += (Integer.parseInt(index1) == i)? 2 : 1;
	        }
	      }
	      return grau;
	    }  
	    return -1; // Maybe should return error instead  
	  } 

	  public int getGrauEntradaD(String n1) {
	    if (this.verticeExists(n1)) {
	      int index1 = this.order.indexOf(n1);
	      int grau = 0;
	  
	      for (ArrayList<Integer> col : this.matrix) { // Percorre a coluna
	        grau += (col.get(index1) > 0)? 1 : 0;
	      }
	      return grau;
	    } 
	    return -1; // Maybe should return error instead
	  }

	  public int getGrauSaidaD(String n1) {
	    if (this.verticeExists(n1)) {
	      int index1 = this.order.indexOf(n1);
	      int grau = 0;
	  
	      for (Integer row : this.matrix.get(index1)) { // Percorre a linha
	        grau += (row > 0)? 1 : 0;
	      }
	      return grau;
	    } 
	    return -1; // Maybe should return error instead  
	  } 

	  public StrategyAdjMatrix getTransposto() {
	    StrategyAdjMatrix transposto = new StrategyAdjMatrix();
	    transposto.criarGrafo(this.getAllVertices(), null);
	    for (int row = 0; row < this.getVerticeQuantity(); row++) {
	      for (int col = 0; col < this.getVerticeQuantity(); col++) {
	       // if (this.getArestaValue(Integer.toString(row), Integer.toString(col)) == 0) {
	        transposto.inserirAresta(Integer.toString(row), Integer.toString(col),this.getArestaValue(Integer.toString(col), Integer.toString(row)));      
	      }
	    }
	    return transposto;
	  }


	  public boolean isDigrafo() {
	    int order = this.getVerticeQuantity();
	    for (int row = 0; row < order; row++) {
	      for (int col = 0; col < order; col++) {
	        if (this.getArestaValue(Integer.toString(row), Integer.toString(col)) != this.getArestaValue(Integer.toString(col), Integer.toString(row))) {
	          return true; 
	        }
	      }
	    }
	    return false;
	  }

	  @Override
	  public String toString() {
	    String grafo = "";
	    for (ArrayList<Integer> row : this.matrix) {
	      for (Integer value : row) {
	        grafo += Integer.toString(value);
	        grafo += " ";
	      }
	      grafo += "\n";
	    }
	    return grafo;
	  }
	  
	  
	
	@Override
	public void updateArestaBy(int n1, int n2, int peso) {
		
	    this.matrix.get(n1).set(n2, peso);
		
	}
	
	@Override
	public void updateAresta(String n1, String n2, int peso) {
		int index1, index2 = 0;
		index1 = this.order.indexOf(n1);
	    index2 = this.order.indexOf(n2);
	    this.matrix.get(index1).set(index2, peso);
		
	}
	
  
  
  
}
