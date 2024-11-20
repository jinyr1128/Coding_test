import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> heap = new ArrayList<>(); // 힙 데이터를 저장할 리스트

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                heap.add(value); // 값을 추가
                heapifyUp(heap); // 힙 정렬 유지
            } else if (command.equals("D")) {
                if (heap.isEmpty()) continue;

                if (value == 1) { // 최댓값 삭제
                    int maxIndex = findMaxIndex(heap);
                    swap(heap, maxIndex, heap.size() - 1);
                    heap.remove(heap.size() - 1);
                } else if (value == -1) { // 최솟값 삭제
                    swap(heap, 0, heap.size() - 1);
                    heap.remove(heap.size() - 1);
                }
                heapifyDown(heap, 0); // 힙 정렬 재구성
            }
        }

        if (heap.isEmpty()) {
            return new int[]{0, 0};
        }

        int max = findMax(heap);
        int min = heap.get(0); // 최소값은 루트
        return new int[]{max, min};
    }

    private void heapifyUp(List<Integer> heap) {
        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parent)) {
                break;
            }
            swap(heap, index, parent);
            index = parent;
        }
    }

    private void heapifyDown(List<Integer> heap, int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest == index) {
                break;
            }
            swap(heap, index, smallest);
            index = smallest;
        }
    }

    private int findMax(List<Integer> heap) {
        int max = Integer.MIN_VALUE;
        for (int value : heap) {
            max = Math.max(max, value);
        }
        return max;
    }

    private int findMaxIndex(List<Integer> heap) {
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) > max) {
                max = heap.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void swap(List<Integer> heap, int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
