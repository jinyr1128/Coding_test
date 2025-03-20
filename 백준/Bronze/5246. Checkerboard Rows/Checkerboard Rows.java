import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = sc.nextInt(); // 테스트 케이스 개수

        for (int t = 0; t < testCases; t++) {
            int pieces = sc.nextInt(); // 체커보드 위의 말 개수
            HashMap<Integer, Integer> rowCount = new HashMap<>(); // 행(row)별 말 개수 저장

            int maxPieces = 0;

            for (int i = 0; i < pieces; i++) {
                int col = sc.nextInt(); // 열 정보 (사용 안함)
                int row = sc.nextInt(); // 행 정보

                rowCount.put(row, rowCount.getOrDefault(row, 0) + 1);
                maxPieces = Math.max(maxPieces, rowCount.get(row));
            }

            System.out.println(maxPieces);
        }

        sc.close();
    }
}
