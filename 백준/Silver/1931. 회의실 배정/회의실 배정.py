import sys
input = sys.stdin.read

def max_meetings(meetings):
    meetings.sort(key=lambda x: (x[1], x[0]))
    count = 0
    end_time = 0
    
    for start, end in meetings:
        if start >= end_time:
            count += 1
            end_time = end
    
    return count

def main():
    data = input().split()
    N = int(data[0])
    meetings = []
    
    index = 1
    for _ in range(N):
        start = int(data[index])
        end = int(data[index + 1])
        meetings.append((start, end))
        index += 2
    
    print(max_meetings(meetings))

main()
