import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int[] arr;
    static int[] selected;
    static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // 전체 수의 개수
        int M = scanner.nextInt();  // 선택해야 할 수의 개수

        arr = new int[N];
        selected = new int[M];
        used = new boolean[N];

        // N개의 수를 입력받음
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        // 수열을 사전 순으로 출력하기 위해 입력받은 수를 정렬
        Arrays.sort(arr);

        // 백트래킹을 이용한 순열 생성
        permutation(0, N, M);

        scanner.close();
    }

    // 순열 생성 메소드
    public static void permutation(int depth, int N, int M) {
        if (depth == M) {  // 선택한 수의 개수가 M개라면 출력
            for (int i = 0; i < M; i++) {
                System.out.print(selected[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {  // 아직 선택되지 않았다면
                used[i] = true;  // 사용 표시
                selected[depth] = arr[i];
                permutation(depth + 1, N, M);
                used[i] = false;  // 백트래킹
            }
        }
    }
}
