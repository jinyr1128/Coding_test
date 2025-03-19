import java.io.*;
import java.util.*;

public class Main {
    static int n, d, k, c; // 접시의 수, 초밥 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호
    static int[] belt; // 초밥 벨트 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 벨트 위 접시 개수
        d = Integer.parseInt(st.nextToken()); // 초밥 종류 개수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹을 접시 개수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        belt = new int[n]; // 벨트 배열 생성
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMaxSushiVariety());
    }

    /**
     * 슬라이딩 윈도우 기법을 활용하여 최대로 먹을 수 있는 초밥 종류 개수 계산
     */
    static int getMaxSushiVariety() {
        Map<Integer, Integer> sushiCount = new HashMap<>();
        int maxVariety = 0;
        int currentVariety = 0;

        // 초기 k개 범위 세팅
        for (int i = 0; i < k; i++) {
            sushiCount.put(belt[i], sushiCount.getOrDefault(belt[i], 0) + 1);
        }
        currentVariety = sushiCount.size();

        // 쿠폰 초밥 추가 고려
        if (!sushiCount.containsKey(c)) {
            maxVariety = currentVariety + 1;
        } else {
            maxVariety = currentVariety;
        }

        // 슬라이딩 윈도우로 범위를 이동하며 최댓값 갱신
        for (int i = 1; i < n; i++) {
            // 제거할 초밥
            int removeIdx = i - 1;
            int removeSushi = belt[removeIdx];
            sushiCount.put(removeSushi, sushiCount.get(removeSushi) - 1);
            if (sushiCount.get(removeSushi) == 0) {
                sushiCount.remove(removeSushi);
                currentVariety--;
            }

            // 추가할 초밥
            int addIdx = (i + k - 1) % n;
            int addSushi = belt[addIdx];
            sushiCount.put(addSushi, sushiCount.getOrDefault(addSushi, 0) + 1);
            if (sushiCount.get(addSushi) == 1) {
                currentVariety++;
            }

            // 쿠폰 초밥 포함 여부 확인
            if (!sushiCount.containsKey(c)) {
                maxVariety = Math.max(maxVariety, currentVariety + 1);
            } else {
                maxVariety = Math.max(maxVariety, currentVariety);
            }
        }

        return maxVariety;
    }
}
