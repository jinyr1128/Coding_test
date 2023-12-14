import java.util.HashSet;

public class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int[] doubledElements = new int[2 * n];
        for (int i = 0; i < n; i++) {
            doubledElements[i] = doubledElements[i + n] = elements[i];
        }

        HashSet<Integer> sums = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < i + n; j++) { 
                sum += doubledElements[j];
                sums.add(sum);
            }
        }

        return sums.size(); 
    }
}
