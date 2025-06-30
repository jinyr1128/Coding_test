import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine());  // 전송 횟수

        for (int i = 0; i < T; i++) {
            String line = sc.nextLine();         // 한 줄 읽기
            String[] parts = line.split(" ");    // 공백 기준 분리
            String input = parts[0];
            String output = parts[1];

            if (input.equals(output)) {
                System.out.println("OK");
            } else {
                System.out.println("ERROR");
            }
        }

        sc.close();
    }
}
