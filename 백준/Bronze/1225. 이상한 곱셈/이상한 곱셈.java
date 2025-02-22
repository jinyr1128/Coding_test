import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        String A = sc.next();
        String B = sc.next();

        // A의 자리수 합 계산
        long sumA = 0;
        for (int i = 0; i < A.length(); i++) {
            sumA += A.charAt(i) - '0'; // 문자 '0'을 빼서 실제 숫자로 변환
        }

        // B의 자리수 합 계산
        long sumB = 0;
        for (int i = 0; i < B.length(); i++) {
            sumB += B.charAt(i) - '0';
        }

        // 최종 결과 계산 (자리수 합의 곱)
        long result = sumA * sumB;

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}
