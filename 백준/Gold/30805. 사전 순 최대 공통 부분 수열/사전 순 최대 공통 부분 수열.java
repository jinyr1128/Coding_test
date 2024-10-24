import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int n = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }

        int m = scanner.nextInt();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            b.add(scanner.nextInt());
        }
        scanner.close();

        boolean flag = true;
        List<Integer> ans = new ArrayList<>();

        while (true) {
            // 각 수열의 최대 공통 값을 찾음
            if (a.isEmpty() || b.isEmpty()) {
                flag = false;
                break;
            }

            int maxA = Collections.max(a);
            int aIdx = a.indexOf(maxA);
            int maxB = Collections.max(b);
            int bIdx = b.indexOf(maxB);

            if (maxA == maxB) {
                // 최댓값을 ans 리스트에 추가
                ans.add(maxA);

                // 최댓값보다 작은 인덱스의 값들을 제거
                a = new ArrayList<>(a.subList(aIdx + 1, a.size()));
                b = new ArrayList<>(b.subList(bIdx + 1, b.size()));
            } else if (maxA > maxB) {
                a.remove(aIdx);
            } else {
                b.remove(bIdx);
            }
        }

        // 결과 출력
        if (!ans.isEmpty()) {
            System.out.println(ans.size());
            for (int value : ans) {
                System.out.print(value + " ");
            }
            System.out.println();
        } else {
            System.out.println(0);
        }
    }
}
