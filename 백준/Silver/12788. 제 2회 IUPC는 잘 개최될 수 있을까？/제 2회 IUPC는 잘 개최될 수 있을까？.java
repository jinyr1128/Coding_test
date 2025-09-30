import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // CTP 회원 수 N 입력
        int N = sc.nextInt();

        // 팀의 수 M과 팀원 수 K 입력
        int M = sc.nextInt();
        int K = sc.nextInt();

        // 필요한 총 펜의 개수 계산
        int totalPensNeeded = M * K;

        // 각 회원이 가진 펜의 수를 저장할 배열
        // Collections.reverseOrder()를 사용하기 위해 int 대신 Integer 사용
        Integer[] penCounts = new Integer[N];
        for (int i = 0; i < N; i++) {
            penCounts[i] = sc.nextInt();
        }
        sc.close();

        // 펜의 개수를 내림차순(많은 순서)으로 정렬
        Arrays.sort(penCounts, Collections.reverseOrder());

        int collectedPens = 0;       // 현재까지 모은 펜의 총개수
        int membersCount = 0;        // 펜을 빌린 회원의 수
        boolean isPossible = false;  // 펜을 모두 모으는 것이 가능한지 여부

        // 펜을 가장 많이 가진 회원부터 순서대로 확인
        for (int i = 0; i < N; i++) {
            collectedPens += penCounts[i];
            membersCount++;

            // 필요한 펜의 개수 이상을 모았다면
            if (collectedPens >= totalPensNeeded) {
                isPossible = true;
                break; // 더 이상 빌릴 필요가 없으므로 반복 중단
            }
        }

        // 결과 출력
        if (isPossible) {
            System.out.println(membersCount);
        } else {
            System.out.println("STRESS");
        }
    }
}