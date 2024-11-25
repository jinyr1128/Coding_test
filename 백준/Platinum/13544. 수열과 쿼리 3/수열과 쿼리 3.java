import java.io.*;
import java.util.*;

class Main {

    static int N, M, lastAnswer = 0;
    static ArrayList<Integer>[] tree; // 세그먼트 트리
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 초기화
        tree = new ArrayList[4 * N];
        for (int i = 0; i < 4 * N; i++) {
            tree[i] = new ArrayList<>();
        }
        init(1, 0, N - 1);

        // 쿼리 처리
        M = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // XOR로 i, j, k 계산
            int i1 = (a ^ lastAnswer);
            int j1 = (b ^ lastAnswer);
            int k1 = (c ^ lastAnswer);

            // i와 j를 1-based index에서 0-based index로 변환
            i1--;
            j1--;

            // 쿼리 결과 계산
            lastAnswer = query(1, 0, N - 1, i1, j1, k1);
            bw.write(lastAnswer + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node].add(arr[start]);
        } else {
            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);

            // 두 자식 노드의 데이터를 병합
            merge(tree[node * 2], tree[node * 2 + 1], tree[node]);
        }
    }

    static void merge(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> result) {
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
    }

    static int query(int node, int start, int end, int L, int R, int val) {
        if (end < L || R < start) {
            return 0; // 범위를 벗어난 경우
        }
        if (L <= start && end <= R) {
            // 현재 구간이 [L, R]에 완전히 포함되는 경우
            return tree[node].size() - upperBound(tree[node], val);
        }
        int mid = (start + end) / 2;
        int leftResult = query(node * 2, start, mid, L, R, val);
        int rightResult = query(node * 2 + 1, mid + 1, end, L, R, val);
        return leftResult + rightResult;
    }

    static int upperBound(ArrayList<Integer> list, int value) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) > value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
