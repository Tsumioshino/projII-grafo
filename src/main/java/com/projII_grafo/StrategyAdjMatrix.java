package com.projII_grafo;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;


// FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.
public class StrategyAdjMatrix implements StrategyStructure {

  ArrayList<String> order;
  ArrayList<ArrayList<Integer>> matrix;

  public StrategyAdjMatrix() {
    this.matrix = new ArrayList<ArrayList<Integer>>();
    this.order = new ArrayList<String>();
  }

  @Override
  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
    if (this.matrix.size() == 0 && this.order.size() == 0) {
      this.inserirConjuntoVertices(vertices);
      if (arestas != null) {
        this.inserirConjuntoArestas(arestas);
      }
    }
  }

  @Override
  public void resetarGrafo() {
    this.matrix = null;
    this.order = null;
  }

  @Override
  public ArrayList<String> getAllVertices(){
    return this.order;
  }

  @Override
  public ArrayList<String> getVerticeAdjacencia(String vertice) {
    ArrayList<String> adjacencia = new ArrayList<String>();
    if (this.verticeExists(vertice)) {
      for (int i = 0; i < this.getVerticeQuantity(); i++) {
        String destino = this.order.get(i);
        if (this.getArestaValue(vertice, destino) != 0) { // se existir aresta
          adjacencia.add(destino);
        }
      }
    } 
    return adjacencia;
  }


  @Override
  public int getArestaValue(String origem, String destino) {
    int origem_ind = this.order.indexOf(origem);
    int destino_ind = this.order.indexOf(destino);
    return this.matrix.get(origem_ind).get(destino_ind);
  }
  
  @Override
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

  @Override
  public void inserirConjuntoVertices(ArrayList<String> vertices) {
    for (String vertice : vertices) {
      this.inserirVertice(vertice);
    }  
  }

  @Override
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

  @Override
  public void inserirConjuntoArestas(ArrayList<String[]> arestas) {
    for (String[] aresta : arestas) {
      this.inserirAresta(aresta[0], aresta[1], Integer.valueOf(aresta[2]));
    }
  } 
  
  @Override
  public void removerAresta(String n1, String n2) {
    if (this.arestaExists(n1, n2)) {
      int index1 = this.order.indexOf(n1);
      int index2 = this.order.indexOf(n2);
      this.matrix.get(index1).set(index2, 0);
    }
  } 
 
  @Override
  public boolean verticeExists(String n1) {
    return (this.order.contains(n1))? true : false;
  } 

  @Override
  public boolean arestaExists(String n1, String n2) {
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      return (this.getArestaValue(n1, n2) > 0)? true : false;
    }
    return false;
  } 

  @Override
  public int getVerticeQuantity() {
    return this.order.size();
  } 

  @Override
  public int getArestaQuantity() {
    int n_arestas = 0;
    for (ArrayList<Integer> row : this.matrix) { // Percorre a coluna
      for (int aresta : row) {
        n_arestas += (aresta > 0)? 1 : 0;
      }
    } 
    return n_arestas; 
  } 

  @Override
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

  @Override
  public int getGrauGeralND(String n1) {
    if (this.verticeExists(n1)) {
      int grau = 0;
      for (int i = 0; i < this.getVerticeQuantity(); i++) {
        String destino = this.order.get(i);
        int ida = this.getArestaValue(n1, destino);
        int volta = this.getArestaValue(destino, n1); 
        if (ida == 0) {
          continue;
        } 
        if (ida == volta) {
          grau += (n1.equals(destino))? 2 : 1;
        }
      }
      return grau;
    }  
    return -1; // Maybe should return error instead  
  } 

  @Override
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

  @Override
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

  @Override
  public StrategyAdjMatrix getTransposto() {
    StrategyAdjMatrix transposto = new StrategyAdjMatrix();

    transposto.criarGrafo(this.getAllVertices(), null);
   
    for (int row = 0; row < this.getVerticeQuantity(); row++) {
      for (int col = 0; col < this.getVerticeQuantity(); col++) {
        String origem = this.order.get(row);
        String destino = this.order.get(col);
        transposto.inserirAresta(origem, 
                                destino,
                                this.getArestaValue(destino, origem));  

      }
    }
    return transposto;
  }


  @Override
  public boolean isDigrafo() {
    int order = this.getVerticeQuantity();
    for (int row = 0; row < order; row++) {
      for (int col = 0; col < order; col++) {
        String origem = this.order.get(row);
        String destino = this.order.get(col);
        if (this.getArestaValue(origem, destino)
         != this.getArestaValue(destino, origem)) {
          return true; 
        }
      }
    }
    return false;
  }
  
  @Override
  public String toString() {
    String grafo = "  ";
    int i = 0;
    for (String v : this.order) {
      grafo += v;
      grafo += " ";
    }
    grafo += "\n";

    for (ArrayList<Integer> row : this.matrix) {
      grafo += this.order.get(i);
      grafo += " ";
      for (Integer value : row) {
        grafo += Integer.toString(value);
        grafo += " ";
      }
      grafo += "\n";
      i++;
    }
    return grafo;
  } 
}
