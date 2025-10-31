import datetime as _dt


class Poop:
    def __init__(self, chicken_id: int):
        self.chicken_id = chicken_id
        self.timestamp = _dt.datetime.now().isoformat()


    def to_dict(self):
        return {"chicken_id": self.chicken_id, "timestamp": self.timestamp}