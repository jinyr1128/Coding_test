import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for(int i = 0; i < citations.length; i++) {
            int min = Math.min(citations[i], citations.length - i);
            if(max < min) max = min;
        }
        return max;
    }
}