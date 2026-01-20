class Discount:
    _instance = None

    def __new__(cls, percentage: float):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.percentage = percentage
        return cls._instance

    def set_percentage(self, percentage: float):
        self.percentage = percentage

    def get_percentage(self) -> float:
        return self.percentage
