import java.io.*;
import java.util.*;

class Main {

    static class Query {
        int left, right, index;

        public Query(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }
    }

    static int[] A;           // 수열
    static int[] Count;       // 값의 빈도수
    static int[] Answer;      // 쿼리의 답 저장
    static int uniqueCount;   // 서로 다른 수의 개수
    static int sqrtN;         // 블록 크기
    static Query[] queries;   // 쿼리 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        Count = new int[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        queries = new Query[M];
        Answer = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());
            queries[i] = new Query(I - 1, J - 1, i); // 0-based index
        }

        // 블록 크기 계산
        sqrtN = (int) Math.sqrt(N);

        // 쿼리 정렬
        Arrays.sort(queries, (q1, q2) -> {
            if (q1.left / sqrtN != q2.left / sqrtN) {
                return q1.left / sqrtN - q2.left / sqrtN;
            }
            return q1.right - q2.right;
        });

        // Mo's Algorithm
        processQueries();

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            bw.write(Answer[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void processQueries() {
        int currentLeft = 0, currentRight = -1;
        uniqueCount = 0;

        for (Query query : queries) {
            int left = query.left;
            int right = query.right;

            // 오른쪽 포인터 확장
            while (currentRight < right) {
                currentRight++;
                add(A[currentRight]);
            }
            // 오른쪽 포인터 축소
            while (currentRight > right) {
                remove(A[currentRight]);
                currentRight--;
            }
            // 왼쪽 포인터 확장
            while (currentLeft < left) {
                remove(A[currentLeft]);
                currentLeft++;
            }
            // 왼쪽 포인터 축소
            while (currentLeft > left) {
                currentLeft--;
                add(A[currentLeft]);
            }

            // 현재 쿼리 결과 저장
            Answer[query.index] = uniqueCount;
        }
    }

    static void add(int value) {
        if (Count[value] == 0) {
            uniqueCount++;
        }
        Count[value]++;
    }

    static void remove(int value) {
        Count[value]--;
        if (Count[value] == 0) {
            uniqueCount--;
        }
    }
}
