class Solution {
    public long solution(long n) {
        double sqrt = Math.sqrt(n);
        if (sqrt == (int)sqrt) {
            return (long)Math.pow(sqrt + 1, 2);
        } else {
            return -1;
        }
    }
}
