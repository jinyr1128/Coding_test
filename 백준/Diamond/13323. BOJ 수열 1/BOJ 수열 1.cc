#include <bits/stdc++.h>
using namespace std;
using ll = long long;

struct FastScanner {
    static const int S = 1 << 20;
    int idx = 0, sz = 0; char buf[S];
    inline char read() {
        if (idx >= sz) {
            sz = (int)fread(buf, 1, S, stdin);
            idx = 0;
            if (!sz) return 0;
        }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out){
        char c = read(); if(!c) return false;
        T sign=1, x=0;
        while(c!='-' && (c<'0'||c>'9')){ c=read(); if(!c) return false; }
        if(c=='-'){ sign=-1; c=read(); }
        for(; c>='0'&&c<='9'; c=read()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
} In;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!In.nextInt(N)) return 0;

    priority_queue<ll> H;  // max-heap
    long long ans = 0;

    for (int i = 1; i <= N; ++i) {
        long long a; In.nextInt(a);
        long long c = a - (long long)i;

        H.push(c);
        if (H.top() > c) {
            ans += H.top() - c;
            H.pop();
            H.push(c);
        }
    }

    cout << ans << '\n';
    return 0;
}
