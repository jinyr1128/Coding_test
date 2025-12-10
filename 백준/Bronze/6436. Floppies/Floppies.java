import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;

        while (sc.hasNext()) {
            // 입력 범위가 10억이므로 long 사용 권장 (계산 과정 중 안전을 위해)
            long s = sc.nextLong();

            // s = 0 이면 종료
            if (s == 0) {
                break;
            }

            // 1단계: 압축 (크기 절반, 반올림)
            long s1 = Math.round(s / 2.0);

            // 2단계: uuencode (크기 50% 증가, 반올림)
            long s2 = Math.round(s1 * 1.5);

            // 3단계: 플로피 디스크 개수 계산
            // 한 덩어리의 크기 = 30,000 라인 * 62 바이트 = 1,860,000 바이트
            long chunkSize = 1860000;
            
            // 올림 연산: (분자 + 분모 - 1) / 분모
            long floppies = (s2 + chunkSize - 1) / chunkSize;
            
            // 0바이트인 경우에도 최소 0장이지만, 문제 조건상 s >= 0이고
            // 0 입력시 종료되므로 s >= 1 상황만 고려하면 최소 1장이 됨.
            if (floppies == 0) floppies = 0; 

            System.out.println("File #" + caseNum);
            System.out.println("John needs " + floppies + " floppies.");
            System.out.println(); // 각 테스트 케이스 뒤에 빈 줄 출력

            caseNum++;
        }
        
        sc.close();
    }
}