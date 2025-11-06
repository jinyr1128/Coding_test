import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 각 숫자의 등장 여부를 기록 (0 ~ 9999)
        boolean[] seen = new boolean[10000];

        while (true) {
            int a0 = sc.nextInt();
            
            // 0이 입력되면 프로그램 종료
            if (a0 == 0) {
                break;
            }

            // 새 테스트 케이스를 위해 배열과 카운트 초기화
            Arrays.fill(seen, false);
            int count = 0;
            int currentNum = a0;

            // 이미 등장한 숫자가 나올 때까지 반복
            while (!seen[currentNum]) {
                // 현재 숫자를 "본 것"으로 표시하고 카운트 증가
                seen[currentNum] = true;
                count++;

                // 1. 제곱 (int 범위를 넘을 수 있으므로 long 사용)
                long squared = (long) currentNum * currentNum;

                // 2. 8자리가 되도록 앞에 0을 붙임 (n=4, 2n=8)
                String formatted = String.format("%08d", squared);

                // 3. 가운데 4자리를 추출 (인덱스 2부터 6 직전까지)
                String middle = formatted.substring(2, 6);

                // 4. 다음 숫자로 갱신
                currentNum = Integer.parseInt(middle);
            }

            // 총 횟수 출력
            System.out.println(count);
        }

        sc.close();
    }
}