import tkinter as tk
from tkinter import ttk, messagebox
from model.Store import Store
from controller.CsvStrategy import CsvStrategy
from controller.JsonStrategy import JsonStrategy
from controller.MongoStrategy import MongoStrategy
from controller.DataManager import DataManager

class FrmStore(tk.Tk):
    def __init__(self, manager):
        super().__init__()
        self.manager = manager
        self.title("Store")
        self.geometry("600x450")
        self.setup_ui()

    def setup_ui(self):
        tk.Label(self, text="Store", font=("Arial", 12, "bold")).pack(pady=10)

        input_frame = tk.Frame(self)
        input_frame.pack(pady=5)

        tk.Label(input_frame, text="ID:").grid(row=0, column=0, padx=5)
        self.txt_id = tk.Entry(input_frame, width=10)
        self.txt_id.grid(row=0, column=1, padx=5)

        tk.Label(input_frame, text="Name:").grid(row=0, column=2, padx=5)
        self.txt_name = tk.Entry(input_frame, width=15)
        self.txt_name.grid(row=0, column=3, padx=5)

        tk.Label(input_frame, text="Price:").grid(row=0, column=4, padx=5)
        self.txt_price = tk.Entry(input_frame, width=10)
        self.txt_price.grid(row=0, column=5, padx=5)

        tk.Label(input_frame, text="Category:").grid(row=0, column=6, padx=5)
        self.txt_category = tk.Entry(input_frame, width=15)
        self.txt_category.grid(row=0, column=7, padx=5)

        persist_frame = tk.Frame(self)
        persist_frame.pack(pady=10, anchor="w", padx=20)
        
        tk.Label(persist_frame, text="Save:").pack(side="left")
        self.cmb_persistence = ttk.Combobox(persist_frame, values=["CSV", "JSON", "MONGO"], state="readonly")
        self.cmb_persistence.set("CSV")
        self.cmb_persistence.pack(side="left", padx=5)
        self.cmb_persistence.bind("<<ComboboxSelected>>", self.on_persistence_change)

        columns = ("id", "name", "price", "category")
        self.tbl_store = ttk.Treeview(self, columns=columns, show="headings", height=8)
        self.tbl_store.heading("id", text="Id")
        self.tbl_store.heading("name", text="Name")
        self.tbl_store.heading("price", text="Price")
        self.tbl_store.heading("category", text="Category")
        self.tbl_store.pack(pady=10, fill="x", padx=20)

        btn_frame = tk.Frame(self)
        btn_frame.pack(pady=10)

        tk.Button(btn_frame, text="Create", command=self.btn_create_action).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Find", command=self.btn_find_action).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Delete", command=self.btn_delete_action).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Update", command=self.btn_update_action).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Load", command=self.btn_load_action).pack(side="left", padx=5)

    def on_persistence_change(self, event):
        op = self.cmb_persistence.get()
        if op == "MONGO": self.manager.set_strategy(MongoStrategy())
        elif op == "JSON": self.manager.set_strategy(JsonStrategy())
        elif op == "CSV": self.manager.set_strategy(CsvStrategy())

    def btn_create_action(self):
        try:
            store = Store(
                int(self.txt_id.get()), 
                self.txt_name.get(), 
                float(self.txt_price.get()), 
                self.txt_category.get()
            )
            self.manager.create(store)
            messagebox.showinfo("Success", "Product saved")
        except ValueError:
            messagebox.showerror("Error", "Invalid data")

    def btn_load_action(self):
        for item in self.tbl_store.get_children():
            self.tbl_store.delete(item)
        
        lista = self.manager.load_all()
        for s in lista:
            self.tbl_store.insert("", "end", values=(s.id, s.name, s.price, s.category))

    def btn_find_action(self):
        try:
            id_search = int(self.txt_id.get())
            s = self.manager.find(id_search)
            if s:
                self.txt_name.delete(0, tk.END)
                self.txt_name.insert(0, s.name)
                self.txt_price.delete(0, tk.END)
                self.txt_price.insert(0, str(s.price))
                self.txt_category.delete(0, tk.END)
                self.txt_category.insert(0, s.category)
            else:
                messagebox.showwarning("Warning", "Product not found")
        except:
            messagebox.showerror("Error", "Check ID field")

    def btn_update_action(self):
        id_edit = int(self.txt_id.get())
        editado = Store(id_edit, self.txt_name.get(), float(self.txt_price.get()), self.txt_category.get())
        self.manager.update(id_edit, editado)
        messagebox.showinfo("Info", "Product updated")

    def btn_delete_action(self):
        if messagebox.askyesno("Confirm", "Are you sure you want to delete?"):
            self.manager.delete(int(self.txt_id.get()))
            messagebox.showinfo("Info", "Product removed")

if __name__ == "__main__":
    
    manager = DataManager(CsvStrategy())
    manager = DataManager(JsonStrategy())
    manager = DataManager(MongoStrategy())

    app = FrmStore(manager)
    app.mainloop()