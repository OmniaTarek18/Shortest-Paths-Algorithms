package test.efficiency;

import main.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;


public class FloydWarshall_efficiencyTest {
    int[] graphSizes = { 10, 50, 100, 300, 500, 700, 1000, 3000, 5000, 7000, 10000 };
    double[] densities = { 0.8, 0.6, 0.5, 0.06, 0.06, 0.004, 0.002, 0.00004, 0.000004, 0.000004, 0.000002 }; // density for each size
    int count = 5; // number of runs to calc meantime

    Random random = new Random();

    @Test
    public void testFloydEfficiency() throws IOException {
        for (int i = 0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            double density = densities[i];

            long totalTime  = 0;
            int edges = (int) (density * size * (size - 1)); // calc edges based on density

            for (int j = 0; j < count; j++) {
                // Generate a random graph file with the current size and edges
                String filePath = GraphGenerator.generateRandomGraphFile(size, edges);

                // Create a new Graph instance with the generated graph file
                Graph graph = new Graph(filePath);

                // Run Floyd-Warshall algorithm
                long start= System.currentTimeMillis();
                int[][] cost = new int[size][size];
                int[][] predecessors = new int[size][size];
                graph.FloydWarshall(cost, predecessors);
                long end = System.currentTimeMillis();
                totalTime += (end - start);
            }
            // Space complexity for Floyd-Warshall is O(2 * (n)^2)
            // 2D array for the predecessors of size n and another one for cost n^2 + n^2
            int totalSpace = 2*(size * size); // Total space for all pairs
            double meanTime = totalTime / (double) count;
            System.out.println("meanTime Floyd on graph of " + size + " vertices and density " + density
                    + ": " + meanTime + " milliseconds. Space Complexity: " + totalSpace + ".");
        }
    }

}