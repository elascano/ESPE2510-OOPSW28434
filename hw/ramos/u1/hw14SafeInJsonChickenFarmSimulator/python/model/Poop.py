class Poop:
    def __init__(self, amount: int):
        self.__amount = amount  

    def __str__(self):
        return f"Poop{{amount={self.__amount}}}"

    # Getter
    def get_amount(self) -> int:
        return self.__amount

    # Setter
    def set_amount(self, amount: int):
        self.__amount = amount