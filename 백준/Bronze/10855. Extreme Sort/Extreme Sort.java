import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력 (사실 배열 크기만 알면 되고, 자바에서는 split 등으로 처리하면 N을 직접 안 써도 되지만 입력 포맷을 따릅니다)
        String line1 = br.readLine();
        if (line1 == null) return;
        int N = Integer.parseInt(line1);

        // 수열 입력
        String line2 = br.readLine();
        if (line2 == null) return;
        
        StringTokenizer st = new StringTokenizer(line2);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 여부 확인 (오름차순이어야 함)
        boolean isSorted = true;
        for (int i = 0; i < N - 1; i++) {
            // 앞의 원소가 뒤의 원소보다 크다면 오름차순이 깨진 것
            if (arr[i] > arr[i + 1]) {
                isSorted = false;
                break;
            }
        }

        // 결과 출력
        if (isSorted) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}