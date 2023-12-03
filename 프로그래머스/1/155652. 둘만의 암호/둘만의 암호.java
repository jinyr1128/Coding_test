public class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            int shiftedIndex = c - 'a';
            for (int i = 0; i < index; i++) {
                do {
                    shiftedIndex = (shiftedIndex + 1) % 26;
                } while (skip.indexOf((char) (shiftedIndex + 'a')) != -1);
            }
            result.append((char) (shiftedIndex + 'a'));
        }
        return result.toString();
    }
}

