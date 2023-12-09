import java.util.*;

public class Solution {
    public String solution(String s) {
        String[] split = s.split(" ");
        List<Integer> numbers = new ArrayList<>();
        
        for (String str : split) {
            numbers.add(Integer.parseInt(str));
        }
        
        int min_val = Collections.min(numbers);
        int max_val = Collections.max(numbers);
        
        return min_val + " " + max_val;
    }
}
