import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CLI {
	Graph graph;
	static Scanner sc = new Scanner(System.in);

	// get file from user and initialize the graph
	public void initializeGraph() {
		System.out.println("Please, Enter file path: ");
		String filePath = sc.nextLine();

		// handle path with double quote.
		String[] temp = filePath.split("\"");
		filePath = String.join("\\", temp);

		graph = new Graph(filePath);
	}

	public int getMainMenuOption() {
		System.out.println("MAIN MENU: ");
		System.out.println("\t1. Find the shortest paths from source node to all other nodes.");
		System.out.println("\t2. Find the shortest paths between all the pairs of nodes.");
		System.out.println("\t3. Check if the graph contains a negative cycle.");
		System.out.println("\t4. Exit.");
		System.out.print("Please, Enter the option you want: ");

		int mainOption = sc.nextInt();
		while (4 < mainOption || mainOption < 1) {
			System.out.print("Please, Enter Valid Option: ");
			mainOption = sc.nextInt();
		}
		return mainOption;
	}

	public int getAlgorithmOption(int mainOption) {
		int num = 1;
		System.out.println("\nAlgorithms: ");
		if (mainOption != 3)
			System.out.println("\t" + num++ + ". Dijkstra Algorithm");
		System.out.println("\t" + num++ + ". Bellman-Ford Algorithm");
		System.out.println("\t" + num++ + ". Floyd-Warshall Algorithm");
		System.out.print("Please, Enter the Algorithm you want: ");
		int algOption = sc.nextInt();
		while (1 > algOption || algOption > num) {
			System.out.print("Please, Enter Valid Option: ");
			algOption = sc.nextInt();
		}
		return algOption;
	}

	public int getSource() {
		System.out.print("Please, Enter the source: ");
		int src = sc.nextInt();
		while (src >= graph.Size() || src < 0) {
			System.out.print("Please, Enter Valid Source: ");
			src = sc.nextInt();
		}
		return src;
	}

	// 1. find the shortest paths from source node to all other nodes.
	public void findShortestPaths(int algOption, int src) {
		if (algOption != 3) {
			int[] cost = new int[graph.Size()];
			int[] parents = new int[graph.Size()];
			Arrays.fill(parents, -1);

			if (algOption == 1)
				graph.Dijkstra(src, cost, parents);
			else if (algOption == 2)
				graph.BellmanFord(src, cost, parents);

			subMenu(src, cost, parents);
		} else {
			int[][] cost = new int[graph.Size()][graph.Size()];
			int[][] parents = new int[graph.Size()][graph.Size()];

			for (int[] parent : parents) {
				Arrays.fill(parent, -1);
			}

			graph.FloydWarshall(cost, parents);

			subMenu(src, cost, parents); // need to be edited later
		}
	}

	// 2. find the shortest paths between all the pairs of nodes.
	public void findAllShortestPaths(int algOption) {
		int[][] cost = new int[graph.Size()][graph.Size()];
		int[][] parents = new int[graph.Size()][graph.Size()];

		for (int[] parent : parents) {
			Arrays.fill(parent, -1);
		}

		if (algOption == 1) {
			for (int i = 0; i < graph.Size(); i++) {
				graph.Dijkstra(i, cost[i], parents[i]);
			}
			subMenu(cost, parents, false);
		} else if (algOption == 2) {
			for (int i = 0; i < graph.Size(); i++) {
				graph.BellmanFord(i, cost[i], parents[i]);
			}
			subMenu(cost, parents, false);
		} else if (algOption == 3) {
			graph.FloydWarshall(cost, parents);
			subMenu(cost, parents, true);
		}
	}

	// sub menu
	// we need to specifiy the option of main menu (option 1 or 2 in MM)
	static int getSubMenuOption(int option) {
		String sub = option == 1 ? "to specific node." : "from specific node to another one.";
		System.out.println("\nSUB-MENU");
		System.out.println("\t1. Get cost " + sub);
		System.out.println("\t2. Get path " + sub);
		System.out.println("\t3. Get back to Main Menu.");
		System.out.print("Please, Enter the option you want: ");
		int subOption = sc.nextInt();

		while (3 < subOption || subOption < 1) {
			System.out.print("Please, Enter Valid Option: ");
			subOption = sc.nextInt();
		}
		return subOption;
	}

	static void subMenu(int src, int[] cost, int[] parents) {
		boolean exitSubMenu = false;
		while (!exitSubMenu) {
			int subOption = getSubMenuOption(1);
			if (subOption == 3) {
				exitSubMenu = true;
			} else {
				System.out.print("Please, Enter the destination: ");
				int dest = sc.nextInt();

				if (subOption == 1)
					getCost(src, dest, cost);
				else if (subOption == 2)
					getPath(src, dest, parents);
			}
		}
	}

	// For floyd-warshall algorithm
	static void subMenu(int src, int[][] cost, int[][] parents) {
		boolean exitSubMenu = false;
		while (!exitSubMenu) {
			int subOption = getSubMenuOption(1);
			if (subOption == 3) {
				exitSubMenu = true;
			} else {
				System.out.print("Please, Enter the destination: ");
				int dest = sc.nextInt();

				if (subOption == 1)
					getCost(src, dest, cost[src]);
				else if (subOption == 2)
					getPath(src, dest, parents);
			}
		}
	}

	static void subMenu(int[][] cost, int[][] parents, boolean isFloydWarshall) {
		boolean exitSubMenu = false;
		while (!exitSubMenu) {
			int subOption = getSubMenuOption(2);
			if (subOption == 3) {
				exitSubMenu = true;
			} else {
				System.out.print("Please, Enter the source: ");
				int src = sc.nextInt();
				System.out.print("Please, Enter the destination: ");
				int dest = sc.nextInt();

				if (subOption == 1)
					getCost(src, dest, cost[src]);
				else if (!isFloydWarshall && subOption == 2)
					getPath(src, dest, parents[src]);
				else if (isFloydWarshall && subOption == 2)
					getPath(src, dest, parents);
			}
		}
	}

	// For floyd-warshall algorithm
	static void getPath(int src, int dest, int[][] predecessors) {
		var graph = new Graph();
		ArrayList<Integer> path = graph.path(src, dest, predecessors);
		if (path == null) {
			System.out.println("No path found from node " + src + " to node " + dest + ".");
			return;
		}
		System.out.println("Path from node " + src + " to node " + dest + " :");
		for (int i = 0; i < path.size() - 1; i++) {
			System.out.print(path.get(i) + " --> ");
		}
		System.out.println(path.get(path.size() - 1));
	}

	static void getPath(int src, int dest, int[] parents) {
		List<String> nodes = new ArrayList<>();
		int temp = dest;
		while (temp != src) {
			if (parents[temp] == -1) {
				System.out.println("No path found from node " + src + " to node " + dest + ".");
				break;
			}
			nodes.add(Integer.toString(temp));
			temp = parents[temp];
		}
		if (temp != src)
			return;
		nodes.add(Integer.toString(temp));
		Collections.reverse(nodes);
		String finalPath = String.join(" --> ", nodes);
		System.out.println("Path from node " + src + " to node " + dest + " :");
		System.out.println(finalPath);
	}

	static void getCost(int src, int dest, int[] cost) {
		System.out.println("The cost of the path from node " + src + " to node " + dest + " = " + cost[dest]);
	}

	// 3. check if the graph contains a negative cycle .
	public void checkNegativeCycle(int algOption) {
		boolean value = false;
		if (algOption == 1) {
			value = checkNegativeCycleBellman();

		} else if (algOption == 2) {
			int[][] cost = new int[graph.Size()][graph.Size()];
			int[][] parents = new int[graph.Size()][graph.Size()];

			value = graph.FloydWarshall(cost, parents);
		}
		if (value)
			System.out.println("No negative cycles");
		else if (!value)
			System.out.println("A negative cycle is found");
	}

	// using bellmand-ford algorithm .
	public boolean checkNegativeCycleBellman() {
		// visit all nodes to make sure there is no -ve cycles
		boolean value = false;
		int[] cost = new int[graph.Size()];
		int[] parents = new int[graph.Size()];

		value = graph.BellmanFord(0, cost, parents);
		if (!value)
			return false;

		ArrayList<Integer> notVisited = new ArrayList<>();
		for (int i = 0; i < graph.Size(); i++) {
			if (cost[i] == Integer.MAX_VALUE)
				notVisited.add(i);
		}
		while (notVisited.size() != 0) {
			value = graph.BellmanFord(notVisited.remove(0), cost, parents);
			if (!value)
				return false;
			for (int i = 0; i < notVisited.size(); i++) {
				if (cost[i] != Integer.MAX_VALUE)
					notVisited.remove(i);
			}

		}
		return true;

	}

}
