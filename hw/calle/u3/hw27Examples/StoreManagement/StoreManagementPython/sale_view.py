import tkinter as tk
from tkinter import ttk, messagebox
from sale_controller import SaleController

class SaleView:
    def __init__(self, root):
        self.controller = SaleController()
        self.root = root
        self.root.title("Management System - ESPE")
        self.root.geometry("700x550")
        self.selected_product_name = "" 

        frame_input = tk.Frame(root)
        frame_input.pack(pady=10)

        tk.Label(frame_input, text="Product Name:").grid(row=0, column=0, padx=5, pady=5)
        self.txt_name = tk.Entry(frame_input)
        self.txt_name.grid(row=0, column=1)

        tk.Label(frame_input, text="Unit Price:").grid(row=1, column=0, padx=5, pady=5)
        self.txt_price = tk.Entry(frame_input)
        self.txt_price.grid(row=1, column=1)

        tk.Label(frame_input, text="Quantity:").grid(row=2, column=0, padx=5, pady=5)
        self.txt_qty = tk.Entry(frame_input)
        self.txt_qty.grid(row=2, column=1)

        frame_buttons = tk.Frame(root)
        frame_buttons.pack(pady=10)

        tk.Button(frame_buttons, text="Register", command=self.save, bg="#2ecc71", fg="white", width=10).grid(row=0, column=0, padx=5)
        tk.Button(frame_buttons, text="Update", command=self.update, bg="#f1c40f", fg="black", width=10).grid(row=0, column=1, padx=5)
        tk.Button(frame_buttons, text="Delete", command=self.delete, bg="#e74c3c", fg="white", width=10).grid(row=0, column=2, padx=5)
        tk.Button(frame_buttons, text="Clear", command=self.clear_fields, bg="#95a5a6", fg="white", width=10).grid(row=0, column=3, padx=5)

        columns = ("Product", "Price", "Qty", "Total")
        self.tree = ttk.Treeview(root, columns=columns, show='headings')
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=120)
        
        self.tree.pack(padx=10, pady=10, fill=tk.BOTH, expand=True)
        
        self.tree.bind("<<TreeviewSelect>>", self.on_tree_select)
        
        self.refresh()

    def on_tree_select(self, event):
        selected_item = self.tree.focus()
        if selected_item:
            values = self.tree.item(selected_item)['values']
            self.selected_product_name = values[0]
            self.txt_name.delete(0, tk.END)
            self.txt_name.insert(0, values[0])
            self.txt_price.delete(0, tk.END)
            self.txt_price.insert(0, values[1])
            self.txt_qty.delete(0, tk.END)
            self.txt_qty.insert(0, values[2])

    def save(self):
        try:
            self.controller.create(self.txt_name.get(), float(self.txt_price.get()), int(self.txt_qty.get()))
            messagebox.showinfo("Success", "Registered!")
            self.refresh()
            self.clear_fields()
        except ValueError:
            messagebox.showerror("Error", "Invalid data")

    def update(self):
        if not self.selected_product_name:
            messagebox.showwarning("Warning", "Select a product from the table")
            return
        try:
            self.controller.update(self.selected_product_name, self.txt_name.get(), float(self.txt_price.get()), int(self.txt_qty.get()))
            messagebox.showinfo("Success", "Updated!")
            self.refresh()
            self.clear_fields()
        except ValueError:
            messagebox.showerror("Error", "Invalid data")

    def delete(self):
        if not self.selected_product_name:
            messagebox.showwarning("Warning", "Select a product from the table")
            return
        if messagebox.askyesno("Confirm", f"Delete {self.selected_product_name}?"):
            self.controller.delete(self.selected_product_name)
            self.refresh()
            self.clear_fields()

    def refresh(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
        for doc in self.controller.get_all():
            self.tree.insert("", "end", values=(doc["productName"], doc["unitPrice"], doc["quantity"], doc["totalPrice"]))

    def clear_fields(self):
        self.txt_name.delete(0, tk.END)
        self.txt_price.delete(0, tk.END)
        self.txt_qty.delete(0, tk.END)
        self.selected_product_name = ""

if __name__ == "__main__":
    root = tk.Tk()
    app = SaleView(root)
    root.mainloop()