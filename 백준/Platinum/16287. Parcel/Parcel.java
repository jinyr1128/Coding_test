import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 읽기
        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = scanner.nextInt();
        }

        // 두 수의 합을 저장할 맵 생성
        HashMap<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = d[i] + d[j];
                if (sum >= w) continue;
                
                if (!map.containsKey(sum)) {
                    map.put(sum, new int[]{i, j});
                }
            }
        }

        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rest = w - (d[i] + d[j]);
                if (d[i] + d[j] < w && rest >= 2) {
                    if (map.containsKey(rest)) {
                        int[] indices = map.get(rest);
                        if (i != indices[0] && j != indices[1] && j != indices[0] && i != indices[1]) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (flag) break;
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
