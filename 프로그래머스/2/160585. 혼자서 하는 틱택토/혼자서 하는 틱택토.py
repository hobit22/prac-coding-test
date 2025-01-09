from collections import Counter

def solution(board):
    answer = -1
    
    counter = Counter(list(''.join(board)))
    if counter['X'] > counter['O'] or counter['X']+1 < counter['O']:
        return 0
    
    else:
        if check(board, "O"):
            if counter['O'] <= counter['X'] or check(board, "X"):
                return 0
            else:   
                return 1
        elif check(board, "X"):
            if counter['X'] < counter['O'] or check(board, "O"):
                return 0
            else:
                return 1
        else:
            return 1
    

def check(board, char):
    # 가로와 세로 체크
    for i in range(3):
        # 가로 체크
        if all(board[i][j] == char for j in range(3)):
            return True
        # 세로 체크
        if all(board[j][i] == char for j in range(3)):
            return True

    # 대각선 체크
    if all(board[i][i] == char for i in range(3)):  # 좌상단 -> 우하단
        return True
    if all(board[i][2 - i] == char for i in range(3)):  # 우상단 -> 좌하단
        return True

    return False
            
            