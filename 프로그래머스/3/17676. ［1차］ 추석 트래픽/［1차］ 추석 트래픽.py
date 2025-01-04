def get_request_count_during_one_second(time, start_and_end_times):
    request_count = 0
    start = time
    end = time + 1000
    for start_and_end_time in start_and_end_times:
        if start_and_end_time[1] >= start and start_and_end_time[0] < end:
            request_count += 1
    return request_count


def solution(lines):
    answer = 0
    start_and_end_times = []
    for line in lines:
        _, time, duration = line.split()
        time = time.split(':')
        duration = float(duration.replace('s', '')) * 1000
        end = (int(time[0]) * 3600 + int(time[1]) * 60 + float(time[2])) * 1000
        start = end - duration + 1
        start_and_end_times.append([start, end])

    for start_and_end_time in start_and_end_times:
        answer = max(answer, get_request_count_during_one_second(start_and_end_time[0], start_and_end_times),
                     get_request_count_during_one_second(start_and_end_time[1], start_and_end_times))
    return answer