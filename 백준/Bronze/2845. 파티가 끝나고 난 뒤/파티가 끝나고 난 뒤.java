import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1m^2당 사람의 수와 파티 장소의 넓이를 입력 받습니다.
        int L = scanner.nextInt();
        int P = scanner.nextInt();

        // 총 참가자 수 계산
        int totalParticipants = L * P;

        // 각 신문에 실린 참가자 수 입력 받기
        int[] newspaperCounts = new int[5];
        for (int i = 0; i < 5; i++) {
            newspaperCounts[i] = scanner.nextInt();
        }

        // 차이 계산 및 출력
        for (int i = 0; i < 5; i++) {
            System.out.print((newspaperCounts[i] - totalParticipants) + " ");
        }
    }
}
