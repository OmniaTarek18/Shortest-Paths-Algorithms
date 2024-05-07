package test.correctness;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.AllShortestPaths;
import main.Graph;

import test.efficiency.GraphGenerator;

public class DijkstraCorrectness {
    /* Small size dense graph n = 100 */
    @Test
    public void SmallDenseGraph() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallDenseGraphD.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        // source = 49
        graph.Dijkstra(49, cost, parents);
        // Assert results
        int[] expectedCost = { Integer.MAX_VALUE, 5, 4, 8, 11, 8, 9, 8, 9, 9, 9, 6, 10, 9, 5, 7, 9, 9, 8, 8, 11, 6, 9,
                10, 9, 7, 9, 9, 10, 8, 10, 9, 8, 10, 7, 9, 8, 6, 9, 8, 9, 10, 9, 5, 11, 11, 7, 11, 9, 0, 2, 10, 8, 7, 8,
                8, 9, 9, 10, 12, 5, 11, 11, 6, 5, 12, 7, 8, 10, 8, 9, 7, 12, 7, 10, 4, 6, 8, 10, 8, 9, 9, 5, 10, 8, 12,
                10, 8, 7, 5, 6, 7, 5, 10, 11, 7, 10, 9, 12, 10, 10 };

        assertArrayEquals(expectedCost, cost);
        // source = 1
        graph.Dijkstra(1, cost, parents);
        // Assert results
        int[] expectedCost2 = { Integer.MAX_VALUE, 0, 8, 9, 11, 8, 8, 12, 8, 12, 9, 7, 11, 12, 8, 10, 10, 10, 4, 9, 12,
                7, 12, 9, 10, 10, 9, 11, 11, 7, 10, 10, 7, 10, 7, 8, 8, 9, 9, 8, 12, 10, 10, 10, 8, 14, 11, 7, 11, 12,
                8, 12, 8, 11, 11, 3, 9, 11, 10, 12, 9, 10, 11, 5, 6, 15, 2, 12, 9, 7, 10, 7, 10, 8, 9, 14, 8, 8, 11, 8,
                8, 10, 8, 10, 7, 8, 10, 9, 8, 12, 9, 8, 9, 9, 10, 9, 6, 10, 9, 5, 13 };

        assertArrayEquals(expectedCost2, cost);
        // source = 50
        graph.Dijkstra(50, cost, parents);
        // Assert results
        int[] expectedCost3 = { Integer.MAX_VALUE, 10, 9, 6, 12, 10, 10, 9, 10, 7, 8, 4, 9, 7, 10, 10, 9, 7, 6, 6, 9, 4,
                9, 9, 7, 5, 7, 7, 8, 9, 8, 7, 9, 8, 9, 10, 6, 10, 8, 7, 12, 8, 11, 8, 10, 9, 10, 9, 9, 10, 0, 11, 6, 8,
                8, 10, 8, 10, 8, 10, 10, 10, 10, 7, 3, 14, 8, 9, 11, 9, 7, 5, 11, 5, 10, 9, 4, 10, 9, 7, 8, 7, 3, 8, 8,
                10, 11, 8, 5, 8, 6, 5, 6, 9, 9, 7, 9, 7, 10, 11, 12 };

        assertArrayEquals(expectedCost3, cost);
        // source = 84
        graph.Dijkstra(84, cost, parents);
        // Assert results
        int[] expectedCost4 = { Integer.MAX_VALUE, 9, 8, 6, 8, 10, 7, 7, 7, 7, 8, 4, 8, 6, 7, 3, 8, 5, 6, 7, 9, 5, 9, 6,
                5, 5, 8, 8, 6, 6, 9, 8, 9, 4, 9, 9, 6, 8, 8, 7, 5, 8, 9, 8, 10, 8, 6, 10, 7, 9, 1, 9, 7, 9, 7, 10, 5,
                11, 7, 9, 7, 11, 7, 7, 4, 11, 7, 9, 6, 10, 7, 6, 10, 5, 8, 8, 5, 10, 7, 7, 7, 7, 4, 8, 0, 8, 4, 5, 5, 9,
                7, 6, 6, 2, 9, 8, 9, 7, 10, 6, 6 };

