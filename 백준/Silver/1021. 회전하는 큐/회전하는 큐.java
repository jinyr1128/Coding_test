import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 큐의 크기 N과 뽑아내려는 원소의 개수 M
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        // 뽑아내려는 원소들의 위치
        int[] positions = new int[M];
        for (int i = 0; i < M; i++) {
            positions[i] = sc.nextInt();
        }
        
        // 양방향 순환 큐를 Deque로 표현
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }
        
        int totalOperations = 0;
        
        // 각 뽑아내려는 위치에 대해 처리
        for (int target : positions) {
            int index = 0;
            for (int num : deque) {
                if (num == target) {
                    break;
                }
                index++;
            }
            
            // 왼쪽으로 이동하는 경우와 오른쪽으로 이동하는 경우 중 최소 연산을 선택
            int leftMoves = index;
            int rightMoves = deque.size() - index;
            
            // 더 작은 연산을 선택
            totalOperations += Math.min(leftMoves, rightMoves);
            
            // 해당 위치까지 왼쪽으로 이동할지 오른쪽으로 이동할지 결정
            if (leftMoves <= rightMoves) {
                // 왼쪽으로 이동
                for (int i = 0; i < leftMoves; i++) {
                    deque.addLast(deque.pollFirst());
                }
            } else {
                // 오른쪽으로 이동
                for (int i = 0; i < rightMoves; i++) {
                    deque.addFirst(deque.pollLast());
                }
            }
            
            // 뽑아낸다.
            deque.pollFirst();
        }
        
        // 정답 출력
        System.out.println(totalOperations);
    }
}
