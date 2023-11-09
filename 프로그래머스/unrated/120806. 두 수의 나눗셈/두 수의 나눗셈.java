class Solution {
    public int solution(int num1, int num2) {

        double temp = ((double) num1 / num2) * 1000;

        int answer = (int) Math.floor(temp);
        
        return answer;
    }
}
