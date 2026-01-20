import tkinter as tk
from tkinter import messagebox

class ProductView(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("product registration")
        self.geometry("300x250")

        tk.Label(self, text="Product name:").pack(pady=5)
        self.ent_name = tk.Entry(self)
        self.ent_name.pack()

        tk.Label(self, text="Price:").pack(pady=5)
        self.ent_price = tk.Entry(self)
        self.ent_price.pack()

        self.btn_save = tk.Button(self, text="SAVE", bg="green", fg="white")
        self.btn_save.pack(pady=20)

    def get_data(self):
        return self.ent_name.get(), float(self.ent_price.get())

    def clear_fields(self):
        self.ent_name.delete(0, tk.END)
        self.ent_price.delete(0, tk.END)

    def show_message(self, text):
        messagebox.showinfo("Store", text)