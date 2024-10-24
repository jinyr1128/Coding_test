import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int N = scanner.nextInt();
        int[] fruits = new int[N];
        for (int i = 0; i < N; i++) {
            fruits[i] = scanner.nextInt();
        }
        scanner.close();

        // 두 종류 이하의 과일로 가장 긴 부분 탕후루 구하기
        Map<Integer, Integer> countMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int maxLength = 0;

        for (int fruit : fruits) {
            queue.add(fruit);
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);

            // 종류가 2개를 초과할 경우 큐에서 제거
            while (countMap.size() > 2) {
                int removed = queue.poll();
                countMap.put(removed, countMap.get(removed) - 1);
                if (countMap.get(removed) == 0) {
                    countMap.remove(removed);
                }
            }

            // 현재 큐의 크기가 최대 길이인지 확인
            maxLength = Math.max(maxLength, queue.size());
        }

        // 결과 출력
        System.out.println(maxLength);
    }
}
