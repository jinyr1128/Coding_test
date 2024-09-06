import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력값 받기
        int L = scanner.nextInt();  // 방학 총 일수
        int A = scanner.nextInt();  // 국어 페이지 수
        int B = scanner.nextInt();  // 수학 페이지 수
        int C = scanner.nextInt();  // 하루에 최대 풀 수 있는 국어 페이지 수
        int D = scanner.nextInt();  // 하루에 최대 풀 수 있는 수학 페이지 수

        // 국어와 수학을 모두 끝내기 위한 최소 일수 계산
        int daysForKorean = (A + C - 1) / C;  // 올림을 위해 (A + C - 1) / C 사용
        int daysForMath = (B + D - 1) / D;    // 올림을 위해 (B + D - 1) / D 사용

        // 숙제를 마치는데 걸리는 더 긴 시간을 사용
        int maxDaysForHomework = Math.max(daysForKorean, daysForMath);

        // 방학 총 일수에서 숙제 일수를 빼면 놀 수 있는 최대 날
        int daysForPlaying = L - maxDaysForHomework;

        // 결과 출력
        System.out.println(daysForPlaying);

        scanner.close();
    }
}
