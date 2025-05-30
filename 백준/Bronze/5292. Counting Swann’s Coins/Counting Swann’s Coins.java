import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);

            if (divisibleBy3 && divisibleBy5) {
                sb.append("DeadMan\n");
            } else if (divisibleBy3) {
                sb.append("Dead\n");
            } else if (divisibleBy5) {
                sb.append("Man\n");
            } else {
                sb.append(i).append(" ");
            }
        }

        // 마지막 줄에 남은 숫자가 있다면 출력
        if (sb.length() > 0) {
            // 공백으로 끝났을 수도 있으므로 제거
            String[] lines = sb.toString().split("\n");
            for (String line : lines) {
                System.out.println(line.trim());
            }
        }
    }
}
