import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 사용자로부터 문자열 str 입력받기
        String str = sc.next();
        
        // 사용자로부터 정수 n 입력받기
        int n = sc.nextInt();

        // 결과를 저장할 StringBuilder 객체 생성
        StringBuilder result = new StringBuilder();

        // str을 n번 반복
        for (int i = 0; i < n; i++) {
            result.append(str);
        }

        // 결과 출력
        System.out.println(result.toString());

        // Scanner 객체 종료
        sc.close();
    }
}
