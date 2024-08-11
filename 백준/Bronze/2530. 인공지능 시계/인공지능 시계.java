import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 현재 시각 입력 받기
        int currentHour = scanner.nextInt();
        int currentMinute = scanner.nextInt();
        int currentSecond = scanner.nextInt();

        // 요리하는 데 필요한 시간 (초 단위)
        int additionalSeconds = scanner.nextInt();

        // 총 초 계산
        int totalSeconds = currentSecond + additionalSeconds;
        int newSecond = totalSeconds % 60;
        int totalMinutes = currentMinute + (totalSeconds / 60);
        int newMinute = totalMinutes % 60;
        int newHour = (currentHour + (totalMinutes / 60)) % 24;

        // 결과 출력
        System.out.println(newHour + " " + newMinute + " " + newSecond);

        scanner.close();
    }
}
