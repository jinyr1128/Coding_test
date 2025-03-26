import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int caseNumber = 1;
        
        while(true) {
            int n0 = sc.nextInt();
            
            // 입력으로 0이 주어지면 종료
            if(n0 == 0) {
                break;
            }
            
            // 1) n1 = 3 * n0
            int n1 = 3 * n0;
            
            // 2) n1의 짝/홀수에 따라 n2 계산
            int n2;
            boolean isEven;
            
            if(n1 % 2 == 0) {
                isEven = true;        // 짝수
                n2 = n1 / 2;
            } else {
                isEven = false;       // 홀수
                n2 = (n1 + 1) / 2;
            }
            
            // 3) n3 = 3 * n2
            int n3 = 3 * n2;
            
            // 4) n4 = n3 / 9 (정수 나눗셈)
            int n4 = n3 / 9;
            
            // 결과 출력
            // 형식: "<케이스번호>. <'even'/'odd'> <n4>"
            System.out.printf("%d. %s %d%n",
                    caseNumber++,
                    isEven ? "even" : "odd",
                    n4);
        }
        
        sc.close();
    }
}
