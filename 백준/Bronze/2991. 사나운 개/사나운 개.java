import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int A = scanner.nextInt(); // 첫 번째 개의 공격 시간
        int B = scanner.nextInt(); // 첫 번째 개의 쉬는 시간
        int C = scanner.nextInt(); // 두 번째 개의 공격 시간
        int D = scanner.nextInt(); // 두 번째 개의 쉬는 시간

        int P = scanner.nextInt(); // 우체부 도착 시간
        int M = scanner.nextInt(); // 우유배달원 도착 시간
        int N = scanner.nextInt(); // 신문배달원 도착 시간
        
        scanner.close();
        
        int[] times = {P, M, N};
        
        for (int time : times) {
            int attackedBy = 0;
            
            // 첫 번째 개의 행동 패턴 확인
            int cycleA = A + B;
            int positionInCycleA = time % cycleA;
            if (positionInCycleA > 0 && positionInCycleA <= A) {
                attackedBy++;
            }
            
            // 두 번째 개의 행동 패턴 확인
            int cycleC = C + D;
            int positionInCycleC = time % cycleC;
            if (positionInCycleC > 0 && positionInCycleC <= C) {
                attackedBy++;
            }
            
            System.out.println(attackedBy);
        }
    }
}
