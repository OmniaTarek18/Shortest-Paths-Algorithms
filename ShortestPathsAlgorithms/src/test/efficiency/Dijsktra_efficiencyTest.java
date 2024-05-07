package test.efficiency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import main.AllShortestPaths;
import main.Graph;

public class Dijsktra_efficiencyTest {
    int[] graphSizes = { 10, 50, 100, 300, 500, 700, 1000, 3000, 5000, 7000, 10000 };
    double[] densities = { 0.2, 0.4, 0.5, 0.03, 0.03, 0.02, 0.01, 0.002, 0.0009, 0.0002, 0.0001 }; // density for each size
    int count = 5; // number of runs to calc meantime
    int numSources = 5; // no of source nodes 
    Random random = new Random();

    @Test
    public void Dijkstra_efficiency_SingleSource() throws IOException {
        for (int i =0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            double density = densities[i];
            int edges = (int) (density * size * (size - 1)); // calc edges based on density
            
            long totalTime =0;
            for (int j =0; j < count; j++) {
                // Generate a random graph file with the current size and edges
                String filePath = GraphGenerator.generateRandomGraphFile(size, edges);

                // Create a new Graph instance with the generated graph file
                Graph graph = new Graph(filePath);

                // Generate multiple source nodes randomly
                List<Integer> sourceNodes = GraphGenerator.generateSourceNodes(size, numSources);

                // Run Dijkstra algorithm for each source node
                long start = System.currentTimeMillis();
                for (int src : sourceNodes) {
                    // Initialize arrays to store costs and parents
                    int[] cost = new int[graph.Size()];
                    int[] parents = new int[graph.Size()];

                    // Run Dijkstra algorithm
                    graph.Dijkstra(src, cost, parents);
                }
                long end= System.currentTimeMillis();
                totalTime += (end - start);
            }

            int totalSpace = 4 * size * numSources; // Total space for all source nodes
            double meanTime = totalTime / (double) (count * numSources);
            System.out.println("Mean Time for Dijkstra on Graph with " + size + " vertices and density " + density + ": "
                    + meanTime + " milliseconds. Space Complexity: " + totalSpace + ".");
        }
    }



    @Test
    public void Dijkstra_All_Paths_efficiency() throws IOException {
        for (int i =0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            double density = densities[i];
            int edges = (int) (density * size * (size - 1)); // Calculate number of edges based on density
            
            long totalTime =0;
            for (int j =0; j < count; j++) {
                // Generate a random graph file with the current size and edges
                String filePath = GraphGenerator.generateRandomGraphFile(size, edges);

                // Create a new Graph instance with the generated graph file
                Graph graph = new Graph(filePath);

                // Initialize lists to store all costs and parents
                List<int[]> allCosts = new ArrayList<>();
                List<int[]> allParents = new ArrayList<>();

                // Run Dijkstra_All_Paths for all source nodes
                long start = System.currentTimeMillis();
                AllShortestPaths.Dijkstra_All_Paths(graph, allCosts, allParents);
                long end = System.currentTimeMillis();
                totalTime+= (end - start);
            }

            int totalSpace = 4 * size * size; // Total space for all pairs
            double meanTime = totalTime / (double) (count);
            System.out.println("meanTime Dijkstra on graph of " + size + " vertices and density " + density
                    + ": " + meanTime + " milliseconds. Space Complexity: " + totalSpace + ".");
        }
    }
}
