import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // N의 범위가 100,000이므로 N*N은 int 범위를 넘을 수 있음 -> long 사용
        long N = sc.nextLong();
        long R = sc.nextLong();
        long C = sc.nextLong();
        
        long totalCells = N * N;
        long answer = 0;
        
        // 1. 기본적으로 같은 색깔(패리티)의 칸 개수를 계산
        if (totalCells % 2 == 0) {
            // 전체 칸 수가 짝수면, 흰색/검은색 칸 수는 정확히 반반
            answer = totalCells / 2;
        } else {
            // 전체 칸 수가 홀수면, (1, 1)과 같은 색인 칸이 하나 더 많음
            boolean startEven = (R + C) % 2 == 0;
            // (1,1)의 합은 2(짝수). 
            // 시작점 합이 짝수라면 많은 쪽((Total+1)/2), 홀수라면 적은 쪽(Total/2)
            if (startEven) {
                answer = (totalCells + 1) / 2;
            } else {
                answer = totalCells / 2;
            }
        }
        
        // 2. N=3일 때의 예외 처리
        if (N == 3) {
            if (R == 2 && C == 2) {
                // 시작점이 중앙이면 움직일 수 없음
                answer = 1;
            } else if ((R + C) % 2 == 0) {
                // 시작점이 중앙이 아닌데 합이 짝수라면,
                // 위에서 계산한 answer에는 도달 불가능한 중앙 칸(2,2)이 포함되어 있으므로 1을 뺌
                answer--;
            }
        }
        
        System.out.println(answer);
        
        sc.close();
    }
}