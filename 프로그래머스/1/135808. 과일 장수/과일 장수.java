import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score); 
        int len = score.length;
        int answer = 0;
        int min = score[len - 1];
        int cnt = 0; 

        for(int i = len - 1; i >= 0; i--) {
            min = Math.min(min, score[i]);
            cnt++;

            if(cnt == m) { 
                answer += min * m;
                min = score[i];
                cnt = 0;
            }
        }
        return answer;
    }
}

