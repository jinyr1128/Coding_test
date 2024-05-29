#include <bits/stdc++.h>
#define all(v) v.begin(), v.end()
#define sz(x) (int)x.size()
#define ini(x, y) memset(x, y, sizeof(x))

using namespace std;

int chk[2001];

// Trie 노드 구조체
struct TrieNode {
    map<char, TrieNode*> children;  // 자식 노드들
    bool isEndOfWord;  // 단어의 끝 여부

    TrieNode() {
        isEndOfWord = false;
        children.clear();
    }

    // 단어 삽입 함수
    void insert(const char* word) {
        if (!*word) { 
            isEndOfWord = true;
            return;
        }
        char currentChar = *word;
        if (children.find(currentChar) == children.end()) {
            children[currentChar] = new TrieNode;
        }
        children[currentChar]->insert(word + 1);
    }

    // 단어 찾기 함수
    void search(const char* word, int index, bool isColor) {
        if (isEndOfWord) chk[index]++;
        if (!*word) return;
        char currentChar = *word;
        if (children.find(currentChar) == children.end()) return;
        children[currentChar]->search(word + 1, isColor ? index + 1 : index - 1, isColor);
    }
};

int main(void) {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int C, N; 
    cin >> C >> N;
    TrieNode* colorTrie = new TrieNode;  // 색상 트라이
    TrieNode* nicknameTrie = new TrieNode;  // 닉네임 트라이

    // 색상 단어 삽입
    for (int i = 1; i <= C; i++) {
        string s; 
        cin >> s;
        colorTrie->insert(s.c_str());
    }

    // 닉네임 단어 삽입 (거꾸로)
    for (int i = 1; i <= N; i++) {
        string s; 
        cin >> s;
        reverse(all(s));
        nicknameTrie->insert(s.c_str());
    }

    int Q; 
    cin >> Q;

    // 질의 처리
    while (Q--) {
        string s; 
        cin >> s;
        ini(chk, 0);  // chk 배열 초기화

        // 색상 단어 찾기
        colorTrie->search(s.c_str(), 0, true);
        
        // 닉네임 단어 찾기 (거꾸로)
        reverse(all(s));
        nicknameTrie->search(s.c_str(), sz(s), false);

        // 결과 출력
        bool isFound = false;
        for (int i = 0; i < sz(s); i++) {
            if (chk[i] == 2) { 
                isFound = true; 
                break; 
            }
        }
        if (isFound) cout << "Yes\n";
        else cout << "No\n";
    }

    return 0;
}
