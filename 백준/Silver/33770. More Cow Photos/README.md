# [Silver III] More Cow Photos - 33770 

[문제 링크](https://www.acmicpc.net/problem/33770) 

### 성능 요약

메모리: 95048 KB, 시간: 652 ms

### 분류

애드 혹

### 제출 일자

2025년 10월 25일 16:10:19

### 문제 설명

<p>The cows are in a particularly mischievous mood today! All Farmer John wants to do is take a photograph of the cows standing in a line, but they keep moving right before he has a chance to snap the picture.</p>

<p>Specifically, each of FJ's $N$ cows $(1 \le N \le 10^5)$ has an integer height from $1$ to $N$. FJ wants to take a picture of the cows standing in line in a very specific ordering. If the cows have heights $h_1, \dots, h_K$ when lined up from left to right, he wants the cow heights to have the following three properties:</p>

<ul>
	<li>He wants the cow heights to increase and then decrease. Formally, there must exist an integer $i$ such that $h_1 \le \dots \le h_i \ge \dots \ge h_K$.</li>
	<li>He does not want any cow standing next to another cow with exactly the same height. Formally, $h_i \neq h_{i+1}$ for all $1 \le i < K$.</li>
	<li>He wants the picture to be symmetric. Formally, if $i + j = K+1$, then $h_i = h_j$.</li>
</ul>

<p>FJ wants the picture to contain as many cows as possible. Specifically, FJ can remove some cows and rearrange the remaining ones. Compute the maximum number of cows FJ can have in the picture satisfying his constraints.</p>

### 입력 

 <p>You have to answer multiple test cases.</p>

<p>The first line of input contains a single integer $T$ ($1 \leq T \leq 10^5$) denoting the number of test cases. $T$ test cases follow.</p>

<p>The first line of every test case contains a single integer $N$. The second line of every test case contains $N$ integers, the heights of the $N$ cows available. The cow heights will be between $1$ and $N$.</p>

<p>It is guaranteed the sum of $N$ over all test cases will not exceed $10^6$.</p>

### 출력 

 <p>Output $T$ lines, the $i$'th line containing the answer to the $i$'th test case. Each line should be an integer denoting the maximum number of cows FJ can include in the picture.</p>

