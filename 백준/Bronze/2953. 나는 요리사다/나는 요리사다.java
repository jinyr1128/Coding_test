import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxScore = 0;
        int winner = 0;

        for (int i = 1; i <= 5; i++) {
            int scoreSum = 0;
            for (int j = 0; j < 4; j++) {
                scoreSum += scanner.nextInt();
            }
            if (scoreSum > maxScore) {
                maxScore = scoreSum;
                winner = i;
            }
        }

        System.out.println(winner + " " + maxScore);
    }
}
