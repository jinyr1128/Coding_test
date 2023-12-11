public class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        String firstStr = s.charAt(0) + "";
        sb.append(firstStr.toUpperCase());

        for (int i = 1; i < s.length(); i++) {
            String current = s.charAt(i) + "";
            if (s.charAt(i - 1) == ' ') {  // 이전 문자가 공백이면 대문자로
                sb.append(current.toUpperCase());
            } else {  // 그 외의 경우에는 소문자로
                sb.append(current.toLowerCase());
            }
        }

        return sb.toString();
    }
}
