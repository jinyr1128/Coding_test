import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static int[] arr;
    static int[] selected;
    static boolean[] used;
    static HashSet<String> results;  // 중복 수열을 피하기 위한 HashSet

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        arr = new int[N];
        selected = new int[M];
        used = new boolean[N];
        results = new HashSet<>();

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        Arrays.sort(arr); // 사전 순 출력을 위해 먼저 정렬

        generatePermutations(0, N, M);

        scanner.close();
    }

    // 순열을 생성하고 출력
    public static void generatePermutations(int depth, int N, int M) {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            String result = sb.toString().trim();
            if (!results.contains(result)) {  // 중복을 방지하기 위해 HashSet 체크
                results.add(result);
                System.out.println(result);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                selected[depth] = arr[i];
                generatePermutations(depth + 1, N, M);
                used[i] = false;
            }
        }
    }
}
