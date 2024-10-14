import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input values
        int N = scanner.nextInt(); // minutes to exercise
        int m = scanner.nextInt(); // initial pulse
        int M = scanner.nextInt(); // max pulse
        int T = scanner.nextInt(); // pulse increase on exercise
        int R = scanner.nextInt(); // pulse decrease on rest
        scanner.close();

        // If it's impossible to exercise due to the pulse constraints
        if (m + T > M) {
            System.out.println(-1);
            return;
        }

        int currentPulse = m; // Start with the initial pulse
        int exerciseMinutes = 0; // Minutes spent exercising
        int totalTime = 0; // Total time including rests

        while (exerciseMinutes < N) {
            if (currentPulse + T <= M) {
                // Perform exercise
                currentPulse += T;
                exerciseMinutes++;
            } else {
                // Take a rest to reduce pulse
                currentPulse = Math.max(m, currentPulse - R);
            }
            totalTime++;
        }

        System.out.println(totalTime);
    }
}
