import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }

        // A는 오름차순으로 정렬
        Arrays.sort(A);

        // B는 내림차순으로 정렬
        Arrays.sort(B);
        reverse(B);  // B 배열을 뒤집어서 내림차순으로 정렬된 것처럼 만든다.

        // S 계산
        int S = 0;
        for (int i = 0; i < N; i++) {
            S += A[i] * B[i];
        }

        // 결과 출력
        System.out.println(S);
    }

    // 배열을 뒤집는 함수
    public static void reverse(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
