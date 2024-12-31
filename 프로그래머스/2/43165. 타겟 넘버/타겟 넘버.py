def solution(numbers, target):
    all_ways = []

    def recursion(numbers, cur_idx, cur_sum):
        if cur_idx == len(numbers):
            all_ways.append(cur_sum)
            return
        recursion(numbers, cur_idx + 1, cur_sum + numbers[cur_idx])
        recursion(numbers, cur_idx + 1, cur_sum - numbers[cur_idx])

    recursion(numbers,0, 0)

    target_count = 0
    for way in all_ways:
        if way == target:
            target_count += 1
    return target_count