package com.projII_grafo;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.LinkedList;


// FORGET
// 1. INSERIR VERTICE PERMITE ADICIONAR VERTICE COM NOME IGUAL
// ISSO VAI DAR RUIM EM ALGORITMOS DE BUSCA, PROVAVELMENTE.

public class StrategyAdjList implements StrategyStructure {

  private class VerticeAresta {
    String vertice;
    String peso_aresta;
  
    public VerticeAresta(String vertice, String peso) {
      this.vertice = vertice;
      this.peso_aresta = peso;
    }

    public String getVertice() {
      return this.vertice;
    }

    public String getAresta() {
      return this.peso_aresta;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) { // literalmente mesmo objeto
        return true;
      }
      if (this.getClass() != obj.getClass()) { // devem ser VerticeAresta no minimo
        return false;
      }
      VerticeAresta v_a = (VerticeAresta) obj; // type casting
      if (this.getVertice() != v_a.getVertice()) {
        return false;
      }

      if (this.getAresta() != v_a.getAresta()) {
        return false;
      }

      return true;
    }
  }
  ArrayList<LinkedList<VerticeAresta>> adjList;

  public StrategyAdjList() {
    this.adjList = new ArrayList<LinkedList<VerticeAresta>>();
  }

  public void criarGrafo(ArrayList<String> vertices, ArrayList<String[]> arestas) {
    if (this.adjList.size() == 0) {
      this.inserirConjuntoVertices(vertices);

      if (arestas != null) {
        this.inserirConjuntoArestas(arestas);
      }
    }
  }
  
  public void resetarGrafo() {
    this.adjList = null;
  }

  public ArrayList<String> getAllVertices() {
    ArrayList<String> vertices = new ArrayList<String>();
    for (LinkedList<VerticeAresta> v_adj : this.adjList) {
      for (VerticeAresta cel : v_adj) {
        vertices.add(cel.getVertice());
        break;
      }
    }
    return vertices;
  }

  public ArrayList<String> getVerticeAdjacencia(String vertice) {
    ArrayList<String> adjacencia = new ArrayList<String>();
    if (this.verticeExists(vertice)) {
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        String head = null;
        for (VerticeAresta cel : v_adj) {
          if ((head == null) && ((cel.getVertice()).equals(vertice))) {
            head = cel.getVertice();
            continue;
          }
          if (head == null) {
            break;
          }
          adjacencia.add(cel.getVertice());
        }
      }
    } 
    return adjacencia;
  }

  public int getArestaValue(String head, String tail) {
    if (this.verticeExists(head) && this.verticeExists(tail)) {
      if (this.arestaExists(head, tail)) {
        boolean ishead = true;
        for (LinkedList<VerticeAresta> v_adj : this.adjList) {
          for (VerticeAresta cel : v_adj) {
            if ((cel.getVertice()).equals(head)) {
              ishead = false;
              continue;
            }
            if (!ishead) {
              // Se ele entrar aqui, ele ta percorrendo lista de adjacencia
              // do vertice em questao
              if ((cel.getVertice()).equals(tail)) {
                return Integer.valueOf(cel.getAresta());
              }
              continue;
            }
            break;
          }   
        }
      } 
    }
    return -1; // Maybe should return error instead  
  }

  public void inserirVertice(String vertice) {
    VerticeAresta v_inicial = new VerticeAresta(vertice, null);
    LinkedList<VerticeAresta> celula = new LinkedList<VerticeAresta>();
    celula.add(v_inicial);
    this.adjList.add(celula);  
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
      int index1 = -1;
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        index1++;
        for (VerticeAresta cel : v_adj) {
          if ((cel.getVertice()).equals(n1)) {
            String peso_s = Integer.toString(peso);
            VerticeAresta v_adjacente = new VerticeAresta(n2, peso_s);
            this.adjList.get(index1).add(v_adjacente); 
            return;
          }
          break;
        }
      }
    } 
  } 

  public void inserirConjuntoArestas(ArrayList<String[]> arestas) { 
    for (String[] aresta : arestas) {
      this.inserirAresta(aresta[0], aresta[1], Integer.valueOf(aresta[2]));
    }
  } 

  public void removerAresta(String n1, String n2) { 
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      boolean head = true;
      int index_l = -1;
      int maybe_i = 0;
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        index_l++;
        if (!head) {
          // Se ele entrar aqui, significa que aresta nao existe
          return;
        } 
        for (VerticeAresta cel : v_adj) {
          if ((cel.getVertice()).equals(n1)) {
            head = false;
            continue;
          }
          if (!head) {
            // Se ele entrar aqui, ele ta percorrendo lista de adjacencia
            // do vertice em questao
            maybe_i++;
            if ((cel.getVertice()).equals(n2)) {
              this.adjList.get(index_l).remove(maybe_i);
              return;
            }
            continue;
          }
          break;
        }
      }
    } 
  } 

  public boolean verticeExists(String n1) { 
    for (LinkedList<VerticeAresta> v_adj : this.adjList) {
      for (VerticeAresta cel : v_adj) {
        if ((cel.getVertice()).equals(n1)) {
          return true;
        }
        break;
      }
    } 
    return false;
  } 

  public boolean arestaExists(String n1, String n2) {
    if (this.verticeExists(n1) && this.verticeExists(n2)) {
      boolean head = true;
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        if (!head) {
          // Se ele entrar aqui, significa que aresta nao existe
          return false;
        } 
        for (VerticeAresta cel : v_adj) {
          if ((cel.getVertice()).equals(n1)) {
            head = false;
            continue;
          }
          if (!head) {
            // Se ele entrar aqui, ele ta percorrendo lista de adjacencia
            // do vertice em questao
            if ((cel.getVertice()).equals(n2)) {
              return true;
            }
            continue;
          }
          break;
        }
      }
    }
    return false;   
  } 

  public int getVerticeQuantity() {
    return this.adjList.size();
  } 

  public int getArestaQuantity() {
    int size = 0;
    if (this.isDigrafo()) {
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        size += v_adj.size() - 1; // Desconsiderando a cabeca por ser um Vertice
      }
    }
    else {
      ArrayList<String> visited = new ArrayList<String>();
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        visited.add(v_adj.getFirst().getVertice());
        for (VerticeAresta cel : v_adj) {
          if (visited.contains(cel.getVertice())) {
            continue;
          }
          size += 1;
        }
      }
    }
    return size;
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
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        for (VerticeAresta cel : v_adj) {
          if ((cel.getVertice()).equals(n1)) {
            return v_adj.size() - 1; // Desconsiderando a cabeca por ser um Vertice
          }
        }
      }  
    } 
    return -1; // Maybe should return error instead  }
  } 

  public int getGrauEntradaD(String n1) { 
    if (this.verticeExists(n1)) {
      int grau = 0;
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        String head = null;
        for (VerticeAresta cel : v_adj) {
          if ((head == null) && ((cel.getVertice()).equals(n1))) {
            head = cel.getVertice();
            continue;
          } 
          grau += ((cel.getVertice()).equals(head))? 2 : 1;
        }
        if (head != null) {
          return grau;
        }
      }     
    } 
    return -1; // Maybe should return error instead
  } 

  public int getGrauSaidaD(String n1) { 
    if (this.verticeExists(n1)) {
      int grau = 0;
      for (LinkedList<VerticeAresta> v_adj : this.adjList) {
        String head = null;
        for (VerticeAresta cel : v_adj) {
          if (head == null) {
            head = cel.getVertice();
            continue;
          } 
          grau += ((cel.getVertice()).equals(n1))? 1 : 0;
        }
      }  
      return grau;   
    } 
    return -1; // Maybe should return error instead  } 
  }

  public StrategyAdjList getTransposto() {
    StrategyAdjList transposto = new StrategyAdjList();
    transposto.criarGrafo(this.getAllVertices(), null);
    for (LinkedList<VerticeAresta> v_adj : this.adjList) {
      String head = null;
      for (VerticeAresta cel : v_adj) {
        if (head == null) {
          head = cel.getVertice();
          continue;
        }
        VerticeAresta the_head = new VerticeAresta(cel.getVertice(), null); // Vertice que possui uma aresta vindo
        int index_of_tail = -1;
        int index_transpose = -1;
        for (LinkedList<VerticeAresta> v_adj2 : this.adjList) {
          index_of_tail++;
          if ((v_adj2.get(0)).equals(the_head)) {
            index_transpose = index_of_tail; // Index do Vertice que possui uma aresta vindo
            break;
          }
        }
        VerticeAresta aresta_transposta = new VerticeAresta(head, cel.getAresta()); // Inverte a aresta com o peso
        transposto.adjList.get(index_transpose).add(aresta_transposta); // E coloca a aresta no Vertice que possuia uma aresta vindo (agora saindo)
      }
    }
    return transposto;
  }

  public boolean isDigrafo() {
    String head = null;
    for (LinkedList<VerticeAresta> v_adj : this.adjList) {
      for (VerticeAresta cel : v_adj) {
        if (head == null) {
          head = cel.getVertice();
          continue;
        }
        String adj_node = cel.getVertice();
        if (!this.arestaExists(adj_node, head)) {
          return true;
        }
      }
    }
    return false;
  } 

  @Override
  public String toString() {
    String grafo = "";
    for (LinkedList<VerticeAresta> v_adj : this.adjList) {
      for (VerticeAresta cel : v_adj) {
        grafo += cel.getVertice();
        grafo += ", ";
        grafo += cel.getAresta();
        grafo += " ";
      }
      grafo += "\n";
    } 
    return grafo;  
  } 
}
