import java.util.*;

class Circle {
    int pos;     // 원의 시작/끝 좌표
    boolean isOpen; // 원의 시작이면 true, 끝이면 false
    int idx;     // 원의 인덱스

    Circle(int pos, boolean isOpen, int idx) {
        this.pos = pos;
        this.isOpen = isOpen;
        this.idx = idx;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Circle> circles = new ArrayList<>();
        
        // 원의 정보를 입력받아 리스트에 시작과 끝 점을 추가
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int r = sc.nextInt();
            circles.add(new Circle(x - r, true, i));  // 원의 시작
            circles.add(new Circle(x + r, false, i)); // 원의 끝
        }

        // 좌표 기준으로 정렬
        circles.sort((a, b) -> Integer.compare(a.pos, b.pos));

        Stack<Integer> stack = new Stack<>();
        
        // 원을 체크하는 로직
        for (Circle circle : circles) {
            if (circle.isOpen) {
                // 시작점이면 스택에 원 인덱스를 추가
                stack.push(circle.idx);
            } else {
                // 끝점에서 스택의 맨 위 원과 현재 원이 일치해야 함
                if (stack.isEmpty() || stack.pop() != circle.idx) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        // 모든 원이 조건을 만족하면 YES 출력
        System.out.println("YES");
    }
}
