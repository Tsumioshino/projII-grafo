package com.projII_grafo;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;


// FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.
public class StrategyAdjMatrix implements StrategyStructure {

  ArrayList<String> order;
  ArrayList<ArrayList<Double>> matrix;

  public StrategyAdjMatrix() {
    this.matrix = new ArrayList<ArrayList<Double>>();
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
  public double getArestaValue(String v_origem, String v_destino) {
    int origem_ind = this.order.indexOf(v_origem);
    int destino_ind = this.order.indexOf(v_destino);
    return this.matrix.get(origem_ind).get(destino_ind);
  }
  
  @Override
  public void inserirVertice(String vertice) {
    // Insere o vértice sem adicionar coluna
    this.order.add(vertice);
    ArrayList<Double> row = new ArrayList<Double>();
    for (int i = 0; i < this.getVerticeQuantity()-1; i++) {
      row.add(0d);
    }
    this.matrix.add(row);
    // Adiciona nova coluna para Matriz de Adjacência
    for (int j = 0; j < this.getVerticeQuantity(); j++) {
      this.matrix.get(j).add(0d);
    }
  }

  @Override
  public void inserirConjuntoVertices(ArrayList<String> vertices) {
    for (String vertice : vertices) {
      this.inserirVertice(vertice);
    }  
  }

  @Override
  public void inserirAresta(String v_origem, String v_destino, double peso) {
    if (this.verticeExists(v_origem) && this.verticeExists(v_destino)) {
      if (this.arestaExists(v_origem, v_destino)) {
        throw new UnsupportedOperationException("Simple Graph doesn't accept more than 1 edge"); 
      }
      else {
        int index1 = this.order.indexOf(v_origem);
        int index2 = this.order.indexOf(v_destino);
        this.matrix.get(index1).set(index2, peso); 
      }
    } 
  }

  @Override
  public void inserirConjuntoArestas(ArrayList<String[]> arestas) {
    for (String[] aresta : arestas) {
      this.inserirAresta(aresta[0], aresta[1], Double.valueOf(aresta[2]));
    }
  } 
  
  @Override
  public void removerAresta(String v_origem, String v_destino) {
    if (this.arestaExists(v_origem, v_destino)) {
      int index1 = this.order.indexOf(v_origem);
      int index2 = this.order.indexOf(v_destino);
      this.matrix.get(index1).set(index2, 0d);
    }
  } 
 
  @Override
  public boolean verticeExists(String vertice) {
    return (this.order.contains(vertice))? true : false;
  } 

  @Override
  public boolean arestaExists(String v_origem, String v_destino) {
    if (this.verticeExists(v_origem) && this.verticeExists(v_destino)) {
      return (this.getArestaValue(v_origem, v_destino) > 0)? true : false;
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
    for (ArrayList<Double> row : this.matrix) { // Percorre a coluna
      for (double aresta : row) {
        n_arestas += (aresta > 0)? 1 : 0;
      }
    } 
    return n_arestas; 
  } 

  @Override
  public int getGrau(String vertice) {
    if (this.verticeExists(vertice)) {
      if (this.isDigrafo()) {
        return this.getGrauEntradaD(vertice) + this.getGrauSaidaD(vertice);
      }
      // Se nao for Digrafo, 
      // entao pra toda de entrada tem sua saida mapeada.
      // E entao o codigo acima repetiria aresta.
      return this.getGrauGeralND(vertice);
    }   
    return -1; // Maybe should return error instead
  } 

  @Override
  public int getGrauGeralND(String vertice) {
    if (this.verticeExists(vertice)) {
      int grau = 0;
      for (int i = 0; i < this.getVerticeQuantity(); i++) {
        String destino = this.order.get(i);
        double ida = this.getArestaValue(vertice, destino);
        double volta = this.getArestaValue(destino, vertice); 
        if (ida == 0) {
          continue;
        } 
        if (ida == volta) {
          grau += (vertice.equals(destino))? 2 : 1;
        }
      }
      return grau;
    }  
    return -1; // Maybe should return error instead  
  } 

  @Override
  public int getGrauEntradaD(String vertice) {
    if (this.verticeExists(vertice)) {
      int index1 = this.order.indexOf(vertice);
      int grau = 0;
  
      for (ArrayList<Double> col : this.matrix) { // Percorre a coluna
        grau += (col.get(index1) > 0)? 1 : 0;
      }
      return grau;
    } 
    return -1; // Maybe should return error instead
  }

  @Override
  public int getGrauSaidaD(String vertice) {
    if (this.verticeExists(vertice)) {
      int index1 = this.order.indexOf(vertice);
      int grau = 0;
  
      for (Double value_on_row : this.matrix.get(index1)) { // Percorre a linha
        grau += (value_on_row > 0)? 1 : 0;
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

    for (ArrayList<Double> row : this.matrix) {
      grafo += this.order.get(i);
      grafo += " ";
      for (Double value : row) {
        grafo += Double.toString(value);
        grafo += " ";
      }
      grafo += "\n";
      i++;
    }
    return grafo;
  } 
}
