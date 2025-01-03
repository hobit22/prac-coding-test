from collections import deque

def solution(priorities, location):
    answer = 0
    
    queue = deque()
    
    for i in range(len(priorities)):
        queue.append((i, priorities[i]))
        
    while queue:
        now_idx, now_priority = queue.popleft()
        
        if exist_max_priority(now_priority, queue):
            queue.append((now_idx, now_priority))
        else:
            answer +=1
            if now_idx == location:
                break
        
        
    
    
    return answer

def exist_max_priority(priority, queue):
    for q in queue:
        if q[1] > priority:
            return True
    return False