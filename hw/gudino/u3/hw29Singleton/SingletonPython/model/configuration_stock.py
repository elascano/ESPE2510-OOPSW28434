class ConfigurationStock:
    _instance = None

    def __init__(self):
        self.minimum_stock = 10

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = ConfigurationStock()
        return cls._instance
