import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int N = scanner.nextInt(); // 여학생 수
        int M = scanner.nextInt(); // 남학생 수
        int K = scanner.nextInt(); // 인턴쉽에 참여해야 하는 학생 수

        // 여학생과 남학생 중 인턴쉽에 참여해야 하는 인원 조정
        while (K > 0) {
            if (N >= 2 * M) {
                N--;
            } else {
                M--;
            }
            K--;
        }

        // 최대 팀 수 계산
        int maxTeams = Math.min(N / 2, M);

        // 결과 출력
        System.out.println(maxTeams);
    }
}
