import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N과 K를 입력 받음
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        // N의 약수를 저장할 리스트
        List<Integer> divisors = new ArrayList<>();

        // 약수 찾기
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                divisors.add(i);
            }
        }

        // 약수를 정렬 (사실 위 반복문에서 약수는 이미 정렬된 상태로 저장됨)
        Collections.sort(divisors);

        // K번째 약수를 출력, 없으면 0 출력
        if (K <= divisors.size()) {
            System.out.println(divisors.get(K - 1));
        } else {
            System.out.println(0);
        }

        scanner.close();
    }
}
