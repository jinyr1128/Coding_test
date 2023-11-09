import java.util.HashMap;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        HashMap<Character, Integer> lastPosition = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            
            if (lastPosition.containsKey(currentChar)) {
                answer[i] = i - lastPosition.get(currentChar);
            } else {
                answer[i] = -1;
            }

            lastPosition.put(currentChar, i);
        }
        
        return answer;
    }
}