import java.io.*;
import java.util.*;

public class Main {

    // 금광 정보를 저장하는 클래스
    static class Mine {
        int x, y, weight;

        Mine(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    // 세그먼트 트리 노드를 저장하는 클래스
    static class SegmentTreeNode {
        long leftMax, rightMax, max, sum;

        SegmentTreeNode() {}

        SegmentTreeNode(long value) {
            this.leftMax = this.rightMax = this.max = this.sum = value;
        }

        SegmentTreeNode(long leftMax, long rightMax, long max, long sum) {
            this.leftMax = leftMax;
            this.rightMax = rightMax;
            this.max = max;
            this.sum = sum;
        }
    }

    static int N, uniqueXCount;
    static ArrayList<Integer> xList = new ArrayList<>();
    static HashMap<Integer, Integer> xCoordinateMap = new HashMap<>();
    static Mine[] mines;
    static SegmentTreeNode[] segmentTree;
    static ArrayList<ArrayList<Mine>> mineLists = new ArrayList<>();
    static final SegmentTreeNode DUMMY_NODE = new SegmentTreeNode();
    static long maxGold;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mines = new Mine[N];

        // 입력을 받아 금광 정보를 저장하고 x 좌표 리스트
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            mines[i] = new Mine(x, y, weight);
            xList.add(x);
        }

        // x 좌표를 정렬하고 유일한 값들에 대한 매핑
        xList.sort(Comparator.naturalOrder());
        for (Integer x : xList) {
            if (!xCoordinateMap.containsKey(x)) {
                xCoordinateMap.put(x, ++uniqueXCount);
            }
        }

        // 금광들의 x 좌표를 매핑된 값으로 변경
        for (int i = 0; i < N; i++) {
            mines[i].x = xCoordinateMap.get(mines[i].x);
        }

        // y 좌표를 기준으로 금광 배열을 정렬
        Arrays.sort(mines, Comparator.comparingInt(mine -> mine.y));

        // y 좌표가 같은 금광들을 그룹으로 분할
        int prevY = Integer.MIN_VALUE;
        for (Mine mine : mines) {
            if (mine.y != prevY) {
                mineLists.add(new ArrayList<>());
            }
            mineLists.get(mineLists.size() - 1).add(mine);
            prevY = mine.y;
        }

        // 세그먼트 트리를 초기화
        segmentTree = new SegmentTreeNode[4 * uniqueXCount];

        // 각 y 좌표 그룹에 대해 최대 금의 양을 계산합니다.
        for (int start = 0; start < mineLists.size(); start++) {
            Arrays.fill(segmentTree, DUMMY_NODE);
            for (int end = start; end < mineLists.size(); end++) {
                for (Mine mine : mineLists.get(end)) {
                    updateSegmentTree(1, 1, uniqueXCount, mine.x, mine.weight);
                }
                maxGold = Math.max(maxGold, segmentTree[1].max);
            }
        }

        System.out.println(maxGold);
    }

    // 세그먼트 트리를 업데이트
    static void updateSegmentTree(int node, int start, int end, int target, long value) {
        if (target > end || target < start) {
            return;
        }
        if (start == target && target == end) {
            segmentTree[node] = new SegmentTreeNode(segmentTree[node].max + value);
            return;
        }
        int mid = (start + end) / 2;
        updateSegmentTree(node * 2, start, mid, target, value);
        updateSegmentTree(node * 2 + 1, mid + 1, end, target, value);
        segmentTree[node] = mergeNodes(segmentTree[node * 2], segmentTree[node * 2 + 1]);
    }

    // 두 노드를 병합 -> 새로운 노드를 생성
    static SegmentTreeNode mergeNodes(SegmentTreeNode left, SegmentTreeNode right) {
        return new SegmentTreeNode(
            Math.max(left.leftMax, left.sum + right.leftMax),
            Math.max(right.rightMax, right.sum + left.rightMax),
            Math.max(Math.max(left.max, right.max), left.rightMax + right.leftMax),
            left.sum + right.sum
        );
    }
}
