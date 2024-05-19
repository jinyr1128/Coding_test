import java.util.*;

public class Main {
    static final int MAX = 1000000;
    static long[] segmentTree;
    static int[] candies;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        candies = new int[MAX + 1];
        int treeHeight = (int) Math.ceil(Math.log(MAX) / Math.log(2));
        int treeSize = (1 << (treeHeight + 1));
        segmentTree = new long[treeSize];

        List<Command> commands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            if (a == 1) {
                int b = sc.nextInt();
                commands.add(new Command(a, b, -1));
            } else {
                int b = sc.nextInt();
                int c = sc.nextInt();
                commands.add(new Command(a, b, c));
            }
        }

        for (Command command : commands) {
            if (command.type == 1) {
                int rank = command.b;
                int flavor = query(1, 1, MAX, rank);
                System.out.println(flavor);
                update(1, 1, MAX, flavor, -1);
            } else {
                int flavor = command.b;
                int count = command.c;
                update(1, 1, MAX, flavor, count);
            }
        }
    }

    static class Command {
        int type, b, c;

        Command(int type, int b, int c) {
            this.type = type;
            this.b = b;
            this.c = c;
        }
    }

    static int query(int node, int start, int end, int count) {
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        if (segmentTree[node * 2] >= count) {
            return query(node * 2, start, mid, count);
        } else {
            return query(node * 2 + 1, mid + 1, end, count - (int) segmentTree[node * 2]);
        }
    }

    static void update(int node, int start, int end, int idx, int diff) {
        if (idx < start || idx > end) {
            return;
        }
        segmentTree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }
}
