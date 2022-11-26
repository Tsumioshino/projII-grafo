package com.projII_grafo;

import java.util.List;

import java.util.ArrayList;

public interface StrategyStructure {
  
  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas); 

  public void resetarGrafo(); 

  public ArrayList<String> getAllVertices(); 

  public ArrayList<String> getVerticeAdjacencia(String n1); 

  public int getArestaValue(String li, String el);
  
  public int getArestaValue(int row, int col);

  public void inserirVertice(String vertice); 

  public void inserirConjuntoVertices(ArrayList<String> vertices); 

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

  public StrategyStructure getTransposto();

  public boolean isDigrafo();
  
  public void updateAresta(String n1, String n2, int peso);
  
  public void updateAresta(int n1, int n2, int peso); 
  }

 


