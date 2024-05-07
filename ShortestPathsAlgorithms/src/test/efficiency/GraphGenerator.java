package test.efficiency;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/* a helper class mainly to generate random graphs used in testing and contains other methods for reading the output from the answer files and fills the arrays or lists with it */
public class GraphGenerator {
    static Random random = new Random();
    public static String generateRandomGraphFile(int vertices, int edges) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(vertices).append(" ").append(edges).append("\n");
        for (int i =0; i < edges; i++) {
            int from = random.nextInt(vertices);
            int to = random.nextInt(vertices);
            int weight = random.nextInt(201) - 100; // assuming weights are between -100 and 100
            sb.append(from).append(" ").append(to).append(" ").append(weight).append("\n");
        }
        //getting the path where the file will be put in 
        String testFolderPath = "ShortestPathsAlgorithms\\src\\test\\efficiency";
        // write graph data to the file
        File directory = new File(testFolderPath);
        File tempFile = new File(directory, "randomGraph.txt");
        // delete it if it already exists to avoid too many files
        if (tempFile.exists()) {
            tempFile.delete();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write(sb.toString());
        writer.close();
        return tempFile.getAbsolutePath();
    }
    // it is used to fil the expected cost array 
    public static int[] fillArrayFromFile(String filePath) throws FileNotFoundException {
        int[] array;
        try {
            System.out.println("dd");
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] numbers = line.split(",");
            array = new int[numbers.length];
            for (int i =0; i < numbers.length; i++) {
                array[i] = Integer.parseInt(numbers[i].trim());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid data format in the file: " + filePath);
        }
        return array;
    }

    // used in all pairs to read all costs and fill it into a list
    public static List<int[]> readAllCost(String path, int graphSize) {
        List<int[]> res = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int i =0; i < graphSize; i++) {
                int[] cost = new int[graphSize];
                for (int j =0; j < graphSize; j++) {
                    cost[j] = Integer.MAX_VALUE; // initialize it with the max
                }
                res.add(cost);
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);

                res.get(row)[col] = value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
    // used to assert floydwarshall results fills the expected results into 2D array
    public static int[][] readDistancesFromFile(String filePath,int graphSize) {
        int[][] distances = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line ;
            String[] parts;
            distances = new int[graphSize][graphSize];
            
            // initialize distances as inifinity
            for (int i = 0; i < graphSize; i++) {
                for (int j = 0; j < graphSize; j++) {
                    distances[i][j] = Integer.MAX_VALUE;
                }
                distances[i][i] = 0; // distance to itself is zero
            }
            
            // reading distances from the ans file
            while ((line = reader.readLine()) != null) {
                parts = line.split(" ");
                int src = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                int weight = Integer.parseInt(parts[2]);
                if(weight == Integer.MAX_VALUE){ // to match the floyd warshall implementation in graph class
                    weight /= 2;
                }
                distances[src][dest] = weight;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distances;
    }
    public static List<Integer> generateSourceNodes(int size, int numSources) {
        List<Integer> sourceNodes = new ArrayList<>();
        for (int i =0; i < numSources; i++) {
            sourceNodes.add(random.nextInt(size));
        }
        return sourceNodes;
    }
}
