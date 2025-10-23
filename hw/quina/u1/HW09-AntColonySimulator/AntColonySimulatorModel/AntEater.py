import time

class AntEater:
    def __init__(self, id, position=(0, 0)):
        self.id = id
        self.position = position
        self.state = "hungry" 
        self.ants_eaten = 0
        self.ticks_since_last_action = 0

    def move(self):
        print(f"AntEater {self.id} is roaming around at {self.position}")

    def eat_ant(self, ant):
        print(f"AntEater {self.id} ate Ant {ant.id} ")
        self.ants_eaten += 1
        self.state = "eating"
        self.ticks_since_last_action = 0

    def sleep(self):
        self.state = "sleeping"
        print(f"AntEater {self.id} is sleeping...")

    def tick(self):
        self.ticks_since_last_action += 1

        if self.state == "hungry":
            self.move()

        elif self.state == "eating":
            if self.ticks_since_last_action >= 10:
                if self.ants_eaten >= 50:
                    self.sleep()
                else:
                    print(f"AntEater {self.id} finished eating one ant.")
                    self.state = "hungry"

        elif self.state == "sleeping":
            if self.ticks_since_last_action >= 600:
                self.state = "hungry"
                print(f"AntEater {self.id} woke up and is hungry again.")