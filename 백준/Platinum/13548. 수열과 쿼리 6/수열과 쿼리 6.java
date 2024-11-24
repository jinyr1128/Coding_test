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

    static int[] A;              // 수열
    static int[] Count;          // 특정 수의 빈도수
    static int[] CountCount;     // 빈도수의 빈도수
    static int[] Answer;         // 쿼리의 결과 저장
    static int maxFrequency;     // 가장 많이 등장한 수의 빈도수
    static int sqrtN;            // 블록 크기
    static Query[] queries;      // 쿼리 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        Count = new int[100001];
        CountCount = new int[100001];

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

        // Mo's Algorithm 처리
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
        maxFrequency = 0;

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
            Answer[query.index] = maxFrequency;
        }
    }

    static void add(int value) {
        // 기존 빈도수를 줄임
        if (Count[value] > 0) {
            CountCount[Count[value]]--;
        }
        // 새로운 빈도수 증가
        Count[value]++;
        CountCount[Count[value]]++;
        // 최댓값 갱신
        maxFrequency = Math.max(maxFrequency, Count[value]);
    }

    static void remove(int value) {
        // 기존 빈도수를 줄임
        CountCount[Count[value]]--;
        if (Count[value] == maxFrequency && CountCount[Count[value]] == 0) {
            maxFrequency--;
        }
        // 새로운 빈도수 감소
        Count[value]--;
        if (Count[value] > 0) {
            CountCount[Count[value]]++;
        }
    }
}
