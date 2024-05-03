import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 테스트 케이스의 수
        while (T-- > 0) {
            int n = scanner.nextInt(); // 의상의 수
            Map<String, Integer> clothesMap = new HashMap<>();
            
            for (int i = 0; i < n; i++) {
                String name = scanner.next(); // 의상 이름 (사용되지 않음)
                String type = scanner.next(); // 의상 종류
                clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
            }
            
            long combinations = 1;
            for (int count : clothesMap.values()) {
                combinations *= (count + 1); // 각 종류별 선택지 수 (안 입는 경우 포함)
            }
            
            System.out.println(combinations - 1); // 전체에서 아무것도 안 입는 경우를 빼줌
        }
        scanner.close();
    }
}
