from tkinter import messagebox

class StockNotifier:
    _instance = None

    def __init__(self):
        pass

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = StockNotifier()
        return cls._instance

    def alert_low_stock(self, product: str, stock: int):
        messagebox.showwarning(
            "Stock Alert",
            f"Low stock for product: {product}\nCurrent stock: {stock}"
        )
