import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 줄 입력: A팀 선수 수, B팀 선수 수, 총 골 수
        int numPlayersA = sc.nextInt();
        int numPlayersB = sc.nextInt();
        int numGoals = sc.nextInt();

        // A팀 선수 명단을 저장할 HashSet 생성
        // HashSet은 특정 원소의 포함 여부를 매우 빠르게 확인할 수 있습니다.
        Set<String> teamA = new HashSet<>();
        for (int i = 0; i < numPlayersA; i++) {
            teamA.add(sc.next());
        }

        // B팀 선수 명단은 굳이 저장할 필요가 없습니다.
        // 득점자가 A팀 소속이 아니면 자동으로 B팀 소속이기 때문입니다.
        for (int i = 0; i < numPlayersB; i++) {
            sc.next(); // 입력만 받고 넘어갑니다.
        }

        int scoreA = 0; // A팀 점수
        int scoreB = 0; // B팀 점수

        // 득점자 명단을 확인하며 점수 계산
        for (int i = 0; i < numGoals; i++) {
            String scorer = sc.next();
            if (teamA.contains(scorer)) {
                scoreA++; // 득점자가 A팀에 있으면 A팀 점수 증가
            } else {
                scoreB++; // 그렇지 않으면 B팀 점수 증가
            }
        }
        
        sc.close();

        // 최종 점수 비교 후 결과 출력
        if (scoreA > scoreB) {
            System.out.println("A");
        } else if (scoreB > scoreA) {
            System.out.println("B");
        } else {
            System.out.println("TIE");
        }
    }
}