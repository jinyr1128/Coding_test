#include <iostream>
#include <vector>
#include <string>
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

string hirschberg(const string &S, const string &T) {
    int m = S.size(), n = T.size();
    if (m == 0) return "";
    if (m == 1) return T.find(S) != string::npos ? S : "";
    if (n == 0) return "";
    if (n == 1) return S.find(T) != string::npos ? T : "";
    
    int mid = m / 2;
    auto L1 = lcsLen(S.substr(0, mid), T);
    auto L2 = lcsLen(string(S.rbegin(), S.rbegin() + (m - mid)), string(T.rbegin(), T.rend()));
    
    int k = 0, max = 0;
    for (int j = 0; j <= n; ++j)
        if (L1[j] + L2[n - j] > max) {
            max = L1[j] + L2[n - j];
            k = j;
        }
    return hirschberg(S.substr(0, mid), T.substr(0, k)) + hirschberg(S.substr(mid), T.substr(k));
}

int main() {
    string S, T;
    cin >> S >> T;
    string lcs = hirschberg(S, T);
    cout << lcs.size() << '\n' << lcs << '\n';
    return 0;
}
