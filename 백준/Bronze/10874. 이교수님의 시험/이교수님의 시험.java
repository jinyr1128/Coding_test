import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 학생 수 입력
        int N = sc.nextInt();

        // 2. 각 학생별 채점 진행
        for (int studentId = 1; studentId <= N; studentId++) {
            boolean isPerfect = true; // 만점 여부 플래그

            // 10문제의 답을 입력받으며 정답 확인
            for (int j = 0; j < 10; j++) {
                int answer = sc.nextInt();
                
                // j번째 문제(0-based index)의 정답 계산: (j % 5) + 1
                // 문제 설명의 ((j+1)-1) % 5 + 1 과 동일
                int correctAnswer = (j % 5) + 1;

                if (answer != correctAnswer) {
                    isPerfect = false;
                    // 여기서 break 하지 않는 이유: 
                    // 한 줄에 있는 나머지 숫자들을 모두 읽어서 소비(consume)해야 
                    // 다음 학생의 입력 처리에 문제가 생기지 않음.
                }
            }

            // 만점자인 경우 학생 번호 출력
            if (isPerfect) {
                System.out.println(studentId);
            }
        }
        
        sc.close();
    }
}