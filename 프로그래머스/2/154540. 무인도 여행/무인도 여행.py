from collections import deque 
    
dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

def solution(maps):
    answer = []
    visited = [[False for _ in range(len(maps[0]))] for _ in range(len(maps))]
    
    for r in range(len(maps)):
        for c in range(len(maps[0])):
            if visited[r][c] is False and maps[r][c] != 'X':
                answer.append(bfs(r, c, maps, visited))
    
    if len(answer) == 0:
        answer.append(-1)
    
    answer.sort()
    
    return answer


                
def bfs(r, c, maps, visited):
    sum = 0
    q = deque()
    q.append((r, c))
    visited[r][c] = True
    
    while q:
        cur_r, cur_c = q.popleft()
        sum += int(maps[cur_r][cur_c])
        for i in range(4):
            next_r = cur_r + dr[i]
            next_c = cur_c + dc[i]
            if next_r < 0 or next_c < 0 or next_r >= len(maps) or next_c >= len(maps[0]):
                continue
            
            if visited[next_r][next_c] is False and maps[next_r][next_c] != 'X':
                visited[next_r][next_c] = True
                q.append((next_r, next_c))
        
    return sum
