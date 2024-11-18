import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();
 
        Arrays.stream(clothes)
              .forEach(cloth -> clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 0) + 1));
        
        return clothesMap.values().stream()
                         .reduce(1, (result, count) -> result * (count + 1)) - 1;
    }
}

