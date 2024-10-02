import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int N = sc.nextInt();  // 참가자의 수
        int[] shirtSizes = new int[6];  // S, M, L, XL, XXL, XXXL
        for (int i = 0; i < 6; i++) {
            shirtSizes[i] = sc.nextInt();
        }
        int T = sc.nextInt();  // 티셔츠 묶음의 수
        int P = sc.nextInt();  // 펜 묶음의 수

        // 티셔츠 묶음 계산
        int totalShirtBundles = 0;
        for (int i = 0; i < 6; i++) {
            totalShirtBundles += (shirtSizes[i] + T - 1) / T;  // 나누어 떨어지지 않으면 한 묶음 더 필요
        }

        // 펜 묶음 계산
        int penBundles = N / P;  // 묶음으로 주문할 수 있는 펜의 개수
        int remainingPens = N % P;  // 묶음으로 다 주문하고 남은 펜의 개수

        // 결과 출력
        System.out.println(totalShirtBundles);
        System.out.println(penBundles + " " + remainingPens);
        
        sc.close();
    }
}
