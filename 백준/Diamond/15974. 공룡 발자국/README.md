# [Diamond III] 공룡 발자국 - 15974 

[문제 링크](https://www.acmicpc.net/problem/15974) 

### 성능 요약

메모리: 146132 KB, 시간: 1672 ms

### 분류

다이나믹 프로그래밍, 정렬, 기하학, 두 포인터, 각도 정렬

### 제출 일자

2025년 8월 29일 12:38:49

### 문제 설명

<p>고대 공룡들이 남쪽에서 북쪽 방향으로 걸어간 발자국들이 발견되었다. 아래 그림은 발견된 공룡 발자국들의 다양한 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/fd709687-f941-4dd8-b84c-f6539882675c/-/preview/" style="width: 252px; height: 118px;"></p>

<p>발자국을 분석한 결과 모든 발자국은 하나의 발뒤꿈치와 <em>k</em>개의 발가락 (<em>k</em> ≥ 2)을 가지며, 두 발가락 사이마다 골이 존재한다. 이를 바탕으로 위 그림의 발자국을 단순화시켜보면 아래와 같이 2<em>k</em>각형으로 표현이 된다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/a23848da-0842-49ca-a173-a8c6fbf3c269/-/preview/" style="width: 290px; height: 131px;"></p>

<p>발자국의 다각형에서 발뒤꿈치를 시작으로 반시계 방향으로 돌면 항상 첫 번째 발가락에서 좌회전, 첫 번째 골에서 우회전, 두 번째 발가락에서 좌회전, 두 번째 골에서 우회전, ……, <em>k</em>-1번째 골에서 우회전, <em>k</em>번째 발가락에서 좌회전해서 발뒤꿈치로 돌아오게 된다. 또한 발뒤꿈치와 발가락을 선분으로 잇는 경우 다음 조건을 항상 만족한다.</p>

<ol>
	<li>해당 선분은 발자국의 다각형을 벗어나지 않는다.</li>
	<li>해당 선분은 골을 지나지 않는다.</li>
</ol>

<p>다음 그림은 발자국이 될 수 없는 다각형들의 예이다.</p>

<table class="table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/ee63f0e6-eec1-4d1a-9c5d-68ba8c7c146d/-/preview/" style="width: 130px; height: 94px;"></td>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/f45662d2-b6cd-435a-a3b7-37cc04f75a0e/-/preview/" style="width: 122px; height: 93px;"></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th style="width: 50%; text-align: center;">조건 1 위배</th>
			<th style="width: 50%; text-align: center;">조건 2 위배</th>
		</tr>
	</tfoot>
</table>

<p>발자국이 발견된 일부 지역에서는 불행히도 정확한 발자국이 남아있지 않고 발자국 다각형의 꼭짓점이 될 수 있는 점들만 남아있다. 심지어 여기에는 발자국과 관련 없는 점들도 같이 남아있을 수 있다. 각 점의 위치는 좌표 (<em>x</em>, <em>y</em>)로 표현되며, <em>x</em>값은 서쪽에서 동쪽으로 증가하고 <em>y</em>값은 남쪽에서 북쪽으로 증가한다. 다행히도 점들 중 가장 남쪽에 있는 점은 유일하며 발뒤꿈치가 된다. 다음 그림의 예에서 보면 (20, 5) 점(붉은 점)이 가장 남쪽에 위치하므로 발뒤꿈치이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/2f3ed613-f0ca-4e1e-8afe-b4caf702e58a/-/preview/" style="width: 264px; height: 194px;"></p>

<p>주어진 점들로 만들 수 있는 가장 많은 발가락을 가진 발자국을 찾고 싶다. 아래는 위 그림에서 찾은 가장 많은 발가락을 가진 발자국의 한 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/e059a5ef-e38c-4505-b998-4c66ac78dc82/-/preview/" style="width: 191px; height: 169px;"></p>

<p>점들의 좌표를 입력으로 받아서, 가장 많은 발가락을 가진 발자국을 하나 찾은 뒤 찾은 다각형의 꼭짓점의 개수와 각 꼭짓점의 좌표를 출력하는 프로그램을 작성하라.</p>

### 입력 

 <p>표준 입력으로 다음 정보가 주어진다. 첫 번째 줄에는 점의 수를 나타내는 정수 <em>N</em>이 주어진다. 다음 <em>N</em>개의 각 줄에는 한 점의 <em>x</em>좌표와 <em>y</em>좌표를 나타내는 두 정수가 주어진다. 입력으로 주어진 점들은 모두 서로 다르다는 것이 보장되고, <em>y</em>좌표가 가장 작은 점이 유일하다는 것도 보장된다.</p>

### 출력 

 <p>표준 출력으로, 첫 번째 줄에는 가장 많은 발가락을 가진 발자국 다각형의 꼭짓점의 개수 <em>T</em>를 출력한다. 다음 <em>T</em>개의 각 줄에는 발뒤꿈치부터 시작하여 반시계 방향으로 돌면서 각 꼭짓점의 <em>x</em>좌표와 <em>y</em>좌표를 출력한다. 그러한 발자국이 여러 개가 있다면 그 중 하나의 발자국만 출력한다. 발자국이 존재할 수 없는 경우 0을 출력한다.</p>

