import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());  // 게이트 수
        int P = Integer.parseInt(br.readLine());  // 비행기 수
        int[] planes = new int[P];

        for (int i = 0; i < P; i++) {
            planes[i] = Integer.parseInt(br.readLine());
        }

        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;
        for (int plane : planes) {
            int availableGate = find(plane);  // 현재 비행기에 대해 도킹 가능한 가장 큰 번호의 게이트 찾기
            if (availableGate == 0) {  // 도킹할 수 있는 게이트가 없으면 중단
                break;
            }
            union(availableGate, availableGate - 1);  // 사용된 게이트 처리
            count++;  // 도킹한 비행기 수 증가
        }

        System.out.println(count);  // 결과 출력
    }

    // 유니온 파인드 알고리즘의 'find' 함수
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 유니온 파인드 알고리즘의 'union' 함수
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[x] = y;
        }
    }
}
