import java.util.*;

public class Main {
    private static int T;
    private static Map<String, Integer> dp = new HashMap<>(); // Memoization for dp

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            result.append(j(x, y)).append("\n");
        }
        sc.close();
        System.out.print(result);
    }

    private static int j(int l, int r) {
        if (l == 0 && r == 0) {
            return 0;
        }

        String key = l + "," + r;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int res = 0;
        for (int i = 1; i <= 29; i++) { // 2^30 > 10^9
            int jumpStart = (1 << i) - 1;
            int jumpEnd = (1 << (i + 1)) - 2;

            int jl = Math.max(l, jumpStart);
            int jr = Math.min(r, jumpEnd);

            if (jl <= jr) {
                int nl = jl - jumpStart;
                int nr = jr - jumpStart;
                res = Math.max(res, j(nl, nr) + i);
            }
        }

        dp.put(key, res);
        return res;
    }
}
