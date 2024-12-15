import java.util.*;

public class Main {
    static String s;
    static int[] suffixArray, rank, lcp, pi;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        n = s.length();

        buildSuffixArray();
        buildLCPArray();
        buildPiArray();

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        List<int[]> results = new ArrayList<>();

        results.add(new int[]{n, 1});
        int x = pi[n - 1];
        while (x > 0) {
            int index = rank[n - x];
            results.add(new int[]{x, calculateCount(index, dp)});
            x = pi[x - 1];
        }

        System.out.println(results.size());
        for (int i = results.size() - 1; i >= 0; i--) {
            System.out.println(results.get(i)[0] + " " + results.get(i)[1]);
        }
    }

    static void buildSuffixArray() {
        suffixArray = new int[n];
        rank = new int[n];
        int[] tempRank = new int[n];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
            rank[i] = s.charAt(i) - 'A';
            order[i] = i;
        }

        for (int length = 1; length < n; length *= 2) {
            int finalLength = length;
            Arrays.sort(order, (a, b) -> {
                if (rank[a] != rank[b]) return Integer.compare(rank[a], rank[b]);
                int rankA = (a + finalLength < n) ? rank[a + finalLength] : -1;
                int rankB = (b + finalLength < n) ? rank[b + finalLength] : -1;
                return Integer.compare(rankA, rankB);
            });

            for (int i = 0; i < n; i++) {
                suffixArray[i] = order[i];
            }

            tempRank[suffixArray[0]] = 0;
            for (int i = 1; i < n; i++) {
                boolean sameRank = rank[suffixArray[i]] == rank[suffixArray[i - 1]]
                        && ((suffixArray[i] + length < n ? rank[suffixArray[i] + length] : -1)
                        == (suffixArray[i - 1] + length < n ? rank[suffixArray[i - 1] + length] : -1));
                tempRank[suffixArray[i]] = tempRank[suffixArray[i - 1]] + (sameRank ? 0 : 1);
            }
            System.arraycopy(tempRank, 0, rank, 0, n);
        }
    }

    static void buildLCPArray() {
        lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = suffixArray[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) {
                    h++;
                }
                lcp[rank[i]] = h;
                if (h > 0) h--;
            }
        }
    }

    static void buildPiArray() {
        pi = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
    }

    static int calculateCount(int x, int[] dp) {
        if (dp[x] != -1) return dp[x];
        dp[x] = 1;
        for (int i = x + 1; i < n; i++) {
            if (lcp[i] >= n - suffixArray[x]) {
                dp[x] += calculateCount(i, dp);
                i += calculateCount(i, dp) - 1;
            } else {
                break;
            }
        }
        return dp[x];
    }
}
