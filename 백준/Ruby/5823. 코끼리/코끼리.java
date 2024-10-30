import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final int LEN = 150000;
    static final int SQ = 400;

    static int N, L, M, bLen;
    static int[] pos = new int[LEN];
    static int[] a = new int[LEN];
    static int[] b = new int[LEN];
    static int[] next = new int[SQ * 2];
    static Bucket[] buckets = new Bucket[SQ];

    static class Bucket {
        int size;
        int[] A = new int[SQ * 2];
        int[] cameras = new int[SQ * 2];
        int[] cover = new int[SQ * 2];

        int end() {
            return size > 0 ? pos[A[size - 1]] : 0;
        }

        int bound(int x) {
            int left = 0, right = size - 1, mid;
            int k = size;
            while (left <= right) {
                mid = (left + right) / 2;
                if (pos[A[mid]] > x) {
                    k = Math.min(k, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return k;
        }

        void update() {
            for (int l = 0, r = 0; l < size; ++l) {
                while (r < size && pos[A[r]] <= pos[A[l]] + L) ++r;
                next[l] = r;
            }
            for (int r = size - 1; r >= 0; --r) {
                cameras[r] = next[r] == size ? 1 : cameras[next[r]] + 1;
                cover[r] = next[r] == size ? pos[A[r]] + L : cover[next[r]];
            }
        }

        void pop(int elem) {
            int k = -1;
            for (int i = 0; i < size; ++i) {
                if (A[i] == elem) {
                    k = i;
                    break;
                }
            }
            size--;
            for (int i = k; i < size; ++i) A[i] = A[i + 1];
            update();
        }

        void push(int elem) {
            int left = 0, right = size - 1, mid;
            int k = size;
            while (left <= right) {
                mid = (left + right) / 2;
                if (pos[A[mid]] > pos[elem]) {
                    k = Math.min(k, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            size++;
            for (int i = size - 1; i > k; --i) A[i] = A[i - 1];
            A[k] = elem;
            update();
        }

        void pushBack(int index) {
            A[size++] = index;
        }
    }

    static void init() {
        for (int i = 0; i < bLen; ++i) buckets[i].size = 0;
        for (int i = 0; i < N; ++i) buckets[i / SQ].pushBack(a[i]);
        for (int i = 0; i < bLen; ++i) buckets[i].update();
    }

    static void normalize() {
        for (int i = 0, k = 0; i < bLen; ++i) {
            for (int j = 0; j < buckets[i].size; ++j, ++k) {
                a[k] = buckets[i].A[j];
                b[a[k]] = k / SQ;
            }
        }
        init();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        M = sc.nextInt();
        bLen = N / SQ + 1;

        for (int i = 0; i < N; ++i) {
            pos[i] = sc.nextInt();
            a[i] = i;
            b[i] = i / SQ;
        }

        for (int i = 0; i < bLen; i++) {
            buckets[i] = new Bucket();
        }
        
        init();

        for (int q = 0, j = 0; q < M; ++q, ++j) {
            if (j == SQ - 1) {
                j = 0;
                normalize();
            }
            int index = sc.nextInt();
            int newY = sc.nextInt();
            buckets[b[index]].pop(index);
            pos[index] = newY;
            b[index] = bLen - 1;
            for (int k = 0; k < bLen; ++k) {
                if (newY <= buckets[k].end()) {
                    b[index] = k;
                    break;
                }
            }
            buckets[b[index]].push(index);

            int cameraCount = buckets[0].cameras[0];
            int lastCovered = buckets[0].cover[0];
            for (int k = 1; k < bLen; ++k) {
                if (buckets[k].size == 0 || buckets[k].end() <= lastCovered) continue;
                int boundIndex = buckets[k].bound(lastCovered);
                cameraCount += buckets[k].cameras[boundIndex];
                lastCovered = buckets[k].cover[boundIndex];
            }

            System.out.println(cameraCount);
        }
        sc.close();
    }
}
