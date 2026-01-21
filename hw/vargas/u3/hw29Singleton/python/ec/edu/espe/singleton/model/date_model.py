class DateModel:

    def __init__(self, day: int = 0, month: int = 0, year: int = 0, hour: int = 0, minute: int = 0):
        self.day = day
        self.month = month
        self.year = year
        self.hour = hour
        self.minute = minute

    def __str__(self):
        return f"{self.year:04d}-{self.month:02d}-{self.day:02d} @ {self.hour:02d}:{self.minute:02d}"

    def is_before(self, other: 'DateModel') -> bool:
        if self.year != other.year:
            return self.year < other.year
        if self.month != other.month:
            return self.month < other.month
        if self.day != other.day:
            return self.day < other.day
        if self.hour != other.hour:
            return self.hour < other.hour
        return self.minute < other.minute