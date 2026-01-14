class Product:
    def __init__(self, name: str, base_price: float):
        self.__name = name
        self.__base_price = base_price
        self.__total_price = 0.0

    @property
    def name(self): return self.__name

    @property
    def base_price(self): return self.__base_price

    @property
    def total_price(self): return self.__total_price

    @total_price.setter
    def total_price(self, value): self.__total_price = value