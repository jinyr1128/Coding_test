import java.util.*;

public class Main {
    private static List<Long> factors = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();

        if (n <= 1) {
            throw new IllegalArgumentException("Input must be greater than 1.");
        }

        factorize(n);
        Collections.sort(factors);

        for (long factor : factors) {
            System.out.println(factor);
        }
    }

    private static long multiply(long a, long b, long mod) {
        long result = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result + a) % mod;
            }
            b >>= 1;
            a = (a * 2) % mod;
        }
        return result;
    }

    private static long power(long a, long b, long mod) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = multiply(result, a, mod);
            }
            b >>= 1;
            a = multiply(a, a, mod);
        }
        return result;
    }

    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        long[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

        long d = n - 1;
        int s = 0;
        while (d % 2 == 0) {
            d /= 2;
            s++;
        }

        for (long a : primes) {
            if (a == n) return true;
            if (a > n) break;

            long x = power(a, d, n);
            if (x == 1 || x == n - 1) continue;

            boolean composite = true;
            for (int r = 0; r < s; r++) {
                x = multiply(x, x, n);
                if (x == n - 1) {
                    composite = false;
                    break;
                }
            }
            if (composite) return false;
        }
        return true;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void factorize(long n) {
        if (n == 1) return;
        if (n % 2 == 0) {
            factors.add(2L);
            factorize(n / 2);
            return;
        }
        if (isPrime(n)) {
            factors.add(n);
            return;
        }

        long x = 2, y = 2, c = 1, g = n;
        Random rand = new Random();

        do {
            if (g == n) {
                x = rand.nextInt((int) Math.min(n - 2, Integer.MAX_VALUE)) + 2;
                y = x;
                c = rand.nextInt(20) + 1;
            }

            x = pollardFunction(x, c, n);
            y = pollardFunction(pollardFunction(y, c, n), c, n);
            g = gcd(Math.abs(x - y), n);
        } while (g == 1);

        factorize(n / g);
        factorize(g);
    }

    private static long pollardFunction(long x, long c, long n) {
        return (multiply(x, x, n) + c) % n;
    }
}
