import math

def solution(progresses, speeds):
    # 각 작업의 남은 일수를 계산
    days = [math.ceil((100 - progress) / speed) for progress, speed in zip(progresses, speeds)]
    
    # 배포마다 기능의 개수를 기록할 리스트
    answer = []
    # 첫 번째 작업의 배포 기준
    deploy_day = days[0]
    # 같은 날 배포될 기능의 개수
    count = 0
    
    for day in days:
        if day <= deploy_day:
            # 현재 작업이 이전 배포일에 포함될 수 있다면
            count += 1
        else:
            # 새 배포일 시작
            answer.append(count)
            count = 1
            deploy_day = day
    
    # 마지막 배포일 처리
    answer.append(count)
    return answer
