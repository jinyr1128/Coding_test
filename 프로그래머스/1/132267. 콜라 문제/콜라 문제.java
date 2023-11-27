public class Solution {
    public int solution(int a, int b, int n) {
        if (n < a) {
            return 0;
        }
        int quotient = n / a;
        int remainder = n % a;
        return quotient * b + solution(a, b, quotient * b + remainder);
    }
}
