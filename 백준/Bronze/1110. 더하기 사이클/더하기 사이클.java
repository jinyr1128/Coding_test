import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        
        int cycleLength = calculateCycleLength(n);
        System.out.println(cycleLength);
    }

    public static int calculateCycleLength(int n) {
        int originalNumber = n;
        int count = 0;

        do {
            count++;
            
            // 두 자리 수로 변환
            int tens = n / 10;
            int units = n % 10;
            
            // 각 자릿수를 더한 값
            int sumDigits = tens + units;
            
            // 새로운 수 생성
            n = (units * 10) + (sumDigits % 10);
        } while (n != originalNumber);

        return count;
    }
}
