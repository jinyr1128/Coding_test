import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // N을 문자열로 입력받음 (매우 큰 수이므로 String으로 처리)
        String N = sc.next();
        sc.close();

        // 나눌 값
        int divisor = 20000303;
        
        // 나머지 계산 (큰 수를 처리하기 위해 한 자리씩 mod 연산)
        int remainder = 0;
        for (int i = 0; i < N.length(); i++) {
            remainder = (remainder * 10 + (N.charAt(i) - '0')) % divisor;
        }

        // 결과 출력
        System.out.println(remainder);
    }
}