        assertArrayEquals(expectedCost4, cost);
        // source = 64
        graph.Dijkstra(64, cost, parents);
        // Assert results
        int[] expectedCost5 = { Integer.MAX_VALUE, 9, 6, 3, 9, 7, 7, 7, 7, 10, 7, 1, 6, 6, 7, 8, 6, 6, 3, 3, 6, 1, 6, 8,
                6, 4, 4, 10, 6, 6, 9, 4, 6, 5, 6, 7, 5, 8, 6, 5, 9, 7, 9, 5, 7, 8, 9, 7, 6, 10, 7, 8, 6, 5, 5, 7, 6, 7,
                5, 7, 8, 7, 8, 4, 0, 11, 5, 6, 8, 6, 4, 2, 8, 2, 8, 8, 2, 7, 6, 6, 7, 4, 2, 8, 6, 8, 9, 6, 2, 7, 4, 2,
                3, 8, 6, 5, 8, 4, 7, 8, 10 };

        assertArrayEquals(expectedCost5, cost);

    }

    @Test
    public void ZeroWeights() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\ZeroWeights.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 0, 0, 0, 0 };
        int[] expectedParents = { 0, 0, 1, 2, 3 };

        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

        assertEquals(new ArrayList<>(Arrays.asList(0)), graph.constructPath(0, src, parents));
        assertEquals(new ArrayList<>(Arrays.asList(0, 1)), graph.constructPath(1, src, parents));
        assertEquals(new ArrayList<>(Arrays.asList(0, 1, 2)), graph.constructPath(2, src, parents));
        assertEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3)), graph.constructPath(3, src, parents));
    }

    // Unreachable : There is no path from 4 to1
    // It should return Positive inifinity(Integer.MAX_VALUE) as cost
    @Test
    public void UnreachableDestination() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\UnreachableDestination.txt";
        Graph graph = new Graph(relativePath);
        int src = 4;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);

        assertEquals(Integer.MAX_VALUE, cost[0]);
        assertEquals(0, parents[0]);

    }

    // unity weights + multiple shortest paths
    // from 0 to 7 we can go either through 0,1,3,7 or 0,1,4,7
    // from 0 to 8 we can through any of these:
    // [0,1,3,7,8]
    // [0,1,4,7,8]
    // [0,2,5,8]
    // [0,2,6,8]
    @Test
    public void assertOptimalPaths() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\UnityWeights.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 1, 1, 2, 2, 2, 2, 3, 3 };
        int[][] expectedParentsOptions = {
                { 0, 0, 0, 1, 1, 2, 2, 3, 5 },
                { 0, 0, 0, 1, 1, 2, 2, 3, 6 },
                { 0, 0, 0, 1, 1, 2, 2, 3, 7 },
                { 0, 0, 0, 1, 1, 2, 2, 4, 5 },
                { 0, 0, 0, 1, 1, 2, 2, 4, 6 },
                { 0, 0, 0, 1, 1, 2, 2, 4, 7 }
        };
        boolean found = false;
        for (int i = 0; i < expectedParentsOptions.length; i++) {
            if (Arrays.equals(parents, expectedParentsOptions[i])) {
                found = true;
                break;
            }
        }

        assertArrayEquals(expectedCost, cost);
        assertTrue(found);
    }

    @Test
    public void selfLoops() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\selfLoops.txt";
        Graph graph = new Graph(relativePath);
        int src = 7;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 12, 5, 6, 7, 10, 4, 19, 0 };
        int[] expectedParents = { 1, 5, 7, 2, 3, 7, 4, 7 };

        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void RepeatedEdges() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\RepeatedEdges.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 2, 3, 5 };
        int[] expectedParents = { 0, 0, 1, 2 };

        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    // n = 1001, edges = 4986
    @Test
    public void RandomLargeSparse() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\RandomLargeSparseD.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];

        // Run Dijkstra algorithm
        graph.Dijkstra(24, cost, parents);
        // source 24
        try {
            int[] expectedCost = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms/src/test/correctness/testCases/CorrectResult/LargeSparseSource24D.txt");
            assertArrayEquals(expectedCost, cost);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Run Dijkstra algorithm
        graph.Dijkstra(660, cost, parents);
        // source 660
        try {
            int[] expectedCost = GraphGenerator.fillArrayFromFile(
                    "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeSparseSource660D.txt");
            assertArrayEquals(expectedCost, cost);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.Dijkstra_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeSparseD.txt",
                graph.Size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    // n = 1001, edges = 632833
    @Test
    public void RandomLargeDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\RandomLargeDenseD.txt";
        Graph graph = new Graph(relativePath);
        List<int[]> allCosts = new ArrayList<>();
        List<int[]> allparents = new ArrayList<>();
        AllShortestPaths.Dijkstra_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\LargeDenseD.txt",
                graph.Size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    // n = 101, edges = 484
    @Test
    public void SmallSparceGraph() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\SmallSparseGraphD.txt";
        Graph graph = new Graph(relativePath);

        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        // source 64
        graph.Dijkstra(64, cost, parents);
        // Assert results
        int[] expectedCost1 = { Integer.MAX_VALUE, 104, 158, 191, 148, 149, 59, 183, 47, 139, 111, 151, 111, 151, 124,
                205, 166, 160, 123, 124, 123, 160, 107, 169, 141, 183, 208, 245, 233, 73, 146, 126, Integer.MAX_VALUE,
                164, 131, 177, 215, 190, 147, 225, 114, 168, 115, 105, 150, 153, 96, 189, 165, 170, 83, 145, 153, 101,
                233, 163, 224, 108, 154, 131, 162, 128, 185, 161, 0, 136, 184, 159, 179, 141, 97, 160, 93, 111, 93, 123,
                135, 167, 138, 177, 158, 132, 159, 157, 174, 177, 162, 102, 221, 144, 137, Integer.MAX_VALUE, 60, 156,
                119, 170, 52, 120, 129, 61, 115
        };

        assertArrayEquals(expectedCost1, cost);
        // source 33
        graph.Dijkstra(33, cost, parents);
        // Assert results
        int[] expectedCost2 = { Integer.MAX_VALUE, 47, 78, 161, 92, 115, 49, 126, 17, 82, 101, 94, 109, 73, 92, 148, 97,
                118, 105, 67, 101, 103, 129, 76, 84, 150, 113, 159, 69, 57, 4, 48, Integer.MAX_VALUE, 0, 74, 127, 136,
                62, 115, 156, 57, 134, 105, 95, 132, 103, 39, 121, 66, 113, 73, 88, 96, 71, 140, 106, 131, 98, 97, 53,
                53, 108, 135, 82, 62, 86, 98, 81, 15, 121, 133, 103, 63, 54, 95, 93, 113, 157, 82, 82, 48, 130, 145,
                106, 91, 120, 146, 80, 164, 122, 127, Integer.MAX_VALUE, 62, 99, 103, 113, 84, 118, 94, 63, 58
        };

        assertArrayEquals(expectedCost2, cost);
        // source 10
        graph.Dijkstra(10, cost, parents);
        // Assert results
        int[] expectedCost3 = { Integer.MAX_VALUE, 27, 47, 114, 37, 38, 29, 106, 48, 62, 0, 74, 61, 66, 13, 128, 72, 61,
                44, 47, 103, 83, 50, 70, 64, 99, 110, 151, 122, 79, 57, 76, Integer.MAX_VALUE, 53, 54, 66, 139, 79, 36,
                131, 37, 57, 62, 46, 71, 42, 19, 92, 68, 93, 53, 68, 76, 52, 134, 86, 123, 78, 57, 28, 75, 85, 74, 85,
                37, 25, 90, 74, 68, 98, 113, 81, 94, 34, 75, 120, 115, 114, 62, 79, 50, 82, 141, 58, 134, 100, 119, 102,
                112, 47, 38, Integer.MAX_VALUE, 42, 79, 85, 93, 89, 70, 111, 43, 38
        };

        assertArrayEquals(expectedCost3, cost);
        // source 52
        graph.Dijkstra(52, cost, parents);
        // Assert results
        int[] expectedCost4 = { Integer.MAX_VALUE, 51, 30, 178, 44, 83, 53, 130, 61, 86, 88, 98, 57, 97, 96, 152, 121,
                117, 72, 71, 133, 107, 95, 28, 88, 127, 140, 143, 167, 87, 102, 72, Integer.MAX_VALUE, 98, 78, 131, 163,
                107, 81, 180, 61, 102, 109, 82, 99, 70, 43, 125, 23, 117, 70, 92, 0, 88, 92, 110, 83, 102, 101, 77, 120,
                123, 104, 109, 14, 109, 139, 105, 113, 102, 111, 107, 107, 58, 99, 137, 95, 161, 86, 109, 5, 78, 102,
                114, 89, 24, 128, 115, 115, 92, 94, Integer.MAX_VALUE, 66, 50, 109, 104, 66, 66, 135, 67, 62
        };

        assertArrayEquals(expectedCost4, cost);
        // source 24
        graph.Dijkstra(24, cost, parents);
        // Assert results
        int[] expectedCost5 = { Integer.MAX_VALUE, 78, 136, 167, 138, 125, 139, 115, 99, 113, 156, 125, 125, 165, 123,
                238, 189, 58, 167, 98, 183, 134, 133, 134, 0, 67, 202, 219, 232, 166, 167, 140, Integer.MAX_VALUE, 163,
                105, 158, 205, 166, 146, 248, 147, 134, 172, 150, 194, 165, 129, 179, 140, 144, 163, 119, 127, 153, 198,
                163, 189, 188, 128, 145, 162, 184, 174, 151, 141, 148, 158, 173, 175, 188, 164, 134, 145, 144, 126, 174,
                195, 116, 157, 179, 122, 146, 192, 122, 191, 151, 121, 162, 195, 134, 159, Integer.MAX_VALUE, 93, 130,
                136, 144, 188, 134, 76, 94, 89
        };

        assertArrayEquals(expectedCost5, cost);

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
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 1, 3, 2, 6 };
        int[] expectedParents = { 0, 0, 1, 0, 3 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void negativeCycles() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\NegativeCycleD.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, -6, -9, -2 };
        int[] expectedParents = { 0, 3, 1, 0 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void multipleShortPath() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MultipleShortestPathsD.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, 2, 2, 4, 4, 6 };
        int[] expectedParents = { 0, 0, 0, 1, 2, 3 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void mediumGraphSparce() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumSparseD.txt";
        Graph graph = new Graph(relativePath);
        System.out.println(graph.Size());
        List<int[]> allCosts = new ArrayList<>(graph.Size());
        List<int[]> allparents = new ArrayList<>(graph.Size());

        AllShortestPaths.Dijkstra_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumSparseD.txt",
                graph.Size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            System.out.println(i);
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void mediumGraphDense() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\MediumDenseD.txt";
        Graph graph = new Graph(relativePath);
        System.out.println(graph.Size());
        List<int[]> allCosts = new ArrayList<>(graph.Size());
        List<int[]> allparents = new ArrayList<>(graph.Size());

        AllShortestPaths.Dijkstra_All_Paths(graph, allCosts, allparents);
        // Assert all costs
        List<int[]> expected = GraphGenerator.readAllCost(
                "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\CorrectResult\\MediumDenseD.txt",
                graph.Size());
        // Compare each pair of arrays in expected and allCosts
        for (int i = 0; i < expected.size(); i++) {
            System.out.println(i);
            assertArrayEquals(expected.get(i), allCosts.get(i));
        }

    }

    @Test
    public void graphWithNegativeEdges() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\GraphWithNegativeEdges.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, -2, -3, -6 };
        int[] expectedParents = { 0, 0, 1, 2 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void equalWeightEdges() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\EqualWeightEdges.txt";
        Graph graph = new Graph(relativePath);
        int src = 2;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 3, 6, 0, 3, 6 };
        int[] expectedParents = { 2, 0, 2, 2, 3 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    // two disconnected components. Hence there will be distances equal to inf
    @Test
    public void diconnectedGraph() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\DiconnectedGraph.txt";
        Graph graph = new Graph(relativePath);
        int src = 5;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 4, 0 };
        int[] expectedParents = { 0, 0, 0, 5, 3, 5 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void bidirectionalDifferentWeights() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\BidirectionalEdgesDifferentWeights.txt";
        Graph graph = new Graph(relativePath);
        int src = 9;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 4, 6, 5, 14, 4, 1, 4, 11, 4, 0 };
        int[] expectedParents = { 9, 9, 8, 1, 9, 9, 5, 4, 5, 9 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

    @Test
    public void allWeightsNegative() {
        String relativePath = "ShortestPathsAlgorithms\\src\\test\\correctness\\testCases\\AllWeightsNegative.txt";
        Graph graph = new Graph(relativePath);
        int src = 0;
        // Initialize arrays to store costs and parents
        int[] cost = new int[graph.Size()];
        int[] parents = new int[graph.Size()];
        // Run Dijkstra algorithm
        graph.Dijkstra(src, cost, parents);
        // Assert results
        int[] expectedCost = { 0, -1, -3, -4, -2, -1, -4, -2, -4, -5 };
        int[] expectedParents = { 0, 0, 0, 2, 3, 4, 5, 6, 7, 8 };
        assertArrayEquals(expectedCost, cost);
        assertArrayEquals(expectedParents, parents);

    }

}
