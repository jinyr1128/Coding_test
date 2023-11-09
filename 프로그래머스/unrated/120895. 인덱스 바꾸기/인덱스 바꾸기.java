class Solution {
    public String solution(String my_string, int num1, int num2) {
        char[] chars = my_string.toCharArray(); // 문자열을 문자 배열로 변환
        
        // num1과 num2 인덱스의 문자를 교환
        char temp = chars[num1];
        chars[num1] = chars[num2];
        chars[num2] = temp;
        
        return new String(chars); // 문자 배열을 문자열로 변환하여 반환
    }
}
