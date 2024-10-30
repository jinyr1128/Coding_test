import java.util.Scanner;

public class Main {

    static int N;
    static int[] A;
    static int[] B;
    static int[] segmentTree;
    static final int MAX = 1_000_001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = new int[N];
        B = new int[MAX];
        segmentTree = new int[N * 4];

        // A열 입력
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        // B열 입력 및 인덱스 저장
        for (int i = 0; i < N; i++) {
            int machineId = sc.nextInt();
            B[machineId] = i;
        }

        long result = 0;

        // 교차하는 케이블 쌍의 개수 계산
        for (int i = 0; i < N; i++) {
            int position = B[A[i]];
            result += sum(1, 0, N - 1, position + 1, N - 1);
            update(1, 0, N - 1, position);
        }

        System.out.println(result);
        sc.close();
    }

    // 세그먼트 트리의 구간 합을 구하는 함수
    static long sum(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) 
             + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    // 세그먼트 트리를 업데이트하는 함수
    static void update(int node, int start, int end, int idx) {
        if (start > idx || end < idx) {
            return;
        }
        if (start == end) {
            segmentTree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx);
        update(node * 2 + 1, mid + 1, end, idx);
        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }
}
