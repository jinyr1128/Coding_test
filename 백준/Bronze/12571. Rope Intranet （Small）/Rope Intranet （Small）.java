import java.util.Scanner;

public class Main {
    
    // 전선 정보를 저장할 클래스
    static class Wire {
        int a; // 왼쪽 건물 높이
        int b; // 오른쪽 건물 높이
        
        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스 개수 T 입력
        int T = sc.nextInt();
        
        for (int i = 1; i <= T; i++) {
            // 전선의 개수 N 입력
            int N = sc.nextInt();
            
            Wire[] wires = new Wire[N];
            
            // 전선 정보 입력
            for (int j = 0; j < N; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                wires[j] = new Wire(a, b);
            }
            
            int intersectionPoints = 0;
            
            // 모든 전선 쌍을 비교하여 교차 여부 확인
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    // 두 전선의 왼쪽 높이 차이와 오른쪽 높이 차이의 부호가 다르면 교차함
                    // 즉, (A1 > A2 이면서 B1 < B2) 또는 (A1 < A2 이면서 B1 > B2) 인 경우
                    if ((wires[j].a - wires[k].a) * (wires[j].b - wires[k].b) < 0) {
                        intersectionPoints++;
                    }
                }
            }
            
            // 결과 출력
            System.out.println("Case #" + i + ": " + intersectionPoints);
        }
        
        sc.close();
    }
}