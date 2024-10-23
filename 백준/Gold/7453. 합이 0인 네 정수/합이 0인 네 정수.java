import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][4];
        
        // 배열 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[] A = new int[N * N];
        int[] B = new int[N * N];
        int index = 0;

        // A 배열과 B 배열 생성 (두 배열의 합)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[index] = arr[i][0] + arr[j][1];
                B[index] = arr[i][2] + arr[j][3];
                index++;
            }
        }

        // A와 B 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        long answer = 0;

        // A의 각 원소에 대해 B에서 대응되는 원소의 개수를 찾기
        for (int i = 0; i < A.length; i++) {
            int target = -A[i];
            
            // lower_bound와 upper_bound를 찾아서 그 사이의 개수를 셉니다.
            int lower = lowerBound(B, target);
            int upper = upperBound(B, target);
            
            answer += (upper - lower);
        }

        // 결과 출력
        System.out.println(answer);
    }

    // lower_bound를 구현하는 함수
    private static int lowerBound(int[] arr, int key) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // upper_bound를 구현하는 함수
    private static int upperBound(int[] arr, int key) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
