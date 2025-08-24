import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean isLeap(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    static int daysInMonth(int y, int m) {
        switch (m) {
            case 1:  return 31; // Jan
            case 2:  return isLeap(y) ? 29 : 28; // Feb
            case 3:  return 31; // Mar
            case 4:  return 30; // Apr
            case 5:  return 31; // May
            case 6:  return 30; // Jun
            case 7:  return 31; // Jul
            case 8:  return 31; // Aug
            case 9:  return 30; // Sep
            case 10: return 31; // Oct
            case 11: return 30; // Nov
            case 12: return 31; // Dec
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        // 2019-01-01 은 화요일 → 0=일, 1=월, 2=화, ... 기준으로 2
        int dowFirst = 2;
        long count = 0;

        for (int y = 2019; y <= N; y++) {
            for (int m = 1; m <= 12; m++) {
                // 해당 달 13일의 요일: 첫날 요일 + 12
                int dow13 = (dowFirst + 12) % 7;
                if (dow13 == 5) count++; // 5 = 금요일

                // 다음 달의 1일 요일로 갱신
                dowFirst = (dowFirst + daysInMonth(y, m)) % 7;

                // 마지막 해의 12월 처리 후 루프 종료
                if (y == N && m == 12) break;
            }
        }

        System.out.println(count);
    }
}
