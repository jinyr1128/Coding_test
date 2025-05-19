import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = Integer.parseInt(sc.nextLine()); // 첫 줄: 출력할 줄 수

        for (int i = 0; i < L; i++) {
            String line = sc.nextLine(); // 예: "9 +"
            String[] parts = line.split(" ");
            int count = Integer.parseInt(parts[0]); // 반복 횟수
            char symbol = parts[1].charAt(0); // 반복할 문자

            // 문자 반복 출력
            for (int j = 0; j < count; j++) {
                System.out.print(symbol);
            }
            System.out.println(); // 줄 바꿈
        }
    }
}
