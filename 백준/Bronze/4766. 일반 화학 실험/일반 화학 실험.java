import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> temperatures = new ArrayList<>();

        // 온도 입력받기
        while (true) {
            double temp = sc.nextDouble();
            if (temp == 999) break;  // 999 입력 시 종료
            temperatures.add(temp);
        }

        // 차이 계산 및 출력
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 1; i < temperatures.size(); i++) {
            double difference = temperatures.get(i) - temperatures.get(i - 1);
            System.out.println(df.format(difference));  // 소수점 둘째 자리까지 출력
        }
        
        sc.close();
    }
}
