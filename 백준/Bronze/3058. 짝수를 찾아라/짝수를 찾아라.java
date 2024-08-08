import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 줄에 테스트 케이스의 개수 T를 입력받음
        int T = scanner.nextInt();

        // T개의 테스트 케이스에 대해 반복
        for (int t = 0; t < T; t++) {
            int sum = 0;
            int minEven = Integer.MAX_VALUE;  // 최솟값을 저장할 변수, 초기값을 최대값으로 설정

            // 각 테스트 케이스는 7개의 숫자로 이루어져 있음
            for (int i = 0; i < 7; i++) {
                int num = scanner.nextInt();
                
                // 짝수인지 확인
                if (num % 2 == 0) {
                    sum += num;  // 짝수의 합 계산
                    if (num < minEven) {
                        minEven = num;  // 최솟값 갱신
                    }
                }
            }

            // 결과 출력: 짝수의 합과 최솟값
            System.out.println(sum + " " + minEven);
        }

        scanner.close();  // Scanner 닫기
    }
}
