import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numbers;
    static ArrayList<Integer> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(numbers); // 입력된 수를 오름차순으로 정렬
        generateSequence(0, 0);
        System.out.print(sb);
    }

    static void generateSequence(int depth, int start) {
        if (depth == M) {
            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        int lastUsed = -1; // 중복 수열 방지를 위한 변수
        for (int i = start; i < N; i++) {
            if (lastUsed != numbers[i]) {
                result.add(numbers[i]);
                generateSequence(depth + 1, i);
                result.remove(result.size() - 1);
                lastUsed = numbers[i]; // 현재 수를 마지막으로 사용한 수로 기록
            }
        }
    }
}
