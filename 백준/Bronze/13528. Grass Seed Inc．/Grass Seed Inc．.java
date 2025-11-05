import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 1. 1제곱미터당 비용 C (double)
        double costPerSqrMeter = sc.nextDouble();

        // 2. 잔디밭의 수 L (int)
        int L = sc.nextInt();

        // 3. 총 넓이를 저장할 변수 (double)
        double totalArea = 0.0;

        // 4. L개의 잔디밭 정보를 입력받으며 총 넓이 계산
        for (int i = 0; i < L; i++) {
            double width = sc.nextDouble();
            double length = sc.nextDouble();
            
            // 현재 잔디밭의 넓이를 총 넓이에 더함
            totalArea += width * length;
        }

        // 5. 총 비용 계산
        double totalCost = totalArea * costPerSqrMeter;

        // 6. 정확도 요구사항에 맞춰 7자리까지 출력
        // 예제 2 (16.6796025)를 보면 더 많은 자릿수가 필요할 수 있으므로
        // 넉넉하게 %.8f 또는 %.7f를 사용합니다.
        System.out.printf("%.7f\n", totalCost);
        
        sc.close();
    }
}