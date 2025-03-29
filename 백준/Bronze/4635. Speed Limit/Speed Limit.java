import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();  // 테스트 케이스의 로그 수

            if (n == -1) break;  // -1이면 종료

            int totalDistance = 0;
            int previousTime = 0;  // 이전까지의 누적 시간

            for (int i = 0; i < n; i++) {
                int speed = scanner.nextInt();  // 속도
                int currentTime = scanner.nextInt();  // 누적 시간

                int timeElapsed = currentTime - previousTime;  // 현재 구간에서 경과한 시간
                totalDistance += speed * timeElapsed;  // 현재 구간 거리 계산해서 더하기

                previousTime = currentTime;  // 다음 반복을 위해 시간 업데이트
            }

            System.out.println(totalDistance + " miles");
        }

        scanner.close();
    }
}
