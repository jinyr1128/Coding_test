import java.io.*;
import java.util.*;

public class Main {

    static class Complex {
        double real, imag;

        Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        Complex add(Complex o) {
            return new Complex(real + o.real, imag + o.imag);
        }

        Complex subtract(Complex o) {
            return new Complex(real - o.real, imag - o.imag);
        }

        Complex multiply(Complex o) {
            return new Complex(real * o.real - imag * o.imag, real * o.imag + imag * o.real);
        }

        Complex divide(double val) {
            return new Complex(real / val, imag / val);
        }
    }

    static void fft(Complex[] a, boolean invert) {
        int n = a.length;
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            while (j >= bit) {
                j -= bit;
                bit >>= 1;
            }
            j += bit;
            if (i < j) {
                Complex temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double angle = 2 * Math.PI / len * (invert ? -1 : 1);
            Complex wlen = new Complex(Math.cos(angle), Math.sin(angle));
            for (int i = 0; i < n; i += len) {
                Complex w = new Complex(1, 0);
                for (int j = 0; j < len / 2; j++) {
                    Complex u = a[i + j];
                    Complex v = a[i + j + len / 2].multiply(w);
                    a[i + j] = u.add(v);
                    a[i + j + len / 2] = u.subtract(v);
                    w = w.multiply(wlen);
                }
            }
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                a[i] = a[i].divide(n);
            }
        }
    }

    static long[] multiply(long[] a, long[] b) {
        int n = 1;
        while (n < a.length + b.length) n <<= 1;

        Complex[] fa = new Complex[n];
        Complex[] fb = new Complex[n];

        for (int i = 0; i < n; i++) {
            fa[i] = new Complex(i < a.length ? a[i] : 0, 0);
            fb[i] = new Complex(i < b.length ? b[i] : 0, 0);
        }

        fft(fa, false);
        fft(fb, false);

        for (int i = 0; i < n; i++) {
            fa[i] = fa[i].multiply(fb[i]);
        }

        fft(fa, true);

        long[] result = new long[n];
        for (int i = 0; i < n; i++) {
            result[i] = Math.round(fa[i].real);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] x = new long[n * 2];
        long[] y = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Long.parseLong(st.nextToken());
            x[i + n] = x[i];
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            y[n - i - 1] = Long.parseLong(st.nextToken());
        }

        long[] result = multiply(x, y);

        long maxScore = 0;
        for (int i = n - 1; i < n + n - 1; i++) {
            maxScore = Math.max(maxScore, result[i]);
        }

        System.out.println(maxScore);
    }
}
