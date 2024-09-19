import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N: 끊어진 기타줄의 개수, M: 기타줄 브랜드의 수
        int N = sc.nextInt();
        int M = sc.nextInt();

        int minPackage = Integer.MAX_VALUE;  // 가장 저렴한 패키지 가격
        int minSingle = Integer.MAX_VALUE;   // 가장 저렴한 낱개 가격

        // 각 브랜드의 패키지 가격과 낱개 가격을 입력받음
        for (int i = 0; i < M; i++) {
            int packagePrice = sc.nextInt();
            int singlePrice = sc.nextInt();
            // 가장 저렴한 패키지와 낱개 가격을 찾음
            minPackage = Math.min(minPackage, packagePrice);
            minSingle = Math.min(minSingle, singlePrice);
        }

        // 패키지와 낱개를 조합하여 최소 가격을 계산
        // 1. 패키지로만 사는 경우
        int totalCost = (N / 6) * minPackage + (N % 6) * minSingle;
        // 2. 남는 줄도 패키지로 사는 경우
        totalCost = Math.min(totalCost, ((N / 6) + 1) * minPackage);
        // 3. 낱개로만 사는 경우
        totalCost = Math.min(totalCost, N * minSingle);

        // 결과 출력
        System.out.println(totalCost);

        sc.close();
    }
}
