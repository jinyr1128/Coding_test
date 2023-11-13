import java.util.*;

class Solution {
    public int[] solution(int[] num_list, int n) {
        ArrayList<Integer> temp = new ArrayList<>();
        
        for(int i = 0; i < num_list.length; i += n) {
            temp.add(num_list[i]);
        }

        int[] answer = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
}
