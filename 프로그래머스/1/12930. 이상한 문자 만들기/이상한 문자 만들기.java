class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int cnt = 0;
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ' ') {
                cnt = 0;
                answer.append(ch);
            } else {
                if(cnt % 2 == 0) {
                    answer.append(Character.toUpperCase(ch));
                } else {
                    answer.append(Character.toLowerCase(ch));
                }
                cnt++;
            }
        }
        return answer.toString();
    }
}
