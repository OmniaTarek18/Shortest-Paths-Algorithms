package main;

import java.util.List;

import java.util.Arrays;

public class AllShortestPaths {

    public static void Dijkstra_All_Paths(Graph g, List<int[]> AllCost, List<int[]> AllParents) {
        for (int i = 0; i < g.Size(); i++) {
            int[] cost = new int[g.Size()];
            int[] parents = new int[g.Size()];
            g.Dijkstra(i, cost, parents);
            AllCost.add(cost);
            AllParents.add(parents);
        }
    }


    public static void BellmanFord_All_Paths(Graph g, List<int[]> AllCost, List<int[]> AllParents) {
        int n = g.Size();
        boolean[] hasNegativeCycle = new boolean[n];
        Arrays.fill(hasNegativeCycle, false);
        System.out.println(g.Size());
        for (int src = 0; src < n; src++) {
            int[] cost = new int[n];
            int[] parents = new int[n];
            g.BellmanFord(src, cost, parents);

                AllCost.add(cost);
                AllParents.add(parents);

        }
    
    }
    
}
