import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 통화의 개수 입력
        int N = sc.nextInt();
        
        // 통화 시간 배열 초기화
        int[] callDurations = new int[N];
        for (int i = 0; i < N; i++) {
            callDurations[i] = sc.nextInt();
        }
        
        // 요금 계산
        int yCost = 0;
        int mCost = 0;
        
        for (int duration : callDurations) {
            yCost += (duration / 30 + 1) * 10; // 영식 요금제
            mCost += (duration / 60 + 1) * 15; // 민식 요금제
        }
        
        // 더 저렴한 요금제를 출력
        if (yCost < mCost) {
            System.out.println("Y " + yCost);
        } else if (mCost < yCost) {
            System.out.println("M " + mCost);
        } else {
            System.out.println("Y M " + yCost);
        }

        sc.close();
    }
}
