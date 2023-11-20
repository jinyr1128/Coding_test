
class Solution {
    public String solution(String s) {
        int length = s.length();
        return s.substring((length - 1) / 2, length / 2 + 1);
    }
}