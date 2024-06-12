import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 멀티탭의 개수 N 입력
        int N = scanner.nextInt();

        // 각 멀티탭이 제공하는 플러그 수를 입력받아 합산
        int totalPlugs = 0;
        for (int i = 0; i < N; i++) {
            int plugs = scanner.nextInt();
            totalPlugs += plugs;
        }

        // 최대 연결 가능한 컴퓨터 수 계산
        int maxComputers = totalPlugs - (N - 1);

        // 결과 출력
        System.out.println(maxComputers);

        scanner.close();
    }
}
