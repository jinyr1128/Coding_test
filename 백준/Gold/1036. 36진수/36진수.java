import java.util.*;

class Decimal36 implements Comparable<Decimal36> {
    int[] cipher = new int[60];
    int maxDigit = 0;

    // 큰 값이 우선 순위를 갖도록 함
    @Override
    public int compareTo(Decimal36 t) {
        if (this.maxDigit != t.maxDigit) {
            return Integer.compare(this.maxDigit, t.maxDigit);
        }
        for (int i = this.maxDigit; i >= 0; i--) {
            if (this.cipher[i] != t.cipher[i]) {
                return Integer.compare(this.cipher[i], t.cipher[i]);
            }
        }
        return 0;
    }
}

public class Main {
    static PriorityQueue<Decimal36> decQueue = new PriorityQueue<>(Collections.reverseOrder());
    static Decimal36[] byCipher = new Decimal36[36];
    static Decimal36 total = new Decimal36();
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 초기화
        for (int i = 0; i < 36; i++) {
            byCipher[i] = new Decimal36();
        }

        for (int i = 0; i < N; i++) {
            String temp = sc.next();
            if (temp.length() == 1 && temp.charAt(0) == '0') {
                continue;
            }
            for (int j = 0; j < temp.length(); j++) {
                int target = charToDec36(temp.charAt(j));
                byCipher[target].cipher[temp.length() - j - 1] += 35 - target;
                total.cipher[temp.length() - j - 1] += target;
            }
        }

        // 각 자릿수를 정리하고 우선순위 큐에 넣기
        for (int i = 0; i < 36; i++) {
            arrangeCipher(byCipher[i]);
            decQueue.add(byCipher[i]);
        }

        K = sc.nextInt();

        // K개의 최댓값을 더하기
        for (int i = 0; i < K; i++) {
            Decimal36 temp = decQueue.poll();
            for (int j = 0; j < 60; j++) {
                total.cipher[j] += temp.cipher[j];
            }
        }

        arrangeCipher(total);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = total.maxDigit; i >= 0; i--) {
            sb.append(cipherToChar(total.cipher[i]));
        }
        System.out.println(sb);
        sc.close();
    }

    // 자릿수를 정리하여 올림을 처리
    static void arrangeCipher(Decimal36 de) {
        for (int i = 0; i < 60; i++) {
            if (de.cipher[i] > 0) {
                de.maxDigit = i;
                if (de.cipher[i] > 35) {
                    de.cipher[i + 1] += de.cipher[i] / 36;
                    de.cipher[i] = de.cipher[i] % 36;
                }
            }
        }
    }

    // 문자를 36진수로 변환
    static int charToDec36(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else {
            return c - 'A' + 10;
        }
    }

    // 36진수 값을 문자로 변환
    static char cipherToChar(int i) {
        if (i < 10) {
            return (char) (i + '0');
        } else {
            return (char) (i - 10 + 'A');
        }
    }
}
