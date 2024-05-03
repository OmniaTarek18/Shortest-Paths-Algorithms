import java.util.List;

public class AllShortestPaths {
    
    public void Dijkstra_All_Paths (Graph g, List<int[]> AllCost, List<int[]> AllParents) {
        for (int i=0; i<g.Size(); i++){
            int[] cost = new int[g.Size()];
            int[] parents = new int[g.Size()];
            g.Dijkstra (i, cost, parents);
            AllCost.add(cost);
            AllParents.add(parents);
        }
    }

}
