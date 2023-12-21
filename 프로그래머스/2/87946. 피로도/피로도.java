import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        int n = dungeons.length;
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        do {
            int fatigue = k;
            int count = 0;
            for (int i : order) {
                if (fatigue >= dungeons[i][0]) {
                    fatigue -= dungeons[i][1];
                    count++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, count);
        } while (nextPermutation(order));
        return answer;
    }
    
    private boolean nextPermutation(int[] order) {
        int i = order.length - 1;
        while (i > 0 && order[i-1] >= order[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        int j = order.length - 1;
        while (order[j] <= order[i-1]) {
            j--;
        }
        swap(order, i-1, j);
        j = order.length - 1;
        while (i < j) {
            swap(order, i, j);
            i++;
            j--;
        }
        return true;
    }
    
    private void swap(int[] order, int i, int j) {
        int temp = order[i];
        order[i] = order[j];
        order[j] = temp;
    }
}
