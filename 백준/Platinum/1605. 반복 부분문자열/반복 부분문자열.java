import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int P = 29; // 소수로 사용
    static final int MOD = 10007; // 해시 테이블 크기
    static int[] pexp = new int[200005]; // 거듭제곱 값 저장
    static int len;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 거듭제곱 미리 계산
        precomputePowers();

        // 입력 처리
        len = Integer.parseInt(br.readLine());
        str = br.readLine();

        // 이진 탐색
        int l = 0, r = len;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (hasRepeatedSubstring(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        // 결과 출력
        System.out.println(l);
    }

    static void precomputePowers() {
        pexp[0] = 1;
        for (int i = 1; i < pexp.length; i++) {
            pexp[i] = (pexp[i - 1] * P) % MOD;
        }
    }

    static boolean hasRepeatedSubstring(int L) {
        List<Integer>[] hashTable = new ArrayList[MOD];
        for (int i = 0; i < MOD; i++) {
            hashTable[i] = new ArrayList<>();
        }

        // 초기 해시 계산
        int hash = 0;
        for (int i = 0; i < L; i++) {
            hash = (hash * P + (str.charAt(i) - 'a')) % MOD;
        }
        hashTable[hash].add(0);

        // 슬라이딩 윈도우를 사용해 해시 계산
        for (int i = L; i < len; i++) {
            hash = (hash - (str.charAt(i - L) - 'a') * pexp[L - 1] % MOD + MOD) % MOD;
            hash = (hash * P + (str.charAt(i) - 'a')) % MOD;

            // 해시값이 같은 부분 문자열이 있는지 확인
            for (int start : hashTable[hash]) {
                if (realMatch(start, i - L + 1, L)) {
                    return true;
                }
            }

            // 현재 해시값 추가
            hashTable[hash].add(i - L + 1);
        }

        return false;
    }

    static boolean realMatch(int s1, int s2, int L) {
        for (int i = 0; i < L; i++) {
            if (str.charAt(s1 + i) != str.charAt(s2 + i)) {
                return false;
            }
        }
        return true;
    }
}
