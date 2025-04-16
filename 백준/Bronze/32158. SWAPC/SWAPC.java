import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = sc.nextInt();
        String s = sc.next();

        char[] chars = s.toCharArray();
        List<Integer> pIdx = new ArrayList<>();
        List<Integer> cIdx = new ArrayList<>();

        // P와 C의 위치를 저장
        for (int i = 0; i < len; i++) {
            if (chars[i] == 'P') pIdx.add(i);
            else if (chars[i] == 'C') cIdx.add(i);
        }

        // 가능한 쌍 개수만큼 스왑
        int min = Math.min(pIdx.size(), cIdx.size());
        for (int i = 0; i < min; i++) {
            int pi = pIdx.get(i);
            int ci = cIdx.get(i);

            // 문자 위치 스왑
            char temp = chars[pi];
            chars[pi] = chars[ci];
            chars[ci] = temp;
        }

        // 결과 출력
        System.out.println(new String(chars));
    }
}
