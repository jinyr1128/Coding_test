import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫째 줄에서 정수의 개수 N 입력 받기
        int N = scanner.nextInt();

        // 둘째 줄에서 정수 배열 입력 받기
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        // 셋째 줄에서 찾으려는 정수 v 입력 받기
        int v = scanner.nextInt();

        // v의 개수를 세기 위한 변수
        int count = 0;

        // 배열을 순회하면서 v의 개수를 센다
        for (int number : numbers) {
            if (number == v) {
                count++;
            }
        }

        // 결과 출력
        System.out.println(count);

        scanner.close();
    }
}
