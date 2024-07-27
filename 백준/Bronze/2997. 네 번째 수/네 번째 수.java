import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = scanner.nextInt();
        }
        scanner.close();

        Arrays.sort(numbers); // 정렬하여 숫자들을 크기 순서대로 정렬

        // 차이를 계산
        int d1 = numbers[1] - numbers[0];
        int d2 = numbers[2] - numbers[1];

        // 네 번째 수를 찾기 위해 조건을 체크
        if (d1 == d2) {
            // 등차수열이 이미 유지되고 있는 경우, 양쪽에 가능한 값을 반환
            System.out.println(numbers[2] + d1); // 오른쪽에 추가
            System.out.println(numbers[0] - d1); // 왼쪽에 추가
        } else if (d1 > d2) {
            System.out.println(numbers[0] + d2);
        } else {
            System.out.println(numbers[1] + d1);
        }
    }
}
