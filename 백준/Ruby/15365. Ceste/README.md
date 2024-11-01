# [Ruby V] Ceste - 15365 

[문제 링크](https://www.acmicpc.net/problem/15365) 

### 성능 요약

메모리: 25556 KB, 시간: 360 ms

### 분류

이분 탐색, 볼록 껍질, 데이크스트라, 기하학, 그래프 이론, 볼록 다각형 내부의 점 판정, 최단 경로

### 제출 일자

2024년 11월 1일 18:44:30

### 문제 설명

<p>There’s a country with N cities and M bidirectional roads. Driving on road i takes T<sub>i</sub> minutes, and costs C<sub>i</sub> kunas (Croatian currency).</p>

<p>To make the arrival to the holiday destination as pleasant as possible, you want to make it as fast and as cheap as possible. More specifically, you are in city 1 and want to minimize the product of total money spent and total time spent (overall, with all roads you drove on) in getting to a city from city 1. For each city (except city 1), output the required minimal product or -1 if city 1 and that city aren’t connected.</p>

### 입력 

 <p>The first line of input contains numbers N (1 ≤ N ≤ 2000), the number of cities, and M (1 ≤ M ≤ 2000), the number of roads.</p>

<p>Each of the following M lines contains four numbers, A<sub>i</sub>, B<sub>i</sub>, T<sub>i</sub>, C<sub>i</sub>, (1 ≤ A<sub>i</sub>, B<sub>i</sub> ≤ N, 1 ≤ T<sub>i</sub>, C<sub>i</sub> ≤ 2000) that denote there is a road connecting cities A<sub>i</sub> and B<sub>i</sub>, that it takes T<sub>i</sub> minutes to drive on it, and it costs C<sub>i</sub> kunas.</p>

<p>It is possible that multiple roads exist between two cities, but there will never be a road that connects a city with itself.</p>

### 출력 

 <p>You must output N - 1 lines. In the i th line, output the required minimal product in order to get to city (i + 1), or -1 if cities 1 and (i + 1) aren’t connected.</p>

