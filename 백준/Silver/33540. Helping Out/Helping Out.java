import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 참가자 수
        sc.nextLine(); // 개행 문자 제거

        Map<String, Integer> scoreMap = new TreeMap<>(); // 자동 정렬되는 Map

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();

            // 기존 점수에 추가
            scoreMap.put(name, scoreMap.getOrDefault(name, 0) + score);
        }

        // 결과 출력 (TreeMap 사용 시 자동 정렬)
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        sc.close();
    }
}
