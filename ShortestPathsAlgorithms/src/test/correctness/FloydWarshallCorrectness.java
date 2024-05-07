package test.correctness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import main.Graph;
import test.efficiency.GraphGenerator;

public class FloydWarshallCorrectness {
    // multiple edges between the same two nodes
    @Test
    public void bidirectionalDifferentWeights() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\BidirectionalEdgesDifferentWeights.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\BidirectionalFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void SmallSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallSparseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallSparseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void SmallDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallDenseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallDenseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                System.out.println(i + " " + j);
                System.out.println(cost[i][j]);
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void MediumSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumSparseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumSparseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void MediumDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumDenseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumDenseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void LargeSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\LargeSparseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeSparseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void LargeDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\LargeDenseFloyd.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // Read expected results from the answer file
        // assert the costs
        int[][] expectedCosts = GraphGenerator.readDistancesFromFile(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeDenseFloyd.txt",
                graph.Size());

        // Assert results
        for (int i = 0; i < graph.Size(); i++) {
            for (int j = 0; j < graph.Size(); j++) {
                assertEquals(expectedCosts[i][j], cost[i][j]);
            }
        }

    }

    @Test
    public void LargeWithNegativeCycle() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\Large + Negative cycle.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];
        // Run Dijkstra algorithm
        boolean cycle = graph.FloydWarshall(cost, parents);
        // Assert results
        assertTrue(!cycle);

    }

    @Test
    public void LargeWithNegativeCycle2() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\Large + NegativeCycle 2.txt";
        Graph graph = new Graph(relativePath);
        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];
        // Run Dijkstra algorithm
        boolean cycle = graph.FloydWarshall(cost, parents);
        // Assert results
        assertTrue(!cycle);

    }

    @Test
    public void negativeCycle() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\NegativeCycle.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];
        // Run Dijkstra algorithm
        boolean cycle = graph.FloydWarshall(cost, parents);
        // Assert results
        assertTrue(!cycle);

    }

    @Test
    public void NegativeEdges() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\GraphWithNegativeEdges.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // assert the costs
        int[] expectedCosts = { 0, -2, -3, -6 };
        int[] expectedParents = { 0, 1, 1, 1 };
        // Assert results for node 0

        for (int j = 0; j < graph.Size(); j++) {
            assertEquals(expectedCosts[j], cost[0][j]);
        }

        for (int j = 0; j < graph.Size(); j++) {
            assertEquals(expectedParents[j], parents[0][j]);
        }

    }

    @Test
    public void positiveCycles() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\positiveCycleD.txt";
        Graph graph = new Graph(relativePath);
        // Initialize arrays to store costs and parents
        int[][] cost = new int[graph.Size()][graph.Size()];
        int[][] parents = new int[graph.Size()][graph.Size()];

        // Run Floyd-Warshall algorithm
        boolean noNegativeCycles = graph.FloydWarshall(cost, parents);
        // Assert that there are no negative cycles
        assertTrue(noNegativeCycles);

        // assert the costs
        int[] expectedCosts = { 0, 1, 3, 2, 6 };
        int[] expectedParents = { 0, 1, 1, 3, 3 };
        // Assert results for node 0

        for (int j = 0; j < graph.Size(); j++) {
            assertEquals(expectedCosts[j], cost[0][j]);
        }

        for (int j = 0; j < graph.Size(); j++) {
            assertEquals(expectedParents[j], parents[0][j]);
        }

    }

}
