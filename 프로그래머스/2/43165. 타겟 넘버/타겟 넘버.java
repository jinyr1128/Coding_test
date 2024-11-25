class Solution {
    public int solution(int[] n, int t) {
        return f(n, t, 0, 0);
    }

    public int f(int[] n, int t, int i, int s) {
        return i == n.length ? (s == t ? 1 : 0) : f(n, t, i + 1, s + n[i]) + f(n, t, i + 1, s - n[i]);
    }
}
