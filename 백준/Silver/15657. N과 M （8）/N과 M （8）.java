import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] numbers;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        numbers = new int[N];
        sequence = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(numbers); // 수열을 사전 순으로 출력하기 위해 먼저 정렬
        generateSequence(0, 0);
        System.out.print(sb);
    }

    static void generateSequence(int start, int depth) {
        if (depth == M) {
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = start; i < N; i++) {
            sequence[depth] = numbers[i];
            generateSequence(i, depth + 1); // i를 start로 전달하여 비내림차순 유지
        }
    }
}
