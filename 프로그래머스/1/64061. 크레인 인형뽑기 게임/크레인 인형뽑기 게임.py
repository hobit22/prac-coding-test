
def solution(board, moves):
    answer = 0
    stack = []

    for move in moves:
        target_col = move - 1
        for i in range(len(board)):
            if board[i][target_col] != 0:
                stack.append(board[i][target_col])
                answer = pop_doll(stack, answer)
                board[i][target_col] = 0
                break

    return answer

def pop_doll(stack, cnt):
    if len(stack) < 2:
        return cnt

    last_doll = stack[-1]
    second_doll = stack[-2]

    if last_doll == second_doll:
        stack.pop()
        stack.pop()
        cnt += 2
        pop_doll(stack, cnt)

    return cnt