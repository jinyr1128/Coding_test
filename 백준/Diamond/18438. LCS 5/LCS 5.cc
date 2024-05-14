#include <iostream>
#include <vector>
using namespace std;

vector<int> lcsLen(const string &X, const string &Y) {
    int n = Y.size();
    vector<int> curr(n + 1), prev(n + 1);
    for (char x : X) {
        swap(curr, prev);
        for (int j = 1; j <= n; ++j)
            curr[j] = (x == Y[j - 1]) ? prev[j - 1] + 1 : max(prev[j], curr[j - 1]);
    }
    return curr;
}

string h(const string &S, const string &T) {
    int m = S.size(), n = T.size();
    if (!m || !n) return "";
    if (m == 1) return T.find(S) != string::npos ? S : "";
    if (n == 1) return S.find(T) != string::npos ? T : "";
    int mid = m / 2, k = 0, mx = 0;
    auto L1 = lcsLen(S.substr(0, mid), T);
    auto L2 = lcsLen(string(S.rbegin(), S.rbegin() + (m - mid)), string(T.rbegin(), T.rend()));
    for (int j = 0; j <= n; ++j) if (L1[j] + L2[n - j] > mx) mx = L1[j] + L2[n - j], k = j;
    return h(S.substr(0, mid), T.substr(0, k)) + h(S.substr(mid), T.substr(k));
}

int main() {
    string S, T;
    cin >> S >> T;
    string lcs = h(S, T);
    cout << lcs.size() << '\n' << lcs << '\n';
}
