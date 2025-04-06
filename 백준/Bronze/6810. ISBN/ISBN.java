import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner로 입력받기
        Scanner scanner = new Scanner(System.in);

        // 고정된 처음 10자리 숫자
        int[] isbn = {9, 7, 8, 0, 9, 2, 1, 4, 1, 8};

        // 마지막 3자리 입력 받아 배열에 추가
        for (int i = 0; i < 3; i++) {
            isbn = append(isbn, scanner.nextInt());
        }

        int sum = 0;
        // 1-3-sum 계산
        for (int i = 0; i < 13; i++) {
            if (i % 2 == 0) {
                sum += isbn[i];         // 홀수 자리: 1배
            } else {
                sum += isbn[i] * 3;     // 짝수 자리: 3배
            }
        }

        // 결과 출력
        System.out.println("The 1-3-sum is " + sum);
    }

    // 배열에 요소 추가하는 유틸 메서드
    private static int[] append(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = value;
        return newArr;
    }
}
