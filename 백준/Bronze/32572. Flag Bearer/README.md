# [Bronze I] Flag Bearer - 32572 

[문제 링크](https://www.acmicpc.net/problem/32572) 

### 성능 요약

메모리: 14348 KB, 시간: 104 ms

### 분류

구현, 문자열

### 제출 일자

2026년 2월 5일 16:03:58

### 문제 설명

<p>The bears have decided to abandon their solitary lives as predators and hunt together. They have formed two groups on the opposite sides of the farm and are preparing to abduct several smaller piglets, whose fate now seems very uncertain.</p>

<p>To prevent their plans from being revealed, the bears will communicate during the operation at a distance using the semaphore alphabet, which they will further encrypt with Caesar’s cipher. Caesar’s cipher is defined by a single non-negative integer $C$. Each letter in the message is encrypted by replacing it with the letter that is $C$ positions further in the alphabet. When the position of the used letter goes beyond the end of the alphabet, counting continues cyclically from the start of the alphabet. So, for example, with $C = 5$, the second-to-last letter in the alphabet is encrypted as the fourth letter in the alphabet. Each letter of the semaphore alphabet is encoded by the position of two lower or upper limbs of a signaling mammal that stands upright, facing the recipient of the message. In the diagram shown below, the center of the diagram represents the torso position of the signaling mammal, and its two limbs are represented by two of the eight possible segments radiating from the center. The assignment between limb positions and letters of the alphabet is provided on a separate page (following the samples).</p>

<p>Now, the bears are going to encrypt their short messages. They are good at semaphore alphabet, but they need some help with Caesar’s cipher. Please provide them with this assistance.</p>

### 입력 

 <p>The first input line contains two integers $N$ ($1 ≤ N ≤ 26$) and $C$ ($0 ≤ C ≤ 25$), representing the length of the word to be encrypted and the constant of the Caesar cipher. Next, there are $9N$ lines, each with $9$ characters. Each $9$ successive lines represent one character in the message. The characters are “<code>.</code>”, “<code>*</code>”, or “<code>#</code>”. Asterisk “<code>*</code>” appears only once in each $9$ lines and it corresponds to the center of the semaphore scheme. Hash symbols “<code>#</code>” correspond to limbs. Coded words use standard English alphabet with $26$ characters.</p>

### 출력 

 <p>Print the encrypted message, in the same format as the input.</p>

