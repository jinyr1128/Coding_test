class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] perNum = {781, 156, 31, 6, 1};
        String[] alphabet = {"A", "E", "I", "O", "U"};
        
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 5; j++) {
                if (word.substring(i, i + 1).equals(alphabet[j])) {
                    answer += 1 + j * perNum[i];
                }
            }
        }
        
        return answer;
    }
}
