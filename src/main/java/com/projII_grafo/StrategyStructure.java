package com.projII_grafo;

import java.util.ArrayList;

public interface StrategyStructure {
  
  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas); 

  public void resetarGrafo(); 

  public ArrayList<String> getAllVertices(); 

  public ArrayList<String> getVerticeAdjacencia(String vertice); 

  public double getArestaValue(String v_origem, String v_destino);

  public void inserirVertice(String vertice); 

  public void inserirConjuntoVertices(ArrayList<String> vertices); 

  public void inserirAresta(String v_origem, String v_destino, double peso); 

  public void inserirConjuntoArestas(ArrayList<String[]> arestas); 

  public void removerAresta(String v_origem, String v_destino); 

  public boolean verticeExists(String vertice); 

  public boolean arestaExists(String v_origem, String v_destino); 

  public int getVerticeQuantity(); 

  public int getArestaQuantity(); 

  public int getGrau(String vertice); 

  public int getGrauGeralND(String vertice); 

  public int getGrauEntradaD(String vertice); 

  public int getGrauSaidaD(String vertice); 

  public StrategyStructure getTransposto();

  public StrategyStructure getNotDigrafo();

  public boolean isDigrafo();
}

 


