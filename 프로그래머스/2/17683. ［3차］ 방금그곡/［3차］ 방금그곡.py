
def solution(m, musicinfos):
    answer = None
    musicinfos = sorted(musicinfos, key=lambda x: x.split(",")[0])

    m = replace_sharp(m)
    for musicinfo in musicinfos:
        music = Music(musicinfo)
        total_melody = music.get_total_melody()

        if m in total_melody:  # 멜로디 비교
            if not answer or music.get_duration() > answer.get_duration():
                answer = music

    return answer.name if answer else "(None)"

def replace_sharp(s):
    replacements = {
        "C#": "c",
        "D#": "d",
        "F#": "f",
        "G#": "g",
        "A#": "a",
        "B#": "b"
    }
    for sharp, flat in replacements.items():
        s = s.replace(sharp, flat)
    return s

class Music:
    def __init__(self, musicinfo):
        infos = musicinfo.split(",")
        self.name = infos[2]
        self.start = infos[0]
        self.end = infos[1]
        self.melody = replace_sharp(infos[3])

    def get_duration(self):
        start_time = self.start.split(":")
        end_time = self.end.split(":")

        return int(end_time[0]) * 60 + int(end_time[1]) - (int(start_time[0]) * 60 + int(start_time[1]))

    def get_total_melody(self):
        duration = self.get_duration()
        total_melody = ""
        for i in range(duration):
            i = i % len(self.melody)
            total_melody += self.melody[i]

        return total_melody