import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스의 수

        while (T-- > 0) {
            int N = sc.nextInt(); // 도시의 수
            int M = sc.nextInt(); // 도로의 수
            int K = sc.nextInt(); // 초기 감염 도시의 수

            // 1. 그래프(인접 리스트) 초기화 (1-based 인덱싱)
            ArrayList<Integer>[] adj = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            // 도로 정보(간선) 입력
            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u].add(v);
                adj[v].add(u); // 양방향 도로
            }

            // 감염 여부를 저장할 boolean 배열
            boolean[] hasVirus = new boolean[N + 1];

            // 초기 감염 도시들을 저장할 배열 (나중에 확산을 위해)
            int[] initialCities = new int[K];
            
            // 2. 초기 감염 처리
            for (int i = 0; i < K; i++) {
                int infectedCity = sc.nextInt();
                initialCities[i] = infectedCity;
                hasVirus[infectedCity] = true;
            }

            // 3. 1차 확산 처리
            // 모든 초기 감염 도시에서 동시에 확산이 일어남
            for (int sourceCity : initialCities) {
                // sourceCity와 연결된 모든 인접 도시(neighbor)를 감염시킴
                for (int neighbor : adj[sourceCity]) {
                    hasVirus[neighbor] = true;
                }
            }

            // 4. 최종 감염된 도시 수 계산
            int totalInfected = 0;
            for (int i = 1; i <= N; i++) {
                if (hasVirus[i]) {
                    totalInfected++;
                }
            }

            // 결과 출력
            System.out.println(totalInfected);
        }

        sc.close();
    }
}