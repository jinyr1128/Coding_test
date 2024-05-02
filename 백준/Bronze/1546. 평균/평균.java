import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] scores = new int[N];
        int maxScore = 0;
        double sum = 0;

        for (int i = 0; i < N; i++) {
            scores[i] = sc.nextInt();
            if (scores[i] > maxScore) {
                maxScore = scores[i];
            }
        }

        for (int score : scores) {
            sum += (double) score / maxScore * 100;
        }

        System.out.println(sum / N);

        sc.close();
    }
}
