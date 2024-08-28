import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 종족의 점수를 배열로 저장
        int[] gandalfScores = {1, 2, 3, 3, 4, 10}; // 호빗, 인간, 엘프, 드워프, 독수리, 마법사
        int[] sauronScores = {1, 2, 2, 2, 3, 5, 10}; // 오크, 인간, 워그, 고블린, 우럭하이, 트롤, 마법사

        int T = scanner.nextInt(); // 전투의 개수
        scanner.nextLine(); // 개행 처리

        for (int t = 1; t <= T; t++) {
            // 간달프 군대의 종족 수 입력받기
            int goodTotal = 0;
            for (int i = 0; i < gandalfScores.length; i++) {
                goodTotal += scanner.nextInt() * gandalfScores[i];
            }

            // 사우론 군대의 종족 수 입력받기
            int evilTotal = 0;
            for (int i = 0; i < sauronScores.length; i++) {
                evilTotal += scanner.nextInt() * sauronScores[i];
            }

            // 결과 출력
            System.out.print("Battle " + t + ": ");
            if (goodTotal > evilTotal) {
                System.out.println("Good triumphs over Evil");
            } else if (evilTotal > goodTotal) {
                System.out.println("Evil eradicates all trace of Good");
            } else {
                System.out.println("No victor on this battle field");
            }
        }

        scanner.close();
    }
}
