import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 날짜의 일의 자리 숫자 입력
        int date = sc.nextInt();
        int violationCount = 0;
        
        // 5대의 자동차 번호의 일의 자리 숫자를 입력받아 위반 여부 체크
        for (int i = 0; i < 5; i++) {
            int carNumber = sc.nextInt();
            // 자동차 번호의 일의 자리 숫자가 날짜와 같으면 위반
            if (carNumber == date) {
                violationCount++;
            }
        }
        
        // 위반 차량 대수 출력
        System.out.println(violationCount);
        
        sc.close();
    }
}