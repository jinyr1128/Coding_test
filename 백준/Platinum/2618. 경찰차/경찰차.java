import java.util.*;
import java.io.*;

public class Main {
    static int n, w;
    static List<int[]> p = new ArrayList<>();
    static int[][] store;
    static int[][] policeStore;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = sc.nextInt();
        
        for (int i = 0; i < w; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            p.add(new int[]{x, y});
        }

        store = new int[1010][1010];
        policeStore = new int[1010][1010];

        for (int i = 0; i < 1010; i++) {
            Arrays.fill(store[i], -1);
            Arrays.fill(policeStore[i], -1);
        }

        System.out.println(findDistance(0, 0));

        int x = 0;
        int y = 0;
        for (int i = 0; i < w; i++) {
            System.out.println(policeStore[x][y]);
            if (policeStore[x][y] == 1) {
                x = i + 1;
            } else {
                y = i + 1;
            }
        }
    }

    static int calculateDistance(int police, int target, int start) {
        int policeX, policeY, targetX, targetY;
        if (start == 1) {
            policeX = 1;
            policeY = 1;
        } else if (start == 2) {
            policeX = n;
            policeY = n;
        } else {
            policeX = p.get(police - 1)[0];
            policeY = p.get(police - 1)[1];
        }
        targetX = p.get(target - 1)[0];
        targetY = p.get(target - 1)[1];
        return Math.abs(policeX - targetX) + Math.abs(policeY - targetY);
    }

    static int findDistance(int police1, int police2) {
        if (police1 == w || police2 == w) {
            return 0;
        }

        int tmp1, tmp2, move;
        move = Math.max(police1, police2) + 1;

        if (store[police1][police2] != -1) {
            return store[police1][police2];
        }

        if (police1 == 0) {
            tmp1 = findDistance(move, police2) + calculateDistance(police1, move, 1);
        } else {
            tmp1 = findDistance(move, police2) + calculateDistance(police1, move, 0);
        }

        if (police2 == 0) {
            tmp2 = findDistance(police1, move) + calculateDistance(police2, move, 2);
        } else {
            tmp2 = findDistance(police1, move) + calculateDistance(police2, move, 0);
        }

        store[police1][police2] = Math.min(tmp1, tmp2);

        if (tmp1 < tmp2) {
            policeStore[police1][police2] = 1;
        } else {
            policeStore[police1][police2] = 2;
        }

        return store[police1][police2];
    }
}
