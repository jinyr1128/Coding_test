import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        // 배열에 담아서 정렬
        int[] arr = {a, b, c};
        Arrays.sort(arr);

        // 정렬된 배열의 두 번째로 큰 값은 가운데 값 (인덱스 1)
        System.out.println(arr[1]);
    }
}
