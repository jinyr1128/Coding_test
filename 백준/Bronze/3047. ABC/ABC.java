import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        
        // 세 수 입력 받기
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = scanner.nextInt();
        }
        
        // 순서 입력 받기
        String order = scanner.next();

        // 입력된 수 정렬
        Arrays.sort(numbers);

        // 결과를 저장할 배열
        int[] result = new int[3];

        // 순서에 맞게 정렬된 숫자를 result에 저장
        for (int i = 0; i < 3; i++) {
            char c = order.charAt(i);
            switch (c) {
                case 'A':
                    result[i] = numbers[0];
                    break;
                case 'B':
                    result[i] = numbers[1];
                    break;
                case 'C':
                    result[i] = numbers[2];
                    break;
            }
        }

        // 결과 출력
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
        
        // Scanner 닫기
        scanner.close();
    }
}
