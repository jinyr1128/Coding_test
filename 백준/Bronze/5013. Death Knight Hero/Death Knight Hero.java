import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine()); // 전투 수
        int winCount = 0;

        for (int i = 0; i < n; i++) {
            String battle = scanner.nextLine();

            if (!battle.contains("CD")) {
                winCount++;
            }
        }

        System.out.println(winCount);
    }
}
