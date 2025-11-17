import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스의 수 T를 입력받습니다.
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            // L과 R을 입력받습니다.
            // L, R이 10^5이므로, k*(k+1)은 int 범위를 넘을 수 있습니다.
            // 따라서 long 타입을 사용합니다.
            long L = sc.nextLong();
            long R = sc.nextLong();

            // 1. 만들 수 있는 ( ) 쌍의 최대 개수 k를 구합니다.
            long k = Math.min(L, R);

            // 2. 최대 부분 문자열의 개수를 계산합니다: k * (k + 1) / 2
            long answer = (k * (k + 1)) / 2;

            // 3. 정답을 형식에 맞게 출력합니다.
            System.out.println("Case #" + i + ": " + answer);
        }
        
        sc.close();
    }
}