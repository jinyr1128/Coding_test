import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int startDay = 11, startHour = 11, startMinute = 11;
        
        int endDay = sc.nextInt();
        int endHour = sc.nextInt();
        int endMinute = sc.nextInt();

        // 시작 시간과 종료 시간을 분 단위로 계산
        int startTotalMinutes = (startDay * 24 * 60) + (startHour * 60) + startMinute;
        int endTotalMinutes = (endDay * 24 * 60) + (endHour * 60) + endMinute;

        int result = endTotalMinutes - startTotalMinutes;

        // 종료 시간이 시작 시간보다 빠르면 -1 출력, 아니면 소요 시간 출력
        if (result < 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

        sc.close();
    }
}
