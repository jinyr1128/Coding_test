import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력을 받음
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 개수 입력
        int testCaseCount = sc.nextInt();

        // 각 부품의 가격 상수
        final double BLASTER_RIFLE = 350.34;
        final double VISUAL_SENSOR = 230.90;
        final double HEARING_SENSOR = 190.55;
        final double ARM = 125.30;
        final double LEG = 180.90;

        // 각 테스트 케이스에 대한 결과 계산 및 출력
        for (int i = 0; i < testCaseCount; i++) {
            // 각 부품의 개수 입력
            int a = sc.nextInt(); // 블래스터 라이플
            int b = sc.nextInt(); // 시각 센서
            int c = sc.nextInt(); // 청각 센서
            int d = sc.nextInt(); // 팔
            int e = sc.nextInt(); // 다리

            // 총 비용 계산
            double totalCost = a * BLASTER_RIFLE + b * VISUAL_SENSOR + c * HEARING_SENSOR + d * ARM + e * LEG;

            // 결과 출력 (소수점 둘째 자리까지)
            System.out.printf("$%.2f%n", totalCost);
        }

        // 스캐너 닫기
        sc.close();
    }
}
