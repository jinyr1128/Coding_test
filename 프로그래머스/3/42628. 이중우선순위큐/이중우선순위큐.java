import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 최소 힙과 최대 힙 선언
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        HashSet<Integer> valid = new HashSet<>(); // 유효한 값 관리

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                minHeap.add(value);
                maxHeap.add(value);
                valid.add(value);
            } else if (command.equals("D")) {
                if (valid.isEmpty()) continue; // 비어있으면 무시

                if (value == 1) { // 최댓값 삭제
                    while (!maxHeap.isEmpty() && !valid.contains(maxHeap.peek())) {
                        maxHeap.poll(); // 유효하지 않은 값 제거
                    }
                    if (!maxHeap.isEmpty()) {
                        int toRemove = maxHeap.poll();
                        valid.remove(toRemove);
                    }
                } else if (value == -1) { // 최솟값 삭제
                    while (!minHeap.isEmpty() && !valid.contains(minHeap.peek())) {
                        minHeap.poll(); // 유효하지 않은 값 제거
                    }
                    if (!minHeap.isEmpty()) {
                        int toRemove = minHeap.poll();
                        valid.remove(toRemove);
                    }
                }
            }
        }

        // 힙 정리: 유효하지 않은 값 제거
        while (!minHeap.isEmpty() && !valid.contains(minHeap.peek())) {
            minHeap.poll();
        }
        while (!maxHeap.isEmpty() && !valid.contains(maxHeap.peek())) {
            maxHeap.poll();
        }

        // 결과 계산
        if (valid.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.peek(), minHeap.peek()};
        }
    }
}
