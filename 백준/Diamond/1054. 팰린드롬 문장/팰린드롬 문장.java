import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int N;
    static String[] words;
    static Map<String, Long>[] dpL;
    static Map<String, Long>[] dpR;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        words = new String[N];
        
        for (int i = 0; i < N; i++) {
            words[i] = scanner.next();
        }
        
        dpL = new HashMap[1 << N];
        dpR = new HashMap[1 << N];
        for (int i = 0; i < (1 << N); i++) {
            dpL[i] = new HashMap<>();
            dpR[i] = new HashMap<>();
        }
        
        System.out.println(fL("", 0) - 1);
    }

    static long fL(String s, int bitmask) {
        if (bitmask == (1 << N) - 1) return isPalindrome(s) ? 1 : 0;
        if (dpL[bitmask].containsKey(s)) return dpL[bitmask].get(s);

        long count = isPalindrome(s) ? 1 : 0;
        
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) != 0) continue;
            
            if (words[i].length() <= s.length()) {
                if (s.startsWith(words[i])) {
                    count += fL(s.substring(words[i].length()), bitmask | (1 << i));
                }
            } else {
                if (words[i].startsWith(s)) {
                    String reversed = new StringBuilder(words[i].substring(s.length())).reverse().toString();
                    count += fR(reversed, bitmask | (1 << i));
                }
            }
        }

        dpL[bitmask].put(s, count);
        return count;
    }

    static long fR(String s, int bitmask) {
        if (bitmask == (1 << N) - 1) return isPalindrome(s) ? 1 : 0;
        if (dpR[bitmask].containsKey(s)) return dpR[bitmask].get(s);

        long count = isPalindrome(s) ? 1 : 0;
        
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) != 0) continue;
            
            if (words[i].length() <= s.length()) {
                if (s.endsWith(words[i])) {
                    count += fR(s.substring(0, s.length() - words[i].length()), bitmask | (1 << i));
                }
            } else {
                if (words[i].endsWith(s)) {
                    String reversed = new StringBuilder(words[i].substring(0, words[i].length() - s.length())).reverse().toString();
                    count += fL(reversed, bitmask | (1 << i));
                }
            }
        }

        dpR[bitmask].put(s, count);
        return count;
    }

    static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
