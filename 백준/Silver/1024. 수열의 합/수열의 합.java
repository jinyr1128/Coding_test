import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        long N = sc.nextLong();
        int L = sc.nextInt();
        
        // L부터 100까지 길이에 대해 가능한 수열을 찾습니다.
        for (int len = L; len <= 100; len++) {
            // a는 시작 숫자, 음이 아닌 정수여야 하므로 a >= 0이어야 합니다.
            // sum = len * (a + (a + len - 1)) / 2 = N이 성립해야 함
            // 따라서, N = len * (2a + len - 1) / 2 에서 a를 구하는 식을 변형
            // 2N = len * (2a + len - 1)
            // 2a = (2N / len) - len + 1
            long temp = N - (long) len * (len - 1) / 2;
            if (temp % len == 0) {
                long a = temp / len;
                if (a >= 0) {
                    // a부터 a+len-1까지 출력
                    for (int i = 0; i < len; i++) {
                        System.out.print((a + i) + " ");
                    }
                    return; // 정답을 찾으면 바로 종료
                }
            }
        }
        
        // 가능한 수열이 없을 때
        System.out.println(-1);
    }
}
