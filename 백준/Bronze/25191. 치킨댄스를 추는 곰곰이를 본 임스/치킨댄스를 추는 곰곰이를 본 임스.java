import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력받기
        Scanner sc = new Scanner(System.in);

        // 치킨집에 있는 치킨의 개수
        int N = sc.nextInt();

        // 임스의 집에 있는 콜라 개수와 맥주 개수
        int A = sc.nextInt();
        int B = sc.nextInt();

        // 임스가 먹을 수 있는 최대 치킨 개수 계산
        int maxChickenByDrink = (A / 2) + B; // 콜라 2개당 1마리, 맥주 1개당 1마리

        // 치킨집에 있는 치킨 개수와 음료수로 먹을 수 있는 치킨 개수 중 최솟값이 정답
        int maxChicken = Math.min(N, maxChickenByDrink);

        // 결과 출력
        System.out.println(maxChicken);

        // 스캐너 닫기
        sc.close();
    }
}
