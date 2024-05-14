import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Wire implements Comparable<Wire> {
        int a, b;
        Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Wire other) {
            return this.a - other.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Wire[] wires = new Wire[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(a, b);
        }

        Arrays.sort(wires);

        List<Integer> lis = new ArrayList<>();
        int[] lisIndices = new int[n];
        Arrays.fill(lisIndices, -1);

        for (int i = 0; i < n; i++) {
            int pos = Collections.binarySearch(lis, wires[i].b);
            if (pos < 0) pos = -pos - 1;
            if (pos < lis.size()) {
                lis.set(pos, wires[i].b);
            } else {
                lis.add(wires[i].b);
            }
            lisIndices[i] = pos;
        }

        int lisLength = lis.size();
        boolean[] isIncluded = new boolean[n];
        for (int i = n - 1, j = lisLength - 1; i >= 0 && j >= 0; i--) {
            if (lisIndices[i] == j) {
                isIncluded[i] = true;
                j--;
            }
        }

        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isIncluded[i]) {
                toRemove.add(wires[i].a);
            }
        }

        Collections.sort(toRemove);
        System.out.println(toRemove.size());
        for (int pos : toRemove) {
            System.out.println(pos);
        }
    }
}

