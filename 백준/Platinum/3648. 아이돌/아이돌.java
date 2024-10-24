import java.util.*;

public class Main {
    static int contestantNum, judgesNum, nodeNum;
    static List<List<Integer>> adj;
    static int[] visitedOrder, sccLabel;
    static Stack<Integer> stack;
    static int order = 1, sccOrder = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            contestantNum = scanner.nextInt();
            judgesNum = scanner.nextInt();
            nodeNum = (contestantNum * 2 + 1) * 2;
            adj = new ArrayList<>(nodeNum);
            for (int i = 0; i < nodeNum; i++) {
                adj.add(new ArrayList<>());
            }
            adj.get(3).add(2); // 상근이가 반드시 뽑히도록 함의 그래프 간선을 추가

            for (int i = 0; i < judgesNum; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int notA, notB;

                a *= 2;
                notA = a + 1;
                b *= 2;
                notB = b + 1;

                if (a < 0) {
                    a = (-a) + 1;
                    notA = a - 1;
                }
                if (b < 0) {
                    b = (-b) + 1;
                    notB = b - 1;
                }

                adj.get(notA).add(b);
                adj.get(notB).add(a);
            }

            if (solve2SAT()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        scanner.close();
    }

    static void tarjanSCC() {
        visitedOrder = new int[nodeNum];
        Arrays.fill(visitedOrder, -1);
        sccLabel = new int[nodeNum];
        Arrays.fill(sccLabel, -1);
        stack = new Stack<>();
        order = 1;
        sccOrder = 1;

        for (int i = 2; i < nodeNum; i++) {
            if (visitedOrder[i] == -1) {
                findSCC(i);
            }
        }
    }

    static int findSCC(int nowNode) {
        int minOrder = visitedOrder[nowNode] = order++;
        stack.push(nowNode);

        for (int nextNode : adj.get(nowNode)) {
            if (visitedOrder[nextNode] == -1) {
                minOrder = Math.min(minOrder, findSCC(nextNode));
            } else if (sccLabel[nextNode] == -1) {
                minOrder = Math.min(minOrder, visitedOrder[nextNode]);
            }
        }

        if (minOrder == visitedOrder[nowNode]) {
            while (true) {
                int temp = stack.pop();
                sccLabel[temp] = sccOrder;
                if (temp == nowNode) break;
            }
            sccOrder++;
        }

        return minOrder;
    }

    static boolean solve2SAT() {
        tarjanSCC();

        if (sccLabel[2] > sccLabel[3]) {
            return false;
        }

        for (int i = 2; i < nodeNum; i += 2) {
            if (sccLabel[i] == sccLabel[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
