import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder result = new StringBuilder();

        while (true) {
            int[] nums = new int[4];
            for (int i = 0; i < 4; i++) {
                nums[i] = sc.nextInt();
            }

            // 종료 조건 (0 0 0 0)
            if (nums[0] == 0 && nums[1] == 0 && nums[2] == 0 && nums[3] == 0) {
                break;
            }

            int steps = 0;
            while (!(nums[0] == nums[1] && nums[1] == nums[2] && nums[2] == nums[3])) {
                int[] nextNums = new int[4];
                for (int i = 0; i < 4; i++) {
                    nextNums[i] = Math.abs(nums[i] - nums[(i + 1) % 4]);
                }
                nums = nextNums;
                steps++;
            }

            result.append(steps).append("\n");
        }

        System.out.print(result);
    }
}
