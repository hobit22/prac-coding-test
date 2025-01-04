import re
from itertools import permutations


def solution(expression):
    answer = 0

    operation_list = list()
    if '*' in expression:
        operation_list.append('*')
    if '+' in expression:
        operation_list.append('+')
    if '-' in expression:
        operation_list.append('-')

    operation_permutations = list(permutations(operation_list))
    expression = re.split('([^0-9])', expression)

    for operation_permutation in operation_permutations:
        copied_expression = expression[:]
        for operator in operation_permutation:
            while operator in copied_expression:
                op_idx = copied_expression.index(operator)
                cal = str(
                    eval(copied_expression[op_idx - 1] + copied_expression[op_idx] + copied_expression[op_idx + 1])
                )

                copied_expression[op_idx - 1] = cal
                copied_expression = copied_expression[:op_idx] + copied_expression[op_idx + 2:]

        answer = max(answer, abs((int(copied_expression[0]))))

    return answer