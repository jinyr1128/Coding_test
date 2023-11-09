class Solution {
    public int solution(String[] s1, String[] s2) {
        int count = 0;
        
        for(String str1 : s1) {
            for(String str2 : s2) {
                if(str1.equals(str2)) {
                    count++;
                    break;
                }
            }
        }
        
        return count;
    }
}