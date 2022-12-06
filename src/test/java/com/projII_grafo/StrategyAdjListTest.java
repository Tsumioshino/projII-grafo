package com.projII_grafo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StrategyAdjListTest {

    StrategyAdjList grafo_adj = new StrategyAdjList();

    
	/** 
	 * @throws Exception
	 */
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


	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testVerticeAresta() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetVertice() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetAresta() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testStrategyAdjList() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testResetarGrafo() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetAllVertices() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetVerticeAdjacencia() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetArestaValue() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testInserirVertice() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testInserirConjuntoVertices() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testInserirAresta() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testInserirConjuntoArestas() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testRemoverAresta() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testVerticeExists() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testArestaExists() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetVerticeQuantity() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetArestaQuantity() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetGrau() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetGrauGeralND() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetGrauEntradaD() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetGrauSaidaD() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testIsDigrafo() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testToString() throws Exception {

	}

	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetTransposto() throws Exception {

	}
}
