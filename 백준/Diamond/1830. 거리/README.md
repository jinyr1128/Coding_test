# [Diamond V] 거리 - 1830 

[문제 링크](https://www.acmicpc.net/problem/1830) 

### 성능 요약

메모리: 6748 KB, 시간: 152 ms

### 분류

볼록 껍질, 분할 정복, 기하학, 회전하는 캘리퍼스, 스위핑

### 제출 일자

2024년 10월 31일 11:59:19

### 문제 설명

<p>2차원 평면에서 두 점 사이의 거리를 정의하는 방법은 여러 가지가 있을 수 있다. 가장 일반적으로 사용되는 유클리드 거리(Euclidean distance)는 두 점을 일직선으로 연결했을 때의 길이를 말한다. 이는 한 점에서 다른 점으로 가는 최단 거리라고 볼 수 있다.</p>

<p>이와는 달리 맨해튼 거리(Manhattan distance)는 두 점의 x좌표 차이와 y좌표 차이를 더한 값으로 정의된다. 뉴욕의 자치구 맨해튼의 이름이 붙여진 이 거리는 한 점에서 다른 점으로 x축 또는 y축에 평행하게 이동했을 때의 거리가 된다.</p>

<p>또한 체비셰프 거리(Chebyshev distance)는 두 점의 x좌표 차이와 y좌표 차이 중 큰 값을 갖는 거리이다. 체스 게임에서 왕(King)이 한 점에서 다른 점으로 이동할 때 필요한 최소 이동 횟수와 같다는 점에서 체스판 거리(Chessboard distance)라고도 한다.</p>

<p>이와 같은 세 가지 거리 개념에 대해서, 2차원 평면상에 서로 다른 N개의 점이 주어졌을 때 가장 가까운 두 점 사이의 거리와 가장 먼 두 점 사이의 거리를 각각 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에는 점의 개수 N(2 ≤ N ≤ 100,000)이 주어진다. 이어서 다음 N개 줄에 걸쳐 각 점의 x좌표와 y좌표가 빈 칸을 사이에 두고 주어진다. 주어지는 좌표는 절댓값이 10,000을 넘지 않는 정수이다.</p>

### 출력 

 <p>첫째, 둘째 줄에는 유클리드 거리로 최대 거리와 최소 거리를 각각 제곱해서 출력한다. 셋쩨, 넷째 줄에는 맨해튼 거리로 최대 거리와 최소 거리를 각각 출력한다. 다섯째, 여섯째 줄에는 체비셰프 거리로 최대 거리와 최소 거리를 각각 출력한다.</p>

