import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 세 개의 정수 입력 받기
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = scanner.nextInt();
        }
        
        // 오름차순으로 정렬
        Arrays.sort(numbers);
        
        // 정렬된 결과 출력
        for (int i = 0; i < 3; i++) {
            System.out.print(numbers[i] + " ");
        }
        
        scanner.close();
    }
}
