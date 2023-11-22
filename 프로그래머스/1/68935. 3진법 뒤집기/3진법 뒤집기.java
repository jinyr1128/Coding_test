class Solution {
    public int solution(int n) {
        String ternary = Integer.toString(n, 3);
        String reversedTernary = new StringBuilder(ternary).reverse().toString();
        int answer = Integer.parseInt(reversedTernary, 3);
        return answer;
    }
}
