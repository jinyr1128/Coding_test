# [Diamond II] Robotic Cow Herd - 14166 

[문제 링크](https://www.acmicpc.net/problem/14166) 

### 성능 요약

메모리: 16060 KB, 시간: 168 ms

### 분류

이분 탐색, 자료 구조, 그리디 알고리즘, 우선순위 큐

### 제출 일자

2025년 3월 31일 10:33:44

### 문제 설명

<p>Bessie is hoping to fool Farmer John by building a herd of K realistic robotic cows (1≤K≤100,000).</p>

<p>It turns out that building a robotic cow is somewhat complicated. There are N (1≤N≤100,000) individual locations on the robot into which microcontrollers must be connected (so a single microcontroller must be connected at each location). For each of these locations, Bessie can select from a number of different models of microcontroller, each varying in cost.</p>

<p>For the herd of robotic cows to look convincing to Farmer John, no two robots should behave identically. Therefore, no two robots should have exactly the same set of microcontrollers. For any pair of robots, there should be at least one location at which the two robots use a different microcontroller model. It is guaranteed that there will always be enough different microcontroller models to satisfy this constraint.</p>

<p>Bessie wants to make her robotic herd as cheaply as possible. Help her determine the minimum possible cost to do this!</p>

### 입력 

 <p>The first line of input contains N and K separated by a space.</p>

<p>The following N lines contain a description of the different microcontroller models available for each location. The ith such line starts with M<sub>i</sub> (1≤M<sub>i</sub>≤10), giving the number of models available for location i. This is followed by M<sub>i</sub> space separated integers P<sub>i,j</sub> giving the costs of these different models (1≤P<sub>i,j</sub>≤100,000,000).</p>

### 출력 

 <p>Output a single line, giving the minimum cost to construct K robots.</p>

