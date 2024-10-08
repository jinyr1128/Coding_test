import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine(); // 개행 문자 제거

        Map<Character, Integer> initialCount = new HashMap<>();

        for (int i = 0; i < N; i++) {
            char initial = sc.nextLine().charAt(0);
            initialCount.put(initial, initialCount.getOrDefault(initial, 0) + 1);
        }

        TreeSet<Character> result = new TreeSet<>();

        for (Map.Entry<Character, Integer> entry : initialCount.entrySet()) {
            if (entry.getValue() >= 5) {
                result.add(entry.getKey());
            }
        }

        if (result.isEmpty()) {
            System.out.println("PREDAJA");
        } else {
            for (char c : result) {
                System.out.print(c);
            }
        }
        sc.close();
    }
}
