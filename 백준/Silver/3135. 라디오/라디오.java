import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 현재 주파수 A, 목표 주파수 B
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 즐겨찾기 개수 N (≤ 5)
        int N = Integer.parseInt(br.readLine().trim());

        // 즐겨찾기 버튼을 전혀 쓰지 않을 때
        int answer = Math.abs(A - B);

        // 각 즐겨찾기 버튼을 한 번만 눌러 보는 경우
        for (int i = 0; i < N; i++) {
            int fav = Integer.parseInt(br.readLine().trim());
            answer = Math.min(answer, 1 + Math.abs(fav - B));
        }

        System.out.println(answer);
    }
}
