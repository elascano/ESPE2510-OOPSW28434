import time

class Tick:
    def __init__(self, duration_ms=100):
        self.duration_ms = duration_ms
        self.current_tick = 0

    def advance(self):
        self.current_tick += 1
        print(f"\n Tick {self.current_tick}")
        time.sleep(self.duration_ms / 1000.0)