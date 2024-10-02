import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] wScores = new int[10];  // W 대학 참가자의 점수를 저장하는 배열
        int[] kScores = new int[10];  // K 대학 참가자의 점수를 저장하는 배열
        
        // W 대학의 점수를 입력받음
        for (int i = 0; i < 10; i++) {
            wScores[i] = sc.nextInt();
        }
        
        // K 대학의 점수를 입력받음
        for (int i = 0; i < 10; i++) {
            kScores[i] = sc.nextInt();
        }
        
        // W 대학과 K 대학의 점수를 내림차순으로 정렬하여 상위 3개의 점수를 선택
        Arrays.sort(wScores);
        Arrays.sort(kScores);
        
        // 상위 3명의 점수 합계 계산
        int wSum = wScores[9] + wScores[8] + wScores[7];  // W 대학 상위 3명 점수
        int kSum = kScores[9] + kScores[8] + kScores[7];  // K 대학 상위 3명 점수
        
        // 결과 출력
        System.out.println(wSum + " " + kSum);
        
        sc.close();
    }
}
