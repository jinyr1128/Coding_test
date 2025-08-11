import java.io.*;
import java.util.*;

public class Main {

    static int N, Q;
    static long K;                 // 찾고 싶은 교환 번호 (1-indexed)
    static int[] A;

    static long swapCnt = 0;       // 지금까지 발생한 교환 수
    static boolean found = false;  // K번째 교환을 찾았는가
    static int ansSmall = -1, ansLarge = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        // 평균 선형 시간 선택(Quickselect) – 의사코드와 동일한 분할/결정, 반복으로 구현
        selectIter(0, N - 1, Q);

        if (found) {
            System.out.println(ansSmall + " " + ansLarge);
        } else {
            System.out.println(-1);
        }
    }

    // 의사코드의 select를 반복문으로 구현 (스택 오버플로우 방지)
    static void selectIter(int p, int r, int q) {
        while (!found && p < r) {
            int t = partition(p, r);           // 분할 (오른쪽 끝을 피벗)
            if (found) return;                 // K번째 교환을 찾았으면 더 진행 불필요
            int k = t - p + 1;                 // 피벗이 서브배열에서 k번째 작은
            if (q < k) {
                r = t - 1;                     // 왼쪽으로 범위를 좁힘
            } else if (q == k) {
                return;                        // 찾는 원소가 피벗
            } else {
                p = t + 1;                     // 오른쪽으로 범위를 좁힘
                q -= k;
            }
        }
    }

    // 의사코드의 partition: Lomuto 방식, A[r]을 피벗으로 사용
    static int partition(int p, int r) {
        int x = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (A[j] <= x) {
                i++;
                swapAndCount(i, j);            // A[++i] <-> A[j]
                if (found) return i;           // 이후 값은 의미 없어 즉시 반환
            }
        }
        if (i + 1 != r) {                      // 의사코드: 다를 때만 교환
            swapAndCount(i + 1, r);            // A[i+1] <-> A[r]
            if (found) return i + 1;
        }
        return i + 1;
    }

    // 스왑을 수행하며 K번째 스왑일 경우 결과값을 기록
    static void swapAndCount(int i, int j) {
        int a = A[i], b = A[j];                // “교환되는 두 수”는 교환 직전 값
        swapCnt++;
        if (!found && swapCnt == K) {
            if (a <= b) { ansSmall = a; ansLarge = b; }
            else { ansSmall = b; ansLarge = a; }
            found = true;
        }
        // 실제 배열 교환 (자기 자신과 교환도 의사코드대로 수행하며 카운트 포함)
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
