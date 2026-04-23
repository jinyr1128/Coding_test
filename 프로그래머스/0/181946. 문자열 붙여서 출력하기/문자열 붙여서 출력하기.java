import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 공백을 기준으로 두 문자열을 각각 입력받음
        String str1 = sc.next();
        String str2 = sc.next();
        
        // 두 문자열을 '+' 연산자로 이어 붙여서 출력
        System.out.println(str1 + str2);
        
        sc.close();
    }
}