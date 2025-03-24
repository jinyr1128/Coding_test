import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            double earthWeight = sc.nextDouble();
            
            // 종료 조건: 음수 입력
            if (earthWeight < 0) {
                break;
            }

            double moonWeight = earthWeight * 0.167;

            // 소수점 둘째 자리까지 출력
            System.out.printf("Objects weighing %.2f on Earth will weigh %.2f on the moon.%n", earthWeight, moonWeight);
        }

        sc.close();
    }
}
