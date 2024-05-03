import java.util.Comparator;

public class WeightComparator implements Comparator<int[]> {

    public int compare(int[] o1, int[] o2) {
        if (o1[1] > o2[1]) {
            return 1;
        }
        else if (o1[1] == o2[1]) {
            return 0;
        }
        return -1;
    }
    
}