import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * 유클리드 호제법을 이용한 최대공약수(GCD) 계산
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * 분수를 저장하고 비교하기 위한 클래스
     */
    static class Fraction implements Comparable<Fraction> {
        int numerator;
        int denominator;
        double value; // 정렬을 위한 실수 값

        Fraction(int num, int den) {
            this.numerator = num;
            this.denominator = den;
            this.value = (double) num / den;
        }

        /**
         * 분수를 실수 값(value) 기준으로 오름차순 정렬
         */
        @Override
        public int compareTo(Fraction other) {
            return Double.compare(this.value, other.value);
        }

        /**
        * 출력을 위한 toString 오버라이드
        */
        @Override
        public String toString() {
            return this.numerator + "/" + this.denominator;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        // 기약분수를 저장할 리스트
        List<Fraction> fractions = new ArrayList<>();

        // 1. 모든 분수 생성 및 기약분수 판별
        // 분모 b (1부터 N까지)
        for (int b = 1; b <= N; b++) {
            // 분자 a (0부터 b까지)
            for (int a = 0; a <= b; a++) {
                
                // 2. gcd(a, b) == 1 이면 기약분수
                if (gcd(a, b) == 1) {
                    fractions.add(new Fraction(a, b));
                }
            }
        }

        // 3. 분수 리스트 정렬
        Collections.sort(fractions);

        // 4. 결과 출력
        // 4-1. 총 개수 출력
        System.out.println(fractions.size());
        
        // 4-2. 정렬된 분수 목록 출력
        for (Fraction frac : fractions) {
            System.out.println(frac);
        }
    }
}