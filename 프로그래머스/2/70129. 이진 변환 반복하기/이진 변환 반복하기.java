import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int count = 0;  // 이진 변환 횟수
        int removedZeros = 0;  // 제거된 0의 개수
        
        while (!s.equals("1")) {
            int originalLength = s.length();  // 변환 이전 문자열의 길이
            
            // 0 제거
            s = s.replaceAll("0", "");
            
            int newLength = s.length();  // 변환 이후 문자열의 길이
            
            // 제거된 0의 개수 누적
            removedZeros += originalLength - newLength;
            
            // 이진 변환
            s = Integer.toBinaryString(newLength);
            
            count++;
        }
        
        answer[0] = count;
        answer[1] = removedZeros;
        
        return answer;
    }
}
