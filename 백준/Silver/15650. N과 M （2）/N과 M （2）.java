import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 숫자들을 저장할 리스트를 생성
        List<Integer> combination = new ArrayList<>();
        // 백트래킹 시작
        recursion(N, M, combination, 1);

        scanner.close();
    }

    // 백트래킹을 수행하는 재귀 메소드
    public static void recursion(int N, int M, List<Integer> combination, int start) {
        // 수열의 길이가 M일 때 결과 출력
        if (combination.size() == M) {
            for (int number : combination) {
                System.out.print(number + " ");
            }
            System.out.println();
            return;
        }

        // start부터 N까지의 숫자를 선택하는 반복문
        for (int i = start; i <= N; i++) {
            combination.add(i);
            recursion(N, M, combination, i + 1);
            combination.remove(combination.size() - 1); // 마지막 요소 제거 (백트래킹)
        }
    }
}
