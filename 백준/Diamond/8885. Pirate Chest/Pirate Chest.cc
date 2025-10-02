#include<bits/stdc++.h>
using namespace std;

int grid[512][512];  // 그리드의 깊이를 저장하는 배열
int min_depth[512];   // 각 열의 최소 깊이를 저장하는 배열

int main() {
    cin.tie(0)->sync_with_stdio(0);

    int max_width, max_height, rows, cols;
    cin >> max_width >> max_height >> rows >> cols;

    // 그리드에 대한 입력을 받는다
    for (int i = 0; i < rows; ++i)
        for (int j = 0; j < cols; ++j) cin >> grid[i][j];
    
    min_depth[cols] = -1; // min_depth 배열의 마지막 값을 -1로 설정
    
    int64_t result = 0;  // 최종 답을 저장할 변수

    // 그리드의 각 행을 기준으로 처리
    for (int start_row = 0; start_row < rows; ++start_row) {
        // min_depth 배열을 해당 행의 깊이로 초기화
        for (int col = 0; col < cols; ++col) 
            min_depth[col] = grid[start_row][col];

        // 행을 확장하면서 최소 깊이를 업데이트
        for (int end_row = start_row; end_row < rows; ++end_row) {
            for (int col = 0; col < cols; ++col)
                min_depth[col] = min(min_depth[col], grid[end_row][col]);

            // 스택을 이용해 최적의 직사각형을 구하는 방식
            stack<pair<int, int>> stk;
            stk.emplace(-1, -1);  // 초기값으로 (-1, -1)을 삽입
            for (int right = 0; right <= cols; ++right) {
                // 현재 열의 최소 깊이를 기준으로 스택을 처리
                while (stk.top().second > min_depth[right]) {
                    int height = stk.top().second;
                    stk.pop();
                    int left = stk.top().first;
                    int height_range = end_row - start_row + 1;
                    int width_range = right - left - 1;
                    
                    // 가능한 직사각형 크기 계산
                    int area = max(min(height_range, max_height) * min(width_range, max_width), 
                                   min(width_range, max_height) * min(height_range, max_width));
                    
                    // 계산된 면적을 이용해 최종 답을 업데이트
                    int remaining_cells = rows * cols - area;
                    int64_t temp = 1ll * height * rows * cols;
                    result = max(result, ((temp - 1) / remaining_cells) * area);
                }
                // 현재 열의 최소 깊이 값과 열 번호를 스택에 넣는다
                stk.emplace(right, min_depth[right]);
            }
        }
    }

    // 계산된 최댓값을 출력
    cout << result;
    return 0;
}
