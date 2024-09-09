import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            // 출근 시간 입력 (시, 분, 초)
            int startHour = scanner.nextInt();
            int startMinute = scanner.nextInt();
            int startSecond = scanner.nextInt();

            // 퇴근 시간 입력 (시, 분, 초)
            int endHour = scanner.nextInt();
            int endMinute = scanner.nextInt();
            int endSecond = scanner.nextInt();

            // 출근 시간과 퇴근 시간의 초 단위로 변환
            int startTimeInSeconds = startHour * 3600 + startMinute * 60 + startSecond;
            int endTimeInSeconds = endHour * 3600 + endMinute * 60 + endSecond;

            // 총 근무 시간을 초 단위로 계산
            int workTimeInSeconds = endTimeInSeconds - startTimeInSeconds;

            // 근무 시간을 다시 시, 분, 초로 변환
            int workHour = workTimeInSeconds / 3600;
            workTimeInSeconds %= 3600;
            int workMinute = workTimeInSeconds / 60;
            int workSecond = workTimeInSeconds % 60;

            // 근무 시간 출력
            System.out.println(workHour + " " + workMinute + " " + workSecond);
        }

        scanner.close();
    }
}