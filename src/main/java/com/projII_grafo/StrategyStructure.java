package com.projII_grafo;

import java.util.ArrayList;

interface StrategyStructure {
  
  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas); 

  public void inserirAresta(String n1, String n2, int peso); 

  public void inserirConjuntoArestas(ArrayList<String[]> arestas); 

  public void removerAresta(String n1, String n2); 

  public boolean verticeExists(String n1); 

  public boolean arestaExists(String n1, String n2); 

  public int getVerticeQuantity(); 

  public int getArestaQuantity(); 

  public int getGrau(String n1); 

  public int getGrauGeralND(String n1); 

  public int getGrauEntradaD(String n1); 

  public int getGrauSaidaD(String n1); 

  public boolean isDigrafo(); 

  @Override
  public String toString(); 
}
