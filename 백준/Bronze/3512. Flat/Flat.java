import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 방의 개수와 1제곱미터당 비용 입력
        int n = sc.nextInt();  // 방의 개수
        int costPerSquareMeter = sc.nextInt();  // 1 제곱미터당 비용

        int totalArea = 0;     // 모든 방의 총 면적
        int bedroomArea = 0;   // 침실의 총 면적
        double effectiveArea = 0;  // 가격 계산을 위한 유효 면적 (발코니 면적 절반 포함)

        // 각 방의 정보를 처리
        for (int i = 0; i < n; i++) {
            int area = sc.nextInt();  // 방의 면적
            String roomType = sc.next();  // 방의 종류

            // 총 면적에는 모든 방의 면적을 더한다
            totalArea += area;

            // 침실의 경우 면적을 별도로 저장
            if (roomType.equals("bedroom")) {
                bedroomArea += area;
            }

            // 발코니는 절반 면적만 유효 면적에 포함
            if (roomType.equals("balcony")) {
                effectiveArea += area / 2.0;
            } else {
                // 다른 방들은 그대로 유효 면적에 포함
                effectiveArea += area;
            }
        }

        // 총 비용 계산
        double flatCost = effectiveArea * costPerSquareMeter;

        // 결과 출력
        System.out.println(totalArea);  // 모든 방의 총 면적 출력
        System.out.println(bedroomArea);  // 침실의 총 면적 출력
        System.out.printf("%.6f%n", flatCost);  // 총 가격 출력 (소수점 6자리까지)

        sc.close();
    }
}
