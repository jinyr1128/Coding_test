import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 거스름돈 액수 입력
        scanner.close();

        int count = 0; // 최소 동전 개수를 저장할 변수

        // 최대한 많은 5원짜리를 사용하고 남은 돈을 2원으로 거슬러 줄 수 있는지 확인
        while (n >= 0) {
            if (n % 5 == 0) { // 남은 돈이 5원으로 나누어떨어지는 경우
                count += n / 5; // 5원짜리 동전 개수 추가
                System.out.println(count);
                return;
            }
            n -= 2; // 5원으로 나누어떨어지지 않으면 2원짜리 동전 하나 사용
            count++; // 동전 개수 증가
        }

        // 정확히 거슬러 줄 수 없는 경우 -1 출력
        System.out.println(-1);
    }
}
