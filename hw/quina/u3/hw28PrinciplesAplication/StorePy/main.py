import tkinter as tk
from tkinter import ttk, messagebox
from controller.product_controller import ProductController

class FrmProduct:
    def __init__(self, root):
        self.root = root
        self.root.title("Store Inventory")
        self.root.geometry("700x550")
        self.root.configure(bg="#cbd5e1")

        uri = "mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/"
        self.controller = ProductController(uri, "StoreDB", "Product")

        tk.Label(root, text="STORE INVENTORY", font=("Arial", 18, "bold"), bg="#cbd5e1").pack(pady=20)

        frame = tk.Frame(root, bg="#e2e8f0", bd=1, relief="solid")
        frame.pack(padx=20, pady=10, fill="x")
        
        inner_frame = tk.Frame(frame, bg="#e2e8f0")
        inner_frame.pack(padx=20, pady=20)

        tk.Label(inner_frame, text="ID:", bg="#e2e8f0", font=("Arial", 10, "bold")).grid(row=0, column=0, sticky="e", pady=5)
        self.txtId = tk.Entry(inner_frame)
        self.txtId.grid(row=0, column=1, padx=10, pady=5)

        tk.Label(inner_frame, text="Name:", bg="#e2e8f0", font=("Arial", 10, "bold")).grid(row=1, column=0, sticky="e", pady=5)
        self.txtName = tk.Entry(inner_frame)
        self.txtName.grid(row=1, column=1, padx=10, pady=5)
        
        tk.Button(inner_frame, text="Search", bg="#bbf7d0", width=10, command=self.search).grid(row=1, column=2, padx=5)

        tk.Label(inner_frame, text="Base Price:", bg="#e2e8f0", font=("Arial", 10, "bold")).grid(row=2, column=0, sticky="e", pady=5)
        self.txtPrice = tk.Entry(inner_frame)
        self.txtPrice.grid(row=2, column=1, padx=10, pady=5)
        
        tk.Button(inner_frame, text="Create", bg="#dcfce7", width=10, command=self.create).grid(row=2, column=2, padx=5)

        columns = ("ID", "Name", "Base", "Final")
        self.tree = ttk.Treeview(root, columns=columns, show="headings")
        
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=100)
            
        self.tree.pack(padx=20, pady=10, fill="both", expand=True)

        footer = tk.Frame(root, bg="#cbd5e1")
        footer.pack(pady=20)
        
        tk.Button(footer, text="Update", bg="#93c5fd", width=12, font=("Arial", 10, "bold"), command=self.update).pack(side="left", padx=20)
        tk.Button(footer, text="Delete", bg="#f87171", width=12, font=("Arial", 10, "bold"), command=self.delete).pack(side="left", padx=20)

        self.load_table()

    def load_table(self):
        for i in self.tree.get_children():
            self.tree.delete(i)
        products = self.controller.get_all_products()
        for p in products:
            self.tree.insert("", "end", values=(p.get('id'), p.get('name'), p.get('basePrice'), p.get('finalPrice')))

    def create(self):
        if self.txtId.get() and self.txtName.get() and self.txtPrice.get():
            self.controller.save_product(self.txtId.get(), self.txtName.get(), self.txtPrice.get())
            self.load_table()
            self.clear_fields()
        else:
            messagebox.showwarning("Warning", "All fields are required")

    def search(self):
        p = self.controller.search_product(self.txtId.get())
        if p:
            self.txtName.delete(0, tk.END)
            self.txtName.insert(0, p.get('name', ''))
            self.txtPrice.delete(0, tk.END)
            self.txtPrice.insert(0, p.get('basePrice', ''))
        else:
            messagebox.showinfo("Info", "Product not found")

    def update(self):
        self.controller.update_product(self.txtId.get(), self.txtName.get(), self.txtPrice.get())
        self.load_table()
        messagebox.showinfo("Success", "Product updated")

    def delete(self):
        self.controller.delete_product(self.txtId.get())
        self.load_table()
        self.clear_fields()
        messagebox.showinfo("Success", "Product deleted")

    def clear_fields(self):
        self.txtId.delete(0, tk.END)
        self.txtName.delete(0, tk.END)
        self.txtPrice.delete(0, tk.END)

if __name__ == "__main__":
    root = tk.Tk()
    app = FrmProduct(root)
    root.mainloop()