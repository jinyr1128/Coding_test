class Solution {
    public int solution(int n, int k) {
        if (n == 0) return 0;

        String num = Integer.toString(n, k);
        String[] arr = num.split("0");

        int answer = 0;
        for (String s : arr) {
            if (s.equals("") || s.equals("1")) continue;
            long number;
            try {
                number = Long.parseLong(s);
            } catch (NumberFormatException e) {
                continue; // 너무 큰 수는 무시
            }
            if (number < 2) continue;
            if (isPrime(number)) answer++;
        }

        return answer;
    }

    private boolean isPrime(long number) {
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
