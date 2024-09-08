import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 각 이동 시간을 초 단위로 입력받기
        int time1 = scanner.nextInt(); // 집 -> 학교
        int time2 = scanner.nextInt(); // 학교 -> PC방
        int time3 = scanner.nextInt(); // PC방 -> 학원
        int time4 = scanner.nextInt(); // 학원 -> 집

        // 총 이동 시간을 계산
        int totalSeconds = time1 + time2 + time3 + time4;

        // 분과 초로 변환
        int minutes = totalSeconds / 60; // 총 초에서 분 구하기
        int seconds = totalSeconds % 60; // 나머지 초 구하기

        // 결과 출력
        System.out.println(minutes);
        System.out.println(seconds);

        scanner.close();
    }
}