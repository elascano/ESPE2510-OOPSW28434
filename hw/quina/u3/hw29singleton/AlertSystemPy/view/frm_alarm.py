import tkinter as tk
from tkinter import messagebox
from controller.alarm_controller import AlarmController

class FrmAlarm(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Update Minimum Stock")
        self.geometry("350x250")
        self.configure(bg="#ececec")
        self.controller = AlarmController()

        tk.Label(self, text="UPDATE MINIMUM STOCK", font=("Arial", 12, "bold"), bg="#ececec").pack(pady=20)
        
        self.txt_new_stock = tk.Entry(self, justify='center')
        self.txt_new_stock.pack(pady=10)

        tk.Button(self, text="Update", command=self.btn_update_clicked).pack(pady=20)

    def btn_update_clicked(self):
        val = self.txt_new_stock.get()
        self.controller.handle_update(val, self)

    def show_low_stock_alert(self, p_id, name, stock):
        messagebox.showwarning("Alarm", f"LOW STOCK ALERT\nID: {p_id}\nProduct: {name}\nStock: {stock}")