class Location:
    def __init__(self, area, section):
        self.area = area
        self.section = section

    def __str__(self):
        return f"Area: {self.area}, Section: {self.section}"
