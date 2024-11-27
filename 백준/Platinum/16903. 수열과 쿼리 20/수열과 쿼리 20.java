import java.io.*;
import java.util.*;

public class Main {

    static class Trie {
        int count;
        Trie[] node;

        public Trie() {
            count = 0;
            node = new Trie[2]; // 0과 1을 저장할 두 가지 노드
        }

        public void insert(BitSet bit, int idx) {
            count++;
            if (idx == -1) return;

            int bitValue = bit.get(idx) ? 1 : 0;
            if (node[bitValue] == null) {
                node[bitValue] = new Trie();
            }
            node[bitValue].insert(bit, idx - 1);
        }

        public void erase(BitSet bit, int idx) {
            count--;
            if (idx == -1) return;

            int bitValue = bit.get(idx) ? 1 : 0;
            node[bitValue].erase(bit, idx - 1);

            if (node[bitValue].count == 0) {
                node[bitValue] = null; // 자식 노드를 제거
            }
        }

        public int find(BitSet bit, BitSet result, int idx) {
            if (idx == -1) {
                return toInt(result);
            }

            int bitValue = bit.get(idx) ? 1 : 0;
            if (node[1 - bitValue] != null) { // 최대 XOR을 위해 반대 비트를 선택
                result.set(idx);
                return node[1 - bitValue].find(bit, result, idx - 1);
            } else {
                result.clear(idx);
                return node[bitValue].find(bit, result, idx - 1);
            }
        }

        private int toInt(BitSet bitSet) {
            int value = 0;
            for (int i = 0; i < 32; i++) {
                if (bitSet.get(i)) {
                    value |= (1 << i);
                }
            }
            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        Trie root = new Trie();
        BitSet zero = new BitSet(32);
        root.insert(zero, 31); // 초기 값 0 추가

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            BitSet bitSet = toBitSet(x);
            BitSet result = new BitSet(32);

            switch (q) {
                case 1:
                    root.insert(bitSet, 31);
                    break;
                case 2:
                    root.erase(bitSet, 31);
                    break;
                case 3:
                    int maxXOR = root.find(bitSet, result, 31);
                    bw.write(maxXOR + "\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
    }

    private static BitSet toBitSet(int x) {
        BitSet bitSet = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            if ((x & (1 << i)) != 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }
}
