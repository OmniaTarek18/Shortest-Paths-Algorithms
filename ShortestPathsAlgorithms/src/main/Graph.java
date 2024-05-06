package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {

    private int n; // Number of vertices
    private List<List<int[]>> graph; // Adjacency List, the neighbors are stored as {vertex, weight}

    public Graph() {
    }

    public Graph(String filePath) {
        // NOTE: I didn't check on repeated edges.
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("ERROR: File does not exist or invalid path.");
        }

        try {
            Scanner sc = new Scanner(file);
            n = sc.nextInt();
            graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            int edges = sc.nextInt();

            for (int i = 0; i < edges; i++) {
                int src = sc.nextInt();
                int[] edge = { sc.nextInt(), sc.nextInt() };
                List<int[]> from = graph.get(src);
                from.add(edge);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("ERROR: The file is empty.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public int Size() {
        return this.n;
    }

    public void Dijkstra(int src, int[] cost, int[] parents) {
        boolean[] sure = new boolean[this.n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new WeightComparator());

        Arrays.fill(cost, Integer.MAX_VALUE);

        int[] base = { src, 0, 0 }; // {destination node, cost, parent}
        pq.add(base);
        cost[src] = 0;
        parents[src] = src;

        while (!pq.isEmpty()) {
            int[] min = pq.poll();
            sure[min[0]] = true; // Make the node with the least cost sure
            for (int[] neighbor : graph.get(min[0])) { // Loop on the neighbors of the least cost node

                // Check if the neighbor of the least cost node is unsure and then compare its
                // current cost and the
                // new cost if taken the cost of the least cost node added to the weight of the
                // edge between them.
                // If the current cost is bigger, then update the cost of the node with the new
                // cost and update the parent of it.
                if (!sure[neighbor[0]] && cost[min[0]] + neighbor[1] < cost[neighbor[0]]) {
                    cost[neighbor[0]] = cost[min[0]] + neighbor[1];
                    parents[neighbor[0]] = min[0];
                    int[] entry = { neighbor[0], cost[neighbor[0]], parents[neighbor[0]] };
                    // Adding the node updated to the priority queue.
                    pq.add(entry);
                }
            }
        }
    }

    public boolean BellmanFord(int src, int[] cost, int[] parents) {
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {

                if (cost[j] == Integer.MAX_VALUE)
                    continue;
                for (int[] edge : graph.get(j)) {
                    if (cost[edge[0]] > cost[j] + edge[1]) {
                        parents[edge[0]] = j;
                        cost[edge[0]] = cost[j] + edge[1];
                    }
                }
            }
        }
        // check negative cycles
        for (int j = 0; j < n; j++) {

            if (cost[j] == Integer.MAX_VALUE)
                continue;
            for (int[] edge : graph.get(j)) {
                if (cost[edge[0]] > cost[j] + edge[1]) {
                    return false;
                }
            }
        }

        return true;

    }

    public boolean FloydWarshall(int[][] cost, int[][] predecessors) {

        int nodes = graph.size();

        // Initialize the cost matrix
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                cost[i][j] = Integer.MAX_VALUE / 2; // To avoid overflow when adding two 2147483647
            }
            if (!graph.get(i).isEmpty()) {
                for (int[] edge : graph.get(i)) {
                    cost[i][edge[0]] = edge[1];
                }
            }
            if(cost[i][i] > 0)
                cost[i][i] = 0;
        }

        // Initialize the predecessors matrix
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (cost[i][j] == Integer.MAX_VALUE / 2) {
                    predecessors[i][j] = -1;
                } else
                    predecessors[i][j] = j; // Source is i and next is j
            }
        }

        // Apply floyd-warshall algorithm
        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        predecessors[i][j] = predecessors[i][k];
                    }
                }
            }

        }

        // check -ve cycles
        for (int i = 0; i < nodes; i++) {
            if (cost[i][i] < 0)
                return false;
        }
        return true;

    }

    // To get the shortest path of floyd-warshall
    ArrayList<Integer> path(int start, int end, int[][] predecessors) {
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = start; i != end; i = predecessors[i][end]) {
            if (i == -1)
                return null;
            path.add(i);
        }
        if (predecessors[end][end] == -1)
            return null;
        else
            path.add(end);
        return path;
    }

    public List<Integer> constructPath(int destination, int source, int[] parents) {
        List<Integer> path = new ArrayList<>();
        int cur = destination;
        // trace back from destination to the source
        while (cur != source) {
            path.add(cur);
            cur = parents[cur];
        }
        // finally add the source to the path
        path.add(source);
        // reverse it so that the path is from the source to destination
        Collections.reverse(path);
        return path;
    }

    // NOTE: The main function is used only for testing what I coded.
    // Feel free to delete and create a new class to create the menu and put the
    // main in it.
    public static void main(String[] argv) {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease enter the file path: ");
        String filePath = sc.nextLine();

        // reform the path and add double slash
        String[] path = filePath.split("\"");
        filePath = String.join("\\", path);

        Graph g = new Graph(filePath);

        // That's a sample of how dijkstra code runs.
        // NOTE: If the cost = Integer.MAX_VALUE, then this means that this node can't
        // be accessed.
        // NOTE: If the source and destination nodes are the same, then the parent node
        // equals any of them.
        int[] cost = new int[g.Size()];
        int[] parents = new int[g.Size()];
        g.Dijkstra(0, cost, parents); // One Source to All Nodes
        // You should print the full path from the source to the desired destination and
        // not only the node used to reach that destination.

        sc.close();
    }
}

// The graph for Question 1 in sheet 4
// 7 12
// 0 4 12
// 4 6 -7
// 1 3 2
// 2 1 3
// 0 2 7
// 2 3 -1
// 2 4 2
// 4 0 -4
// 5 6 2
// 6 3 1
// 0 1 2
// 3 5 2

// The solution for the Graph present in graph.txt file is
// From To Cost Parent
// 0 0 0 0
// 0 1 3 0
// 0 2 8 0
// 0 3 4 1
// 0 4 1 0

// 1 0 3 3
// 1 1 0 1
// 1 2 11 0
// 1 3 1 1
// 1 4 4 0

// 2 0 7 3
// 2 1 4 2
// 2 2 0 2
// 2 3 5 2
// 2 4 8 0

// 3 0 2 3
// 3 1 5 0
// 3 2 10 0
// 3 3 0 3
// 3 4 3 0

// 4 0 8 3
// 4 1 11 0
// 4 2 16 0
// 4 3 6 4
// 4 4 0 4