import tkinter as tk
from tkinter import ttk, messagebox

class MainWindow(tk.Tk):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("System Product Management Python")
        self.geometry("600x400")
        
        self._init_components()
        self._load_data()

    def _init_components(self):
        frame_form = tk.Frame(self)
        frame_form.pack(pady=10)

        tk.Label(frame_form, text="Name:").grid(row=0, column=0)
        self.txt_name = tk.Entry(frame_form)
        self.txt_name.grid(row=0, column=1, padx=5)

        tk.Label(frame_form, text="Price:").grid(row=0, column=2)
        self.txt_price = tk.Entry(frame_form)
        self.txt_price.grid(row=0, column=3, padx=5)

        btn_save = tk.Button(frame_form, text="SAVE", command=self._save_action)
        btn_save.grid(row=0, column=4, padx=10)

        columns = ("ID", "Name", "Base Price", "IVA Price")
        self.tree = ttk.Treeview(self, columns=columns, show="headings")
        
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=100)
            
        self.tree.pack(expand=True, fill='both', padx=10, pady=10)

    def _save_action(self):
        name = self.txt_name.get()
        price_str = self.txt_price.get()
        
        try:
            price = float(price_str)
            data_to_send = {
                "name": name,
                "priceBase": price
            }
            
            self.controller.add_product(data_to_send)
            
            self.txt_name.delete(0, tk.END)
            self.txt_price.delete(0, tk.END)
            self._load_data()
            
        except ValueError:
            messagebox.showerror("Error", "The price must be a number")

    def _load_data(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
            
        rows = self.controller.get_data_for_table()
        for row in rows:
            self.tree.insert("", tk.END, values=row)