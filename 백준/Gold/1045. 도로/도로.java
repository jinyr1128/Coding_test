import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] makeSet, ranks, result;
    static Queue<int[]> edgeList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        // 초기화
        makeSet = new int[N];
        ranks = new int[N];
        result = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet[i] = i;
            ranks[i] = 1;
        }

        int cityCnt = 0;

        // 인접 행렬 입력 및 처리
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = i + 1; j < N; j++) {
                if (line.charAt(j) == 'Y') {
                    if (union(i, j)) {
                        cityCnt++;
                        result[i]++;
                        result[j]++;
                    } else {
                        edgeList.add(new int[]{i, j});
                    }
                }
            }
        }

        // 최소 연결 요구 조건 확인
        if (cityCnt < N - 1 || cityCnt + edgeList.size() < M) {
            System.out.println(-1);
        } else {
            int remainCnt = M - cityCnt;

            // 남은 간선을 추가하여 M개의 도로를 확보
            while (remainCnt > 0 && !edgeList.isEmpty()) {
                int[] edge = edgeList.poll();
                result[edge[0]]++;
                result[edge[1]]++;
                remainCnt--;
            }

            // 결과 출력
            for (int res : result) {
                System.out.print(res + " ");
            }
        }
    }

    // Find 함수 (경로 압축)
    static int findParents(int x) {
        if (makeSet[x] != x) {
            makeSet[x] = findParents(makeSet[x]);
        }
        return makeSet[x];
    }

    // Union 함수 (랭크 기반)
    static boolean union(int x, int y) {
        int rootX = findParents(x);
        int rootY = findParents(y);

        if (rootX != rootY) {
            if (ranks[rootX] < ranks[rootY]) {
                makeSet[rootX] = rootY;
            } else if (ranks[rootX] > ranks[rootY]) {
                makeSet[rootY] = rootX;
            } else {
                makeSet[rootY] = rootX;
                ranks[rootX]++;
            }
            return true;
        }
        return false;
    }
}
