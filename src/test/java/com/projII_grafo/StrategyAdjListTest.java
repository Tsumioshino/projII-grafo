package com.projII_grafo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StrategyAdjListTest {

    StrategyAdjList grafo_adj = new StrategyAdjList();

    @Test
	public void testCriarGrafo() throws Exception {
        ArrayList<String> vertices = new ArrayList<>();
        ArrayList<String[]> arestas = new ArrayList<>();

        vertices.add("a");

        String arestass[] = {"a", "a", "0"};

        arestas.add(arestass);

        grafo_adj.criarGrafo(vertices, arestas);
        
        System.out.println(grafo_adj);
	}


	@Test
	public void testVerticeAresta() throws Exception {

	}

	@Test
	public void testGetVertice() throws Exception {

	}

	@Test
	public void testGetAresta() throws Exception {

	}

	@Test
	public void testStrategyAdjList() throws Exception {

	}

	@Test
	public void testResetarGrafo() throws Exception {

	}

	@Test
	public void testGetAllVertices() throws Exception {

	}

	@Test
	public void testGetVerticeAdjacencia() throws Exception {

	}

	@Test
	public void testGetArestaValue() throws Exception {

	}

	@Test
	public void testInserirVertice() throws Exception {

	}

	@Test
	public void testInserirConjuntoVertices() throws Exception {

	}

	@Test
	public void testInserirAresta() throws Exception {

	}

	@Test
	public void testInserirConjuntoArestas() throws Exception {

	}

	@Test
	public void testRemoverAresta() throws Exception {

	}

	@Test
	public void testVerticeExists() throws Exception {

	}

	@Test
	public void testArestaExists() throws Exception {

	}

	@Test
	public void testGetVerticeQuantity() throws Exception {

	}

	@Test
	public void testGetArestaQuantity() throws Exception {

	}

	@Test
	public void testGetGrau() throws Exception {

	}

	@Test
	public void testGetGrauGeralND() throws Exception {

	}

	@Test
	public void testGetGrauEntradaD() throws Exception {

	}

	@Test
	public void testGetGrauSaidaD() throws Exception {

	}

	@Test
	public void testIsDigrafo() throws Exception {

	}

	@Test
	public void testToString() throws Exception {

	}

	@Test
	public void testGetTransposto() throws Exception {

	}
}
