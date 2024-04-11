class Solution {
    public int[] solution(int n) {
        int count = n / 2 + n % 2;
        int[] answer = new int[count];
        
        for (int i = 0; i < count; i++) {
            answer[i] = 2 * i + 1;
        }
        
        return answer;
    }
}
