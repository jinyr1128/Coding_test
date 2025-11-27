import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄: N 입력
        int N = Integer.parseInt(br.readLine());
        
        // 두 번째 줄: 순열 A 입력
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        // 배열 A를 오른쪽으로 한 칸 회전시킨 순열 B를 출력
        // B[0]은 A의 마지막 원소(A[N-1])가 됨
        sb.append(A[N - 1]).append(" ");
        
        // 나머지 원소들(A[0]부터 A[N-2]까지)을 차례대로 출력
        for (int i = 0; i < N - 1; i++) {
            sb.append(A[i]).append(" ");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString().trim());
    }
}