#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<ll,ll> pll;
typedef pair<int,int> pii;
#define fi first
#define se second
#define endl '\n'
#define y1 grid_limit
#define all(x) x.begin(),x.end()
const int inf=0x3f3f3f3f;

struct SegmentTree{
	int base=1<<11, tree[1<<12];
	// 구간 업데이트 함수
	void update(int l, int r, int v){
		l += base; r += base;
		while(l <= r){
			if(l & 1) tree[l] += v;
			if(~r & 1) tree[r] += v;
			l = (l + 1) >> 1;
			r = (r - 1) >> 1;
		}
	}
	// 특정 위치의 값 조회 함수
	int query(int p){
		int ret = 0;
		for(p += base; p; p >>= 1) ret += tree[p];
		return ret;
	}
} segment[1510];

int gridSize, dp[1510][1510], leftBound[1510], rightBound[1510];
int getValue(int row, int col){ return dp[row][col] + segment[row].query(col); }

int main(){
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> gridSize;
	ll totalMaxShells = 0;
	// 초기 조개 개수 입력 및 최대 조개 수 계산
	for(int i = 1; i <= gridSize; i++) 
		for(int j = 1; j <= gridSize; j++) 
			cin >> dp[i][j];
	
	for(int i = 1; i <= gridSize; i++) 
		for(int j = 1; j <= gridSize; j++){
			dp[i][j] += max(dp[i-1][j], dp[i][j-1]);
			totalMaxShells += dp[i][j];
		}
	
	cout << totalMaxShells << endl;
	
	// 변화 명령 처리
	for(int iter = 0; iter < gridSize; iter++){
		char operation; int row, col;
		cin >> operation >> row >> col;
		int valueChange = (operation == 'U' ? 1 : -1);
		leftBound[row] = rightBound[row] = col;
		for(int i = row + 1; i <= gridSize; i++) leftBound[i] = gridSize + 1, rightBound[i] = 0;
		
		// 오른쪽 방향 최대 조개 값 갱신
		for(int curRow = row, curCol = col;;){
			if(curCol < gridSize && max(getValue(curRow, curCol), getValue(curRow - 1, curCol + 1)) + valueChange == max(getValue(curRow, curCol) + valueChange, getValue(curRow - 1, curCol + 1))) curCol++;
			else curRow++;
			if(curRow > gridSize) break;
			rightBound[curRow] = curCol;
		}
		
		// 왼쪽 방향 최대 조개 값 갱신
		for(int curRow = row, curCol = col;;){
			if(curRow < gridSize && max(getValue(curRow, curCol), getValue(curRow + 1, curCol - 1)) + valueChange == max(getValue(curRow, curCol) + valueChange, getValue(curRow + 1, curCol - 1))) curRow++;
			else curCol++;
			if(curCol > gridSize || curCol > rightBound[curRow]) break;
			leftBound[curRow] = min(leftBound[curRow], curCol);
		}
		
		// 세그먼트 트리 업데이트 및 총 최대 조개 수 조정
		for(int i = row; i <= gridSize; i++){
			if(leftBound[i] <= rightBound[i]){
				segment[i].update(leftBound[i], rightBound[i], valueChange);
				totalMaxShells += valueChange * (rightBound[i] - leftBound[i] + 1);
			}
		}
		cout << totalMaxShells << endl;
	}
	return 0;
}
