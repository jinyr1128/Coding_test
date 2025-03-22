import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine()); // 테스트 케이스 개수

        for (int i = 0; i < T; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            int idx = Integer.parseInt(parts[0]); // 오타 위치 (1-based)
            String word = parts[1];

            // 오타 위치 제거 (0-based index 변환)
            String result = word.substring(0, idx - 1) + word.substring(idx);
            System.out.println(result);
        }

        sc.close();
    }
}
