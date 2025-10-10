import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hp = Integer.parseInt(st.nextToken());
        int wp = Integer.parseInt(st.nextToken());
        int hm = Integer.parseInt(st.nextToken());
        int wm = Integer.parseInt(st.nextToken());

        char[][] pattern = new char[hp][wp];
        for (int i = 0; i < hp; i++) {
            pattern[i] = br.readLine().toCharArray();
        }

        char[][] masterpiece = new char[hm][wm];
        for (int i = 0; i < hm; i++) {
            masterpiece[i] = br.readLine().toCharArray();
        }

        System.out.println(solve(hp, wp, hm, wm, pattern, masterpiece));
    }

    private static int solve(int hp, int wp, int hm, int wm, char[][] pattern, char[][] masterpiece) {
        long P1 = 31; // 가로 해싱을 위한 소수
        long P2 = 37; // 세로 해싱을 위한 소수
        long M = 1_000_000_007; // 모듈러

        // 롤링 해시를 위한 제곱수 미리 계산
        long p1_pow_wp = 1;
        for (int i = 0; i < wp - 1; i++) {
            p1_pow_wp = (p1_pow_wp * P1) % M;
        }

        long p2_pow_hp = 1;
        for (int i = 0; i < hp - 1; i++) {
            p2_pow_hp = (p2_pow_hp * P2) % M;
        }

        // 1. 패턴의 해시값 계산
        long[] patternRowHashes = new long[hp];
        for (int r = 0; r < hp; r++) {
            long rowHash = 0;
            for (int c = 0; c < wp; c++) {
                rowHash = (rowHash * P1 + pattern[r][c]) % M;
            }
            patternRowHashes[r] = rowHash;
        }

        long patternHash = 0;
        for (int r = 0; r < hp; r++) {
            patternHash = (patternHash * P2 + patternRowHashes[r]) % M;
        }

        // 2. 걸작의 모든 wp 길이의 가로 해시값 계산
        long[][] masterpieceRowHashes = new long[hm][wm - wp + 1];
        for (int r = 0; r < hm; r++) {
            long rowHash = 0;
            for (int c = 0; c < wp; c++) {
                rowHash = (rowHash * P1 + masterpiece[r][c]) % M;
            }
            masterpieceRowHashes[r][0] = rowHash;

            for (int c = 1; c <= wm - wp; c++) {
                long prevHash = masterpieceRowHashes[r][c - 1];
                long firstCharTerm = (masterpiece[r][c - 1] * p1_pow_wp) % M;
                
                // 롤링 해시: 이전 해시에서 첫 문자를 빼고, 새 문자를 더함
                rowHash = (prevHash - firstCharTerm + M) % M;
                rowHash = (rowHash * P1) % M;
                rowHash = (rowHash + masterpiece[r][c + wp - 1]) % M;
                masterpieceRowHashes[r][c] = rowHash;
            }
        }

        // 3. 세로 방향으로 롤링 해시를 적용하여 패턴과 비교
        int count = 0;
        for (int c = 0; c <= wm - wp; c++) {
            // 첫 윈도우의 해시값 계산
            long currentHash = 0;
            for (int r = 0; r < hp; r++) {
                currentHash = (currentHash * P2 + masterpieceRowHashes[r][c]) % M;
            }
            if (currentHash == patternHash) {
                count++;
            }

            // 세로로 윈도우를 한 칸씩 내리면서 해시값 갱신
            for (int r = 1; r <= hm - hp; r++) {
                long prevHash = currentHash;
                long firstRowTerm = (masterpieceRowHashes[r - 1][c] * p2_pow_hp) % M;
                
                currentHash = (prevHash - firstRowTerm + M) % M;
                currentHash = (currentHash * P2) % M;
                currentHash = (currentHash + masterpieceRowHashes[r + hp - 1][c]) % M;

                if (currentHash == patternHash) {
                    count++;
                }
            }
        }

        return count;
    }
}