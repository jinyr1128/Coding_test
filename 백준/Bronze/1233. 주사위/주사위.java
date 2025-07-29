import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S1 = Integer.parseInt(st.nextToken());
        int S2 = Integer.parseInt(st.nextToken());
        int S3 = Integer.parseInt(st.nextToken());

        int maxSum = S1 + S2 + S3;           // 가능한 최고 합
        int[] freq = new int[maxSum + 1];    // freq[sum] = 발생 횟수

        /* 모든 눈 조합을 완전 탐색 */
        for (int a = 1; a <= S1; a++) {
            for (int b = 1; b <= S2; b++) {
                for (int c = 1; c <= S3; c++) {
                    freq[a + b + c]++;
                }
            }
        }

        int bestSum = 3;          // 최솟값부터 검색 (3 = 1+1+1)
        int bestCnt = freq[3];

        for (int s = 4; s <= maxSum; s++) {
            if (freq[s] > bestCnt) {
                bestCnt = freq[s];
                bestSum = s;      // 더 높은 빈도 발견
            }
        }

        System.out.println(bestSum);
    }
}
