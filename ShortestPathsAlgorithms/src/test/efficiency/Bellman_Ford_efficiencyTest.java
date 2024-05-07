package test.efficiency;

import main.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bellman_Ford_efficiencyTest {
    int count = 5; // number of runs to calc meantime
    Random random = new Random();
    int numSources = 5; // no of source nodes 
    int[] graphSizes = { 10, 50, 100, 300, 500, 700, 1000, 3000, 5000, 7000, 10000 };
    double[] densities = { 0.8, 0.6, 0.5, 0.06, 0.06, 0.004, 0.002, 0.00004, 0.000004, 0.000004, 0.000002 }; // density for each size

    @Test
    public void Bellman_Ford_efficiency() throws IOException {
        for (int i = 0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            double density = densities[i];

            long totalTime = 0;
            int edges = (int) (density * size * (size - 1)); // calc edges based on density

            for (int j = 0; j < count; j++) {
                // Generate a random graph file with the current size and edges
                String filePath = GraphGenerator.generateRandomGraphFile(size, edges);

                // Create a new Graph instance with the generated graph file
                Graph graph = new Graph(filePath);

                // Generate multiple source nodes randomly
                List<Integer> sourceNodes = GraphGenerator.generateSourceNodes(size, numSources);

                // Run Bellman-Ford algorithm for each source node
                long start = System.currentTimeMillis();
                for (int src : sourceNodes) {
                    // Initialize arrays to store costs and parents
                    int[] cost = new int[graph.Size()];
                    int[] parents = new int[graph.Size()];

                    // Run Bellman-Ford algorithm
                    graph.BellmanFord(src, cost, parents);
                }
                long end = System.currentTimeMillis();
                totalTime += (end - start);
            }
            // Since we only need space for the cost and parents arrays each of size n
            int totalSpace = 2 * size * numSources; // Total space for all source nodes
            double meanTime = totalTime / (double) (count * numSources);
            System.out.println("Mean Time for Bellman-Ford on Graph with " + size + " vertices and density " + density
                    + ": " + meanTime + " milliseconds. Space Complexity: " + totalSpace + ".");
        }
    }

    @Test
    public void bellmanFordAllPathsEfficiency() throws IOException {
        for (int i = 0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            double density = densities[i];
            int edges = (int) (density * size * (size - 1)); // Calculate number of edges based on density

            long totalTime = 0;
            for (int j = 0; j < count; j++) {
                // Generate a random graph file with the current size and edges
                String filePath = GraphGenerator.generateRandomGraphFile(size, edges);

                // Create a new Graph instance with the generated graph file
                Graph graph = new Graph(filePath);

                // Initialize lists to store all costs and parents
                List<int[]> allCosts = new ArrayList<>();
                List<int[]> allParents = new ArrayList<>();

                // Run Bellman-Ford algorithm for all source nodes(all pairs)
                long start = System.currentTimeMillis();
                for (int src = 0; src < size; src++) {
                    // Initialize arrays to store costs and parents
                    int[] cost = new int[graph.Size()];
                    int[] parents = new int[graph.Size()];

                    // Run Bellman-Ford algorithm
                    graph.BellmanFord(src, cost, parents);
                    allCosts.add(cost);
                    allParents.add(parents);
                }
                long end = System.currentTimeMillis();
                totalTime += (end - start);
            }

            int totalSpace = 2 * size * size; // Total space for all pairs
            double meanTime = totalTime / (double) (count);
            System.out.println(
                    "meanTime Bellman on graph of " + size + " vertices and density " + density
                            + ": " + meanTime + " milliseconds. Space Complexity: " + totalSpace + ".");
        }
    }

}
