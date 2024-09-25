import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.run();
    }
}

class Solver {
    private int N, K, totalLength;
    private String[] words;
    private boolean[] used;
    private int resultCount;

    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        words = new String[N];
        used = new boolean[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
            totalLength += words[i].length();
        }
        K = sc.nextInt();
        sc.close();

        resultCount = 0;
        findCombinations("");
        System.out.println(resultCount);
    }

    private void findCombinations(String current) {
        if (current.length() == totalLength) {
            if (isMagicString(current)) {
                resultCount++;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                findCombinations(current + words[i]);
                used[i] = false;
            }
        }
    }

    private boolean isMagicString(String s) {
        int count = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (isSameString(s, rotateString(s, i))) {
                count++;
            }
        }

        return count == K;
    }

    private String rotateString(String s, int i) {
        return s.substring(i) + s.substring(0, i);
    }

    private boolean isSameString(String s1, String s2) {
        return s1.equals(s2);
    }
}
