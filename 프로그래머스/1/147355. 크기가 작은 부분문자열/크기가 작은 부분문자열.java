class Solution {
    public int solution(String t, String p) {
  if (t == null || p == null || t.isEmpty() || p.isEmpty()) {
            return 0;
  }
        int answer = 0;
        int len = p.length(); 
        long numP = Long.parseLong(p);

        for (int i = 0; i <= t.length() - len; i++) {
            String sub = t.substring(i, i + len);
            long numT = Long.parseLong(sub);
            if (numT <= numP) {
                answer++;
            }
        }
        return answer;
    }
}