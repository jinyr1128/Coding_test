import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static ArrayDeque<Integer> q1 = new ArrayDeque<>();
    static ArrayDeque<Integer> q2 = new ArrayDeque<>();
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        tree = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                q1.add(i);
            } else {
                q2.add(i);
            }
            // 펜윅 트리 초기화: 모든 자리에 사람이 있음 (값 1)
            update(i, 1);
        }

        int time = 0;
        int total = n; // 남은 학생 수

        while (total > 0) {
            time++;

            // 현재 줄에서 min(K, total) 번째 학생의 실제 인덱스를 찾음
            int limitCount = Math.min(k, total);
            int limitIdx = findKth(limitCount);

            boolean has1 = false;
            boolean has2 = false;

            // 1학년 큐의 맨 앞 학생이 범위 내에 있는지 확인
            if (!q1.isEmpty() && q1.peek() <= limitIdx) {
                has1 = true;
            }

            // 2학년 큐의 맨 앞 학생이 범위 내에 있는지 확인
            if (!q2.isEmpty() && q2.peek() <= limitIdx) {
                has2 = true;
            }

            // 조건에 따라 학생 제거 및 트리 업데이트
            if (has1 && has2) {
                int idx1 = q1.poll();
                int idx2 = q2.poll();
                update(idx1, -1);
                update(idx2, -1);
                total -= 2;
            } else if (has1) {
                int idx1 = q1.poll();
                update(idx1, -1);
                total -= 1;
            } else if (has2) {
                int idx2 = q2.poll();
                update(idx2, -1);
                total -= 1;
            }
        }

        System.out.println(time);
    }

    // 펜윅 트리 업데이트
    static void update(int idx, int diff) {
        while (idx <= n) {
            tree[idx] += diff;
            idx += (idx & -idx);
        }
    }

    // 펜윅 트리: k번째 활성화된(남아있는) 인덱스를 이분 탐색(Binary Lifting)으로 찾기
    static int findKth(int k) {
        int idx = 0;
        // n보다 작거나 같은 2의 거듭제곱 중 가장 큰 값부터 시작하여 비트 단위로 탐색
        // 100,000 이하이므로 2^16 = 65536 부터 확인하면 충분함 (17번째 비트)
        for (int i = 1 << 17; i > 0; i >>= 1) {
            int nextIdx = idx + i;
            if (nextIdx <= n && tree[nextIdx] < k) {
                idx = nextIdx;
                k -= tree[idx];
            }
        }
        return idx + 1;
    }
}