import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    // base^exp with BigInteger exponent (binary exponentiation)
    static BigInteger powBig(BigInteger base, BigInteger exp) {
        if (exp.signum() == 0) return BigInteger.ONE; // including 0^0 -> 1 by combinatorial convention
        if (base.signum() == 0) return BigInteger.ZERO; // 0^positive = 0
        if (base.equals(BigInteger.ONE)) return BigInteger.ONE;

        BigInteger result = BigInteger.ONE;
        BigInteger b = base;
        BigInteger e = exp;

        while (e.signum() > 0) {
            if (e.testBit(0)) {
                result = result.multiply(b);
                if (result.signum() == 0) return BigInteger.ZERO; // early exit if ever becomes 0
            }
            b = b.multiply(b);
            e = e.shiftRight(1);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder out = new StringBuilder();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (st.countTokens() < 4) continue;

            BigInteger C = new BigInteger(st.nextToken());
            BigInteger W = new BigInteger(st.nextToken());
            BigInteger L = new BigInteger(st.nextToken());
            BigInteger P = new BigInteger(st.nextToken());

            if (C.signum() == 0 && W.signum() == 0 && L.signum() == 0 && P.signum() == 0) break;

            BigInteger exponent = W.multiply(L).multiply(P);
            BigInteger ans = powBig(C, exponent);
            out.append(ans.toString()).append('\n');
        }

        System.out.print(out.toString());
    }
}
