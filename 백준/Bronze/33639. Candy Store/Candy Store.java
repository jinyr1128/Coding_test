import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. N(고객 수)과 Q(질문 수) 입력
        if (sc.hasNextInt()) {
            int N = sc.nextInt();
            int Q = sc.nextInt();

            // 고객의 전체 이름과 이니셜을 저장할 배열
            String[] fullNames = new String[N];
            String[] initials = new String[N];

            // 2. 고객 이름 입력 및 이니셜 추출
            for (int i = 0; i < N; i++) {
                String firstName = sc.next();
                String lastName = sc.next();

                // 전체 이름 저장 (출력용)
                fullNames[i] = firstName + " " + lastName;
                
                // 이니셜 생성 (비교용) -> 예: John Doe -> "JD"
                initials[i] = "" + firstName.charAt(0) + lastName.charAt(0);
            }

            // 3. 질문 처리
            for (int i = 0; i < Q; i++) {
                String query = sc.next(); // 찾으려는 이니셜 입력
                
                int count = 0;
                String matchedName = "";

                // 모든 고객을 확인하며 이니셜 비교
                for (int j = 0; j < N; j++) {
                    if (initials[j].equals(query)) {
                        count++;
                        matchedName = fullNames[j]; // 일치하는 이름 저장
                    }
                }

                // 결과 출력
                if (count == 0) {
                    System.out.println("nobody");
                } else if (count == 1) {
                    System.out.println(matchedName);
                } else {
                    System.out.println("ambiguous");
                }
            }
        }
        
        sc.close();
    }
}