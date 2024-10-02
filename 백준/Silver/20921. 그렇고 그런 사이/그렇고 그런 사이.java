import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 사람의 수
        int K = sc.nextInt(); // 그렇고 그런 사이의 수
        
        int[] result = new int[N];
        
        // 초기 상태를 N부터 1까지 내림차순으로 설정 (최대 그렇고 그런 사이)
        for (int i = 0; i < N; i++) {
            result[i] = N - i;
        }
        
        int currentK = N * (N - 1) / 2; // 현재 최대 그렇고 그런 사이 개수
        
        // 현재 K보다 많으면, swap을 통해서 K에 맞게 줄여나간다.
        for (int i = 0; i < N && currentK > K; i++) {
            for (int j = i + 1; j < N && currentK > K; j++) {
                // swap result[i] and result[j] to reduce the number of "그렇고 그런 사이"
                int temp = result[i];
                result[i] = result[j];
                result[j] = temp;
                currentK--;
                
                // 만약 currentK가 K에 도달하면 종료
                if (currentK == K) {
                    break;
                }
            }
        }
        
        // 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
