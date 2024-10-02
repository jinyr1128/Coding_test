import java.util.*;

class Main {

    static class Road implements Comparable<Road> {
        long cost;
        int city;
        int pavingCount;

        Road(long cost, int city, int pavingCount) {
            this.cost = cost;
            this.city = city;
            this.pavingCount = pavingCount;
        }

        @Override
        public int compareTo(Road other) {
            return Long.compare(this.cost, other.cost); // 최소값이 우선순위가 높은 값이 되도록 설정
        }
    }

    static int n, m, k;
    static long[][] cost; // 도시별, 포장 횟수별 최소 비용 저장
    static ArrayList<ArrayList<int[]>> roads = new ArrayList<>(); // 도시와 도로의 연결 정보

    static long dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        cost = new long[n + 1][k + 1];

        // 비용 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], Long.MAX_VALUE);
        }

        // 시작점 세팅
        pq.add(new Road(0, 1, 0)); // 1번 도시에서 시작, 포장 횟수 0
        cost[1][0] = 0;

        while (!pq.isEmpty()) {
            Road current = pq.poll();
            long currentCost = current.cost;
            int currentCity = current.city;
            int pavingCount = current.pavingCount;

            // 이미 더 작은 비용이 저장된 경우 무시
            if (currentCost > cost[currentCity][pavingCount]) {
                continue;
            }

            // 현재 도시에서 연결된 다른 도시 탐색
            for (int[] road : roads.get(currentCity)) {
                int nextCity = road[0];
                long roadCost = road[1];

                // 포장하지 않은 경우
                if (cost[nextCity][pavingCount] > currentCost + roadCost) {
                    cost[nextCity][pavingCount] = currentCost + roadCost;
                    pq.add(new Road(cost[nextCity][pavingCount], nextCity, pavingCount));
                }

                // 포장하는 경우
                if (pavingCount < k && cost[nextCity][pavingCount + 1] > currentCost) {
                    cost[nextCity][pavingCount + 1] = currentCost;
                    pq.add(new Road(cost[nextCity][pavingCount + 1], nextCity, pavingCount + 1));
                }
            }
        }

        // 최소 비용 계산
        long minCost = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            minCost = Math.min(minCost, cost[n][i]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 도시 수
        m = sc.nextInt(); // 도로 수
        k = sc.nextInt(); // 포장할 수 있는 도로 수

        // 도로 정보 초기화
        for (int i = 0; i <= n; i++) {
            roads.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(); // 도시 1
            int b = sc.nextInt(); // 도시 2
            int cost = sc.nextInt(); // 두 도시 간의 도로 비용

            roads.get(a).add(new int[]{b, cost});
            roads.get(b).add(new int[]{a, cost});
        }

        // 다익스트라 알고리즘 실행
        System.out.println(dijkstra());
    }
}
