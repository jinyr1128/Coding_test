import java.util.Scanner;

public class Main {

    static int[] heights;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            heights = new int[n];
            for (int i = 0; i < n; i++) {
                heights[i] = sc.nextInt();
            }

            System.out.println(findMaxRectangleArea(0, n - 1));
        }
        sc.close();
    }

    // 분할 정복을 이용하여 최대 넓이를 구하는 함수
    private static long findMaxRectangleArea(int left, int right) {
        if (left > right) return 0;
        if (left == right) return heights[left];

        int mid = (left + right) / 2;

        long maxLeftArea = findMaxRectangleArea(left, mid);
        long maxRightArea = findMaxRectangleArea(mid + 1, right);
        long maxMidArea = calculateMaxAreaInMiddle(left, right, mid);

        return Math.max(Math.max(maxLeftArea, maxRightArea), maxMidArea);
    }

    // 중간 부분을 포함하는 최대 넓이 계산 함수
    private static long calculateMaxAreaInMiddle(int left, int right, int mid) {
        int leftIdx = mid;
        int rightIdx = mid;
        int minHeight = heights[mid];
        long maxArea = heights[mid];

        while (leftIdx > left || rightIdx < right) {
            if (rightIdx < right && (leftIdx == left || heights[leftIdx - 1] < heights[rightIdx + 1])) {
                rightIdx++;
                minHeight = Math.min(minHeight, heights[rightIdx]);
            } else {
                leftIdx--;
                minHeight = Math.min(minHeight, heights[leftIdx]);
            }
            maxArea = Math.max(maxArea, (long) minHeight * (rightIdx - leftIdx + 1));
        }

        return maxArea;
    }
}
