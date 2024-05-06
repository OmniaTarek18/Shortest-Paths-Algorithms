package test.correctness;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.AllShortestPaths;
import main.Graph;
import test.efficiency.GraphGenerator;

public class BellmanFordCorrectness {
    @Test
    public void LargeWithNegativeCycle() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\Large + Negative cycle.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run bellman algorithm
        boolean cycle = graph.BellmanFord(src, cost, parents);
        // Assert results
        assertTrue(cycle);

    }

    @Test
    public void LargeWithNegativeCycle2() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\Large + NegativeCycle 2.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run bellman algorithm
        boolean cycle = graph.BellmanFord(src, cost, parents);
        // Assert results
        assertTrue(cycle);

    }

    @Test
    public void bidirectionalDifferentWeights() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\BidirectionalEdgesDifferentWeights.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run bellman algorithm
        graph.BellmanFord(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 5, 6, 13, 5, 2, 5, 12, 5, 1 };
        int[] expectedParents = { 0, 0, 0, 1, 9, 9, 5, 4, 5, 0 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void RandomLargeDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\LargeDenseBellman.txt";
        Graph graph = new Graph(relativePath);
        int[] costs = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        graph.BellmanFord(526, costs, parents);
        // assert the result for 526
        try {
            int[] expectedCosts = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeDense526Bellman.txt");
            assertArrayEquals(expectedCosts, costs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        graph.BellmanFord(323, costs, parents);
        // assert the result for 323
        try {
            int[] expectedCosts = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeDense323Bellman.txt");
            assertArrayEquals(expectedCosts, costs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SmallSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallSparseBellman.txt";
        Graph graph = new Graph(relativePath);
        int[] costs = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        graph.BellmanFord(24, costs, parents);
        // assert costs for 24
        try {
            int[] expectedCosts = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallSparse24Bellman.txt");
            assertArrayEquals(expectedCosts, costs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        graph.BellmanFord(78, costs, parents);
        // assert costs for 78
        try {
            int[] expectedCosts = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallSparse78Bellman.txt");
            assertArrayEquals(expectedCosts, costs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // all pairs
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.BellmanFord_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallSparseBellman.txt",
                graph.Size());
        System.out.println(expected.size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void LargeSparce() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\LargeSparseBellman.txt";
        Graph graph = new Graph(relativePath);
        // all pairs
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.BellmanFord_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeSparseBellman.txt",
                graph.Size());
        System.out.println(expected.size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void MediumDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumDenseBellman.txt";
        Graph graph = new Graph(relativePath);
        // all pairs
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.BellmanFord_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumDenseBellman.txt",
                graph.Size());
        System.out.println(expected.size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void MediumSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumSparseBellman.txt";
        Graph graph = new Graph(relativePath);
        // all pairs
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.BellmanFord_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumSparseBellman.txt",
                graph.Size());
        System.out.println(expected.size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void SmallDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallDenseBellman.txt";
        Graph graph = new Graph(relativePath);
        // all pairs
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.BellmanFord_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\SmallDenseBellman.txt",
                graph.Size());
        System.out.println(expected.size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void MultipleShortPaths() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MultipleShortestPathsD.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run bellman algorithm
        graph.BellmanFord(src, cost, parents);
        List<Integer> path = graph.constructPath(5, src, parents);

        // Assert results
        int[][] expectedPathOptions = {
                { 0, 2, 3, 5 },
                { 0, 1, 3, 5 },
                { 0, 2, 4, 5 }
        };

        boolean found = false;
        for (int[] option : expectedPathOptions) {
            List<Integer> expectedPathList = new ArrayList<>();
            for (int value : option) {
                expectedPathList.add(value);
            }
            if (path.equals(expectedPathList)) {
                found = true;
                break;
            }
        }

        assertTrue(found);

    }
    @Test
    public void NegativeEdges() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\GraphWithNegativeEdges.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.BellmanFord(src, cost, parents);
        // Assert results
        int[] expectedCost = {0,-2,-3,-6};
        int[] expectedParents = {0,0,1,2};
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }
    @Test
    public void positiveCycles() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\positiveCycleD.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.BellmanFord(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 1, 3, 2, 6 };
        int[] expectedParents = { 0, 0, 1, 0, 3 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

}
