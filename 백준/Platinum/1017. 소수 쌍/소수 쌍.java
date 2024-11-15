import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime = new boolean[2001];
    static int[] arr;
    static int[] match;
    static boolean[] visited;
    static int currentPair;
    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sieveOfEratosthenes();
        
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = arr[0];
        
        // 그룹을 나눔: 시작 값이 홀수인지 짝수인지에 따라 left와 right 그룹을 나눔
        for (int value : arr) {
            if (start % 2 == 0) {
                if (value % 2 == 0) left.add(value);
                else right.add(value);
            } else {
                if (value % 2 == 1) left.add(value);
                else right.add(value);
            }
        }
        
        if (left.size() != right.size()) {
            System.out.println(-1);
            return;
        }

        ArrayList<Integer> results = new ArrayList<>();
        
        for (int r : right) {
            if (isPrime[start + r]) {
                match = new int[right.size()];
                Arrays.fill(match, -1);
                currentPair = right.indexOf(r);
                
                if (canMatchAllPairs()) {
                    results.add(r);
                }
            }
        }

        if (results.isEmpty()) {
            System.out.println(-1);
        } else {
            results.stream().sorted().forEach(x -> System.out.print(x + " "));
        }
    }

    // 에라토스테네스의 체를 사용하여 소수 판별
    static void sieveOfEratosthenes() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    // DFS 기반의 이분 매칭
    static boolean dfs(int node) {
        if (visited[node]) return false;
        visited[node] = true;

        for (int i = 0; i < right.size(); i++) {
            if (i != currentPair && isPrime[left.get(node) + right.get(i)]) {
                if (match[i] == -1 || dfs(match[i])) {
                    match[i] = node;
                    return true;
                }
            }
        }
        return false;
    }

    // 모든 쌍을 찾을 수 있는지 확인
    static boolean canMatchAllPairs() {
        int matchedCount = 1;
        match[currentPair] = 0;

        for (int i = 1; i < left.size(); i++) {
            visited = new boolean[right.size()];
            if (dfs(i)) matchedCount++;
        }

        return matchedCount == left.size();
    }
}
