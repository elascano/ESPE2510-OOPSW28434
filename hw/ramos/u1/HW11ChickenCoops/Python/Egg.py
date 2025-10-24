class Egg:
    def __init__(self, size: str):
        self.__size = size  # atributo privado
    
    def __str__(self):
        return f"Egg{{size={self.__size}}}"
    
    # Getter
    def get_size(self) -> str:
        return self.__size
    
    # Setter
    def set_size(self, size: str):
        self.__size = size
