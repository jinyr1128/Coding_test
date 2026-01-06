import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 문제에서 지정한 PI 값 사용 (Math.PI 대신 사용해야 함)
        final double PI = 3.14159;

        while (sc.hasNext()) {
            String command = sc.next();

            // "ENDOFINPUT"을 만나면 프로그램 종료
            if (command.equals("ENDOFINPUT")) {
                break;
            }

            // "START" 다음에 오는 숫자 3개를 읽음
            int radius = sc.nextInt();    // X: 반지름
            int fuel = sc.nextInt();      // Y: 연료량
            int angle = sc.nextInt();     // Z: 각도

            // "END" 문자열 소비
            sc.next();

            // [수정된 부분] 최단 거리 이동을 위해 각도 보정
            // 각도가 180도보다 크다면 반대쪽(더 짧은 쪽)으로 이동
            if (angle > 180) {
                angle = 360 - angle;
            }

            // 1. 필요한 왕복 거리 계산
            // 원의 둘레 = 2 * PI * r
            // 편도 거리 = 원의 둘레 * (각도 / 360)
            // 왕복 거리 = 편도 거리 * 2
            double requiredDistance = 2.0 * (2.0 * PI * radius * (angle / 360.0));

            // 2. 현재 연료로 갈 수 있는 최대 거리 계산 (1갤런당 5마일)
            double maxDistance = fuel * 5.0;

            // 3. 비교 및 출력
            if (maxDistance >= requiredDistance) {
                // 갈 수 있는 경우: 남은 연료 계산
                double fuelConsumed = requiredDistance / 5.0;
                
                // 남은 연료 = 전체 연료 - 소모 연료
                // 소수점 버림(truncate)을 위해 int로 캐스팅
                int fuelLeft = (int)(fuel - fuelConsumed);
                System.out.println("YES " + fuelLeft);
            } else {
                // 갈 수 없는 경우: 최대 이동 거리 출력
                // fuel * 5는 정수이므로 int로 캐스팅하여 출력
                System.out.println("NO " + (int)maxDistance);
            }
        }
        
        sc.close();
    }
}