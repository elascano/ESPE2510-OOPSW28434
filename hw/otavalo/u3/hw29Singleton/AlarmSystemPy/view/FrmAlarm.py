import tkinter as tk
from tkinter import messagebox

class FrmAlarm(tk.Tk):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Alarm System - Singleton")
        self.geometry("300x250")

        tk.Label(self, text="Minimum Stock", font=("Arial", 14)).pack(pady=10)
        tk.Label(self, text="New Stock:").pack()
        
        self.txt_new_stock = tk.Entry(self)
        self.txt_new_stock.pack(pady=5)

        tk.Button(self, text="Update", command=self._on_click).pack(pady=20)

    def _on_click(self):
        val = self.txt_new_stock.get()
        self.controller.handle_update(val)

    def show_alert(self, msg):
        messagebox.showwarning("LOW STOCK ALERT", msg)