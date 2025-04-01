import java.util.*;
import java.io.*;

public class Main {
    static int MAXN = 1010101;

    static int[] par;
    static long[] sz;

    static Pair[] arr;           // 철판 (s, t)
    static ArrayList<Pair2> poi; // (값, 철판인덱스)
    static ArrayList<Integer>[] gp; // 루트별 소속 판

    static class Pair {
        long s, t;
        public Pair(long s, long t){
            this.s = s;
            this.t = t;
        }
    }

    static class Pair2 implements Comparable<Pair2> {
        long val;
        int idx;
        public Pair2(long v, int i){
            val = v;
            idx = i;
        }
        public int compareTo(Pair2 o){
            // 값 오름차순, 값이 같으면 철판 idx 오름차순
            if(this.val == o.val) return Integer.compare(this.idx, o.idx);
            return Long.compare(this.val, o.val);
        }
    }

    public static void main(String[] args){
        FastReader sc = new FastReader();
        int n = sc.nextInt();

        arr = new Pair[n+1];
        poi = new ArrayList<>();
        par = new int[n+1];
        sz = new long[n+1];
        gp = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            gp[i] = new ArrayList<>();
        }

        long ans = 0L;
        for(int i=1;i<=n;i++){
            long s = sc.nextLong();
            long t = sc.nextLong();
            arr[i] = new Pair(s, t);
            ans += (s + t);

            poi.add(new Pair2(s, i));
            poi.add(new Pair2(t, i));
        }

        // Union-Find 초기화
        for(int i=1;i<=n;i++){
            par[i] = i;
            sz[i] = 1;
        }

        // poi 정렬
        Collections.sort(poi);

        // 인접한 poi 중 값이 같으면 union
        for(int i=1; i<poi.size(); i++){
            if(poi.get(i).val == poi.get(i-1).val){
                int p1 = poi.get(i).idx;
                int p2 = poi.get(i-1).idx;
                unionUF(p1, p2);
            }
        }

        // 루트별로 철판 모으기
        for(int i=1;i<=n;i++){
            int r = findUF(i);
            gp[r].add(i);
        }

        // 각 루트 처리
        for(int r=1;r<=n;r++){
            int cnt = gp[r].size();
            if(cnt == 0) continue; // 빈 그룹

            // 모은 철판들의 s,t를 전부 loc에
            ArrayList<Long> loc = new ArrayList<>();
            for(int idx : gp[r]){
                loc.add(arr[idx].s);
                loc.add(arr[idx].t);
            }
            // 중복 제거
            Collections.sort(loc);
            // Java에서 직접 unique 처리
            // 방법1: 수동으로 
            ArrayList<Long> uniqueLoc = new ArrayList<>();
            uniqueLoc.add(loc.get(0));
            for(int i=1;i<loc.size();i++){
                if(!loc.get(i).equals(loc.get(i-1))){
                    uniqueLoc.add(loc.get(i));
                }
            }

            // cnt개만큼 가장 작은 값 빼기
            for(int i=0;i<cnt;i++){
                ans -= uniqueLoc.get(i);
            }
        }

        System.out.println(ans);
    }

    static int findUF(int x){
        if(par[x] == x) return x;
        return par[x] = findUF(par[x]);
    }

    static void unionUF(int a, int b){
        a = findUF(a);
        b = findUF(b);
        if(a == b) return;
        // 크기가 큰 쪽에 작은 쪽을 붙이기
        if(sz[a] < sz[b]){
            par[a] = b;
            sz[b] += sz[a];
        } else {
            par[b] = a;
            sz[a] += sz[b];
        }
    }

    // 빠른 입력용
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null||!st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
    }
}
