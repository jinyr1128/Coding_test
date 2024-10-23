import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배열의 길이 입력
        int n = sc.nextInt();
        int[] A = new int[n];

        // 배열 A의 원소들 입력
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // 조작의 개수 입력
        int m = sc.nextInt();
        // 조작 리스트: 각 조작은 (l, r, c)로 이루어짐
        List<Operation> operations = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int l = sc.nextInt() - 1; // 인덱스를 0부터 시작하도록 변환
            int r = sc.nextInt() - 1;
            int c = sc.nextInt();
            operations.add(new Operation(l, r, c));
        }

        // 우선순위 큐를 이용하여 비용을 최소화하기 위한 다익스트라 알고리즘 사용
        PriorityQueue<State> pq = new PriorityQueue<>();
        Map<List<Integer>, Integer> dist = new HashMap<>();

        List<Integer> initialState = toList(A);
        pq.add(new State(initialState, 0));
        dist.put(initialState, 0);

        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            List<Integer> currentArr = currentState.arr;
            int currentCost = currentState.cost;

            // 현재 상태가 이미 최소 비용으로 처리된 경우 무시
            if (dist.get(currentArr) < currentCost) {
                continue;
            }

            // 모든 가능한 조작을 시도
            for (Operation op : operations) {
                List<Integer> newArr = new ArrayList<>(currentArr);
                // 주어진 범위의 두 값을 교환
                Collections.swap(newArr, op.l, op.r);

                int newCost = currentCost + op.cost;
                if (!dist.containsKey(newArr) || dist.get(newArr) > newCost) {
                    dist.put(newArr, newCost);
                    pq.add(new State(newArr, newCost));
                }
            }
        }

        // 최종적으로 배열이 정렬된 상태가 되었을 때의 최소 비용을 찾음
        List<Integer> sortedArr = toList(A);
        Collections.sort(sortedArr);

        // 정렬된 배열이 dist에 존재하면 최소 비용 출력, 그렇지 않으면 -1 출력
        if (dist.containsKey(sortedArr)) {
            System.out.println(dist.get(sortedArr));
        } else {
            System.out.println(-1);
        }
    }

    // 배열을 List<Integer>로 변환하는 함수
    private static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }

    // 상태를 나타내는 클래스 (배열과 그 배열을 만들기까지의 비용)
    static class State implements Comparable<State> {
        List<Integer> arr;
        int cost;

        State(List<Integer> arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    // 조작을 나타내는 클래스 (범위 l, r와 그에 따른 비용 c)
    static class Operation {
        int l, r, cost;

        Operation(int l, int r, int cost) {
            this.l = l;
            this.r = r;
            this.cost = cost;
        }
    }
}
