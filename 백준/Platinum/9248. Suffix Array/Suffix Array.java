import java.util.*;

public class Main {

    static class Suffix implements Comparable<Suffix> {
        int index, rank, nextRank;

        public Suffix(int index, int rank, int nextRank) {
            this.index = index;
            this.rank = rank;
            this.nextRank = nextRank;
        }

        @Override
        public int compareTo(Suffix other) {
            if (this.rank != other.rank) return Integer.compare(this.rank, other.rank);
            return Integer.compare(this.nextRank, other.nextRank);
        }
    }

    // Suffix Array 생성
    public static int[] getSuffixArray(String s) {
        int n = s.length();
        Suffix[] suffixes = new Suffix[n];
        int[] suffixArray = new int[n];
        int[] rank = new int[n];

        // 초기 랭크 설정
        for (int i = 0; i < n; i++) {
            rank[i] = s.charAt(i) - 'a';
            suffixes[i] = new Suffix(i, rank[i], i + 1 < n ? s.charAt(i + 1) - 'a' : -1);
        }

        Arrays.sort(suffixes);

        for (int length = 2; length < 2 * n; length *= 2) {
            int[] newRank = new int[n];
            int rankCounter = 0;
            newRank[suffixes[0].index] = rankCounter;

            for (int i = 1; i < n; i++) {
                if (suffixes[i].rank != suffixes[i - 1].rank || suffixes[i].nextRank != suffixes[i - 1].nextRank) {
                    rankCounter++;
                }
                newRank[suffixes[i].index] = rankCounter;
            }

            for (int i = 0; i < n; i++) {
                int nextIndex = suffixes[i].index + length / 2;
                suffixes[i] = new Suffix(suffixes[i].index, newRank[suffixes[i].index],
                        nextIndex < n ? newRank[nextIndex] : -1);
            }
            Arrays.sort(suffixes);
        }

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index;
        }
        return suffixArray;
    }

    // LCP Array 생성
    public static int[] getLCPArray(String s, int[] suffixArray) {
        int n = s.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

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

        return lcp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] suffixArray = getSuffixArray(s);
        int[] lcpArray = getLCPArray(s, suffixArray);

        // 출력
        for (int idx : suffixArray) {
            System.out.print((idx + 1) + " ");
        }
        System.out.println();
        System.out.print("x ");
        for (int i = 1; i < lcpArray.length; i++) {
            System.out.print(lcpArray[i] + " ");
        }
    }
}
