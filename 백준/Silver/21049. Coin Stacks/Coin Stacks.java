import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

    // 각 스택의 (동전 수, 원래 인덱스)를 저장하는 내부 클래스
    // Comparable을 구현하여 정렬할 수 있게 함
    static class Stack implements Comparable<Stack> {
        int coins;
        int originalIndex;

        Stack(int coins, int originalIndex) {
            this.coins = coins;
            this.originalIndex = originalIndex;
        }

        // C++의 greater<>와 같이, 동전 수(coins) 기준 내림차순 정렬
        @Override
        public int compareTo(Stack other) {
            // other.coins가 this.coins보다 크면 양수 반환 (내림차순)
            return Integer.compare(other.coins, this.coins);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // n개의 Stack 객체를 담을 배열
        Stack[] stacks = new Stack[n];
        for (int i = 0; i < n; i++) {
            int coins = sc.nextInt();
            stacks[i] = new Stack(coins, i + 1); // 1-based index
        }

        // 1. 내림차순으로 정렬
        Arrays.sort(stacks);

        // 2. 정답(움직임)을 저장할 리스트
        List<String> ans = new ArrayList<>();

        // 3. 두 번째로 큰 스택에 동전이 0개가 될 때까지 반복
        while (stacks[1].coins > 0) {
            // C++: ans.push_back({a[0].second, a[1].second});
            // 가장 큰 두 스택의 인덱스를 정답에 추가
            ans.add(stacks[0].originalIndex + " " + stacks[1].originalIndex);

            // C++: a[0].first--; a[1].first--;
            // 두 스택에서 동전 1개씩 제거
            stacks[0].coins--;
            stacks[1].coins--;

            // C++: sort(a.begin(), a.end(), greater<pair<int, int>>());
            // 동전 수가 바뀌었으므로 다시 정렬
            Arrays.sort(stacks);
        }

        // 4. C++: if (a[0].first) { ... }
        // 반복문 종료 후, 가장 큰 스택에도 동전이 남았는지 확인
        if (stacks[0].coins > 0) {
            // 가장 큰 스택에 동전이 남았다면, 짝을 맞출 수 없으므로 실패
            System.out.println("no");
        } else {
            // 모든 스택이 비었으므로 성공
            System.out.println("yes");
            for (String move : ans) {
                System.out.println(move);
            }
        }
        sc.close();
    }
}