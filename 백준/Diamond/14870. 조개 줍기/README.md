# [Diamond IV] 조개 줍기 - 14870 

[문제 링크](https://www.acmicpc.net/problem/14870) 

### 성능 요약

메모리: 35104 KB, 시간: 664 ms

### 분류

자료 구조, 다이나믹 프로그래밍, 세그먼트 트리, 두 포인터

### 제출 일자

2025년 2월 7일 09:10:35

### 문제 설명

<p>바닷가에 있는 정올시에는 여러 지역들이 정방형 격자 형태로 나뉘어져 있다. 각 지역에는 한 가구만 살고 있으며 가장 왼쪽 위에 있는 지역에는 수산시장이 있다. (수산시장이 있는 지역에도 한 가구가 산다.)</p>

<p>각 지역에서 수산시장으로 이동하려면 다음의 두 가지 방법만을 이용하여 이동한다.</p>

<ol>
	<li>바로 위에 붙어있는 지역으로 이동</li>
	<li>바로 왼쪽에 붙어있는 지역으로 이동</li>
</ol>

<p>각 지역에 사는 사람들은 매일 수산시장으로 출근하면서 지나가는 지역에서 조개를 주워 수산시장에서 판다. (출발하는 지역과 수산물 시장이 있는 지역에서도 조개를 주울 수 있다.)</p>

<p>각 지역에는 자연보호를 위해 그 지역을 지나가는 한 가구가 주울 수 있는 조개 개수의 최댓값이 있다. (여러 가구가 지나가면 가구마다 최댓값만큼 조개를 주울 수 있을 정도로 조개는 충분하다.)</p>

<p>예를 들어, 격자의 크기가 3 (행) × 3 (열) 이고 지역마다 한 가구가 주울 수 있는 조개 개수의 최댓값이 다음의 왼쪽 표와 같다고 하자.</p>

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14870/1.png" style="height:95px; width:235px"></p>

<p>각 지역에서 하루에 수산시장에 팔 수 있는 조개 개수의 최댓값은 오른쪽 표와 같다. 예를 들면, 맨 오른쪽 아래 격자에서 출발해서 최대로 조개를 줍는 방법은 위쪽으로 2번 이동하고 왼쪽으로 두 번 이동하는 방법이며 총 8+6+7+2+3=26개의 조개를 주울 수 있다. 그리고 이 도시의 아홉 지역에서 수산시장에 팔 수 있는 조개 개수의 최댓값을 모두 합하면 3+5+12+7+9+18+12+15+26=107 개이다.</p>

<p>정올시의 성실한 공무원들은 주기적으로 각 지역의 조개 숫자를 조사하여 한 가구가 주울 수 있는 조개 개수의 최댓값을 수정한다. 하지만 급격한 변화는 위험하므로 최댓값을 +1이나 –1만큼만 조정하는 것이 가능하다. 조정하지 않은 지역의 조개 개수의 최댓값은 그대로 유지된다. 예를 들면, 위의 왼쪽 표에서 격자의 1행, 2열의 2가 3으로 바뀐다면 각 지역에서 주울 수 있는 조개 개수의 최댓값과 각 지역에서 하루에 수산시장에 팔 수 있는 조개 개수의 최댓값이 다음과 같이 바뀐다.</p>

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14870/2.png" style="height:94px; width:236px"></p>

<p>격자 칸마다 주울 수 있는 조개 개수의 최댓값의 초기 값이 주어지고, 격자 칸에서 주울 수 있는 조개 개수의 최댓값의 변화를 입력으로 받아, 각 지역에서 하루에 수산시장에 팔 수 있는 조개 개수의 최댓값을 계산해서 그 합을 출력하는 프로그램을 작성하라.</p>

### 입력 

 <p>표준 입력으로 다음 정보가 주어진다. 첫 번째 줄에는 격자의 행(열)의 개수를 나타내는 정수 N(2 ≤ N ≤ 1,500)이 주어진다. 다음 N줄에는 각 격자 칸에서 주울 수 있는 조개의 개수가 제일 윗 행부터 순서대로 한 줄에 한 행씩 주어진다. 한 행의 값은 가장 왼쪽 열의 값부터 하나씩 나열된다. 주어지는 값들은 0이상 1,000이하이다. 다음 N개의 줄에는 각 줄에 변화 명령이 하나씩 주어진다. 변화 명령의 첫 글자는 U 혹은 D이다. 이어서 빈칸을 하나 두고 두 자연수가 주어지는데, 첫 번째는 행 번호, 두 번째는 열 번호이다. 첫 글자가 U인 경우 행 번호, 열 번호에 해당하는 격자 칸에서 주울 수 있는 조개의 개수가 1 증가한다. D인 경우 해당 격자 칸에서 주울 수 있는 조개의 개수가 1 감소한다. 감소한 결과가 음수가 되는 경우는 없다. 각 변화에 대해서 아래에 지정한 값을 출력해야 한다. 주어진 각 변화 명령은 이전 변화들이 모두 적용된 결과에 적용된다.</p>

### 출력 

 <p>표준 출력으로, 초기에 각 격자 칸의 입력을 기준으로 모든 지역에서 팔 수 있는 조개 개수의 최댓값의 합을 출력한다. 그 다음, 각 변화 명령에 대해 그 변화 명령을 적용한 후, 모든 지역에서 팔 수 있는 조개 개수의 최댓값의 합을 출력한다. 전체 출력은 N+1줄임에 주의하라.</p>

