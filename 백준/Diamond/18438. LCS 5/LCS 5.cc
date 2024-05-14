#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

std::string hirschberg(const std::string& S, const std::string& T);

int main() {
    std::string S, T;
    std::cin >> S >> T;
    
    std::string lcs = hirschberg(S, T);
    std::cout << lcs.length() << '\n';
    std::cout << lcs << '\n';

    return 0;
}

std::vector<int> lcsLength(const std::string& X, const std::string& Y) {
    int m = X.length();
    int n = Y.length();
    std::vector<int> current(n + 1, 0);
    std::vector<int> previous(n + 1, 0);

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (X[i - 1] == Y[j - 1]) {
                current[j] = previous[j - 1] + 1;
            } else {
                current[j] = std::max(previous[j], current[j - 1]);
            }
        }
        std::swap(current, previous);
    }
    return previous;
}

std::string hirschberg(const std::string& S, const std::string& T) {
    int m = S.length();
    int n = T.length();

    if (m == 0) {
        return "";
    } else if (m == 1) {
        if (T.find(S) != std::string::npos) {
            return S;
        } else {
            return "";
        }
    } else if (n == 0) {
        return "";
    } else if (n == 1) {
        if (S.find(T) != std::string::npos) {
            return T;
        } else {
            return "";
        }
    }

    int mid = m / 2;
    std::vector<int> L1 = lcsLength(S.substr(0, mid), T);
    std::vector<int> L2 = lcsLength(std::string(S.rbegin(), S.rbegin() + (m - mid)), std::string(T.rbegin(), T.rend()));

    int k = 0;
    int max = 0;
    for (int j = 0; j <= n; ++j) {
        if (L1[j] + L2[n - j] > max) {
            max = L1[j] + L2[n - j];
            k = j;
        }
    }

    std::string lcsLeft = hirschberg(S.substr(0, mid), T.substr(0, k));
    std::string lcsRight = hirschberg(S.substr(mid), T.substr(k));

    return lcsLeft + lcsRight;
}
