import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n];
        
        for (int l : lost) 
            students[l-1]--;
        for (int r : reserve) 
            students[r-1]++;
        
        for (int i = 0; i < students.length; i++) {
            if(students[i] < 0) {
                if(i-1>=0 && students[i-1] > 0) {
                    students[i]++;
                    students[i-1]--;
                } else if(i+1<students.length && students[i+1] > 0) {
                    students[i]++;
                    students[i+1]--;
                }
            }
        }
        
        return (int) Arrays.stream(students).filter(i -> i >= 0).count();
    }
}