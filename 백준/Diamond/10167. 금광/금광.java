import java.io.*;
import java.util.*;

class GoldMine implements Comparable<GoldMine> {
    int y, x, w;

    public GoldMine(int x, int y, int w) {
        this.y = y;
        this.x = x;
        this.w = w;
    }

    @Override
    public int compareTo(GoldMine o) {
        return this.y - o.y;
    }
}

class SegmentTreeNode {
    long rightVal, leftVal, maxVal, sum;

    public SegmentTreeNode(long rightVal, long leftVal, long maxVal, long sum) {
        this.rightVal = rightVal;
        this.leftVal = leftVal;
        this.maxVal = maxVal;
        this.sum = sum;
    }
}

public class Main {

    static SegmentTreeNode[] segmentTree;
    static int[] xCoordinates;
    static long maxGold = 0;
    static int treeSize = 2, uniqueXCount, uniqueYCount;
    static final long MIN_VALUE = (long) -1e15;

    // 세그먼트 트리 구성
    static void constructSegmentTree() {
        for (int i = treeSize / 2 - 1; i > 0; i--) {
            long rightVal, leftVal, maxVal, sum;
            rightVal = Math.max(segmentTree[i * 2 + 1].sum + segmentTree[i * 2].rightVal, segmentTree[i * 2 + 1].rightVal);
            leftVal = Math.max(segmentTree[i * 2].sum + segmentTree[i * 2 + 1].leftVal, segmentTree[i * 2].leftVal);
            maxVal = Math.max(segmentTree[i * 2].maxVal, segmentTree[i * 2 + 1].maxVal);
            maxVal = Math.max(segmentTree[i * 2].rightVal + segmentTree[i * 2 + 1].leftVal, maxVal);
            sum = segmentTree[i * 2].sum + segmentTree[i * 2 + 1].sum;
            segmentTree[i] = new SegmentTreeNode(rightVal, leftVal, maxVal, sum);
        }
    }

    // 세그먼트 트리 업데이트
    static void updateSegmentTree(int index, int value) {
        index += treeSize / 2;
        segmentTree[index].leftVal += value;
        segmentTree[index].rightVal += value;
        segmentTree[index].sum += value;
        segmentTree[index].maxVal += value;
        while (index > 1) {
            index /= 2;
            long rightVal, leftVal, maxVal, sum;
            rightVal = Math.max(segmentTree[index * 2 + 1].sum + segmentTree[index * 2].rightVal, segmentTree[index * 2 + 1].rightVal);
            leftVal = Math.max(segmentTree[index * 2].sum + segmentTree[index * 2 + 1].leftVal, segmentTree[index * 2].leftVal);
            maxVal = Math.max(segmentTree[index * 2].maxVal, segmentTree[index * 2 + 1].maxVal);
            maxVal = Math.max(segmentTree[index * 2].rightVal + segmentTree[index * 2 + 1].leftVal, maxVal);
            sum = segmentTree[index * 2].sum + segmentTree[index * 2 + 1].sum;
            segmentTree[index] = new SegmentTreeNode(rightVal, leftVal, maxVal, sum);
        }
    }

    // 세그먼트 트리 초기화
    static void initializeSegmentTree() {
        segmentTree = new SegmentTreeNode[treeSize];
        for (int i = 0; i < treeSize / 2; i++) {
            if (i < uniqueXCount) {
                segmentTree[treeSize / 2 + i] = new SegmentTreeNode(0, 0, 0, 0);
            } else {
                segmentTree[treeSize / 2 + i] = new SegmentTreeNode(MIN_VALUE, MIN_VALUE, MIN_VALUE, 0);
            }
        }
    }

    // 세그먼트 트리에서 최대값 가져오기
    static long getMaxValue() {
        long max = 0;
        max = Math.max(segmentTree[1].leftVal, segmentTree[1].rightVal);
        max = Math.max(segmentTree[1].maxVal, max);
        max = Math.max(segmentTree[1].sum, max);
        return max;
    }

    public static void main(String[] args) throws IOException {
        Main mainClass = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        xCoordinates = new int[N];

        ArrayList<GoldMine> goldMines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            xCoordinates[i] = x;
            goldMines.add(new GoldMine(x, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Arrays.sort(xCoordinates);
        int previous = Integer.MIN_VALUE;
        HashMap<Integer, Integer> xCoordinateIndex = new HashMap<>();
        uniqueXCount = 0;
        for (int i = 0; i < xCoordinates.length; i++) {
            if (xCoordinates[i] != previous) {
                xCoordinateIndex.put(xCoordinates[i], ++uniqueXCount);
                previous = xCoordinates[i];
            }
        }
        Collections.sort(goldMines);
        previous = Integer.MIN_VALUE;
        uniqueYCount = 0;
        for (int i = 0; i < N; i++) {
            GoldMine goldMine = goldMines.get(i);
            if (goldMine.y == previous) {
                goldMine.y = uniqueYCount;
            } else {
                previous = goldMine.y;
                goldMine.y = ++uniqueYCount;
            }
            goldMine.x = xCoordinateIndex.get(goldMine.x);
        }

        while (true) {
            if (treeSize >= uniqueXCount) {
                treeSize *= 2;
                break;
            }
            treeSize *= 2;
        }
        for (int i = 1; i <= uniqueYCount; i++) {
            mainClass.initializeSegmentTree();
            mainClass.constructSegmentTree();
            int lastY = i;
            for (GoldMine goldMine : goldMines) {
                if (goldMine.y < i) continue;
                if (goldMine.y == lastY) {
                    mainClass.updateSegmentTree(goldMine.x - 1, goldMine.w);
                } else {
                    maxGold = Math.max(mainClass.getMaxValue(), maxGold);
                    lastY = goldMine.y;
                    mainClass.initializeSegmentTree();
                    mainClass.constructSegmentTree();
                    mainClass.updateSegmentTree(goldMine.x - 1, goldMine.w);
                }
            }
            maxGold = Math.max(mainClass.getMaxValue(), maxGold);
        }
        System.out.println(maxGold);
    }
}
