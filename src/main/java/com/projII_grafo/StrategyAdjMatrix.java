package com.projII_grafo;
import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

// DO NOT FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.
public class StrategyAdjMatrix implements StrategyStructure {

  ArrayList<String> order;
  ArrayList<ArrayList<Integer>> matrix;

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

  public int getVerticeValue(int row, int col) {
    return this.matrix.get(row).get(col);
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
      int index1 = this.order.indexOf(n1);
      int index2 = this.order.indexOf(n2);
      return (this.getVerticeValue(index1, index2) > 0)? true : false;
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
      int index1 = this.order.indexOf(n1);
      int grau = 0;
      for (int i = 0; i < this.getVerticeQuantity(); i++) {
        int ida = this.getVerticeValue(index1, i);
        int volta = this.getVerticeValue(i, index1); 
        if (ida == 0) {
          continue;
        } 
        if (ida == volta) {
          grau += (index1 == i)? 2 : 1;
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

  public boolean isDigrafo() {
    int order = this.getVerticeQuantity();
    for (int n_row = 0; n_row < order; n_row++) {
      for (int n_col = 0; n_col < order; n_col++) {
        if (this.getVerticeValue(n_row, n_col) != this.getVerticeValue(n_col, n_row)) {
          return false;
        }
      }
    }
    return true;
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
}