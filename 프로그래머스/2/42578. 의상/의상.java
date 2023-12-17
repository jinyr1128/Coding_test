import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();
        for (String[] cloth : clothes) {
            clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 0) + 1);
        }
        
        int answer = 1;
        for (int value : clothesMap.values()) {
            answer *= (value + 1);
        }
        
        return answer - 1;
    }
}
