import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 현재 시각 입력 받기
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        // 요리하는 데 필요한 시간 입력 받기
        int C = scanner.nextInt();

        // 현재 시각을 분으로 환산
        int totalMinutes = A * 60 + B;
        // 요리 시간을 더함
        totalMinutes += C;

        // 종료 시각 계산
        int endHour = (totalMinutes / 60) % 24;
        int endMinute = totalMinutes % 60;

        // 결과 출력
        System.out.println(endHour + " " + endMinute);

        scanner.close();
    }
}
