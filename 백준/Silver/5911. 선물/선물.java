import java.io.*;
import java.util.*;

public class Main {
    static class Item {
        int idx;
        long P, S;
        long full;   // P + S
        long disc;   // floor(P/2) + S
        Item(int idx, long P, long S) {
            this.idx = idx;
            this.P = P;
            this.S = S;
            this.full = P + S;
            this.disc = (P / 2) + S;
        }
    }

    static class Pair {
        long cost;
        int idx;
        Pair(long cost, int idx) { this.cost = cost; this.idx = idx; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long P = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            items[i] = new Item(i, P, S);
        }

        // 정가(P+S) 기준으로 오름차순 목록 (인덱스 보존)
        Pair[] sortedFull = new Pair[N];
        for (int i = 0; i < N; i++) {
            sortedFull[i] = new Pair(items[i].full, items[i].idx);
        }
        Arrays.sort(sortedFull, (a, b) -> {
            if (a.cost == b.cost) return Integer.compare(a.idx, b.idx);
            return Long.compare(a.cost, b.cost);
        });

        int answer = 0;

        // 쿠폰을 i번 친구에게 사용하고, i는 반드시 포함한 뒤
        // 남은 예산으로 정가 싼 순서대로 채운다(단 i는 제외)
        for (int i = 0; i < N; i++) {
            long budget = B - items[i].disc;
            if (budget < 0) {
                // i를 할인해도 못 사면 이 경우는 0명(혹은 다른 i에서 가능)
                continue;
            }
            int cnt = 1; // i는 이미 포함
            for (int k = 0; k < N; k++) {
                int idx = sortedFull[k].idx;
                if (idx == i) continue; // i는 이미 구매
                long c = sortedFull[k].cost;
                if (budget >= c) {
                    budget -= c;
                    cnt++;
                } else break; // 더 이상 못 삼
            }
            if (cnt > answer) answer = cnt;
        }

        // 혹시 쿠폰을 쓰지 않고 사는 경우(= 0명 선택 포함)도 비교 (이론상 위 루프로 커버되지만 안전하게)
        int noCoupon = 0;
        long budget = B;
        for (int k = 0; k < N; k++) {
            long c = sortedFull[k].cost;
            if (budget >= c) {
                budget -= c;
                noCoupon++;
            } else break;
        }
        answer = Math.max(answer, noCoupon);

        System.out.println(answer);
    }
}
