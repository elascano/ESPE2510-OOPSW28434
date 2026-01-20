import tkinter as tk
from tkinter import ttk, messagebox

class ComputersView:
    def __init__(self, root, controller):
        self.root = root
        self.controller = controller

        self.root.title("Computer Management")
        self.root.geometry("700x500")
        self.root.resizable(False, False)

        
        title = tk.Label(
            root,
            text="Computer Management System",
            font=("Arial", 18, "bold")
        )
        title.pack(pady=10)

    
        form_frame = tk.LabelFrame(
            root,
            text="Computer Data",
            font=("Arial", 11, "bold"),
            padx=15,
            pady=15
        )
        form_frame.pack(padx=20, pady=10, fill="x")

        
        tk.Label(form_frame, text="Brand:").grid(row=0, column=0, sticky="w", pady=5)
        self.txt_brand = tk.Entry(form_frame, width=30)
        self.txt_brand.grid(row=0, column=1, pady=5)

        tk.Label(form_frame, text="Daily Fee ($):").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_price = tk.Entry(form_frame, width=30)
        self.txt_price.grid(row=1, column=1, pady=5)

        tk.Label(form_frame, text="Delay Days:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_days = tk.Entry(form_frame, width=30)
        self.txt_days.grid(row=2, column=1, pady=5)

        
        btn_frame = tk.Frame(root)
        btn_frame.pack(pady=10)

        self.btn_save = ttk.Button(
            btn_frame,
            text="SAVE",
            command=self.handle_save
        )
        self.btn_save.pack()


        table_frame = tk.Frame(root)
        table_frame.pack(padx=20, pady=10, fill="both", expand=True)

        columns = ("Brand", "Fee", "Days", "Fine", "Total")
        self.tree = ttk.Treeview(
            table_frame,
            columns=columns,
            show="headings"
        )

        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, anchor="center")

        self.tree.pack(fill="both", expand=True)

        self.load_data()

    
    def handle_save(self):
        brand = self.txt_brand.get().strip()
        price = self.txt_price.get()
        days = self.txt_days.get()

        if not brand or not price or not days:
            messagebox.showwarning("Validation", "All fields are required")
            return

        if self.controller.save_registry(brand, price, days):
            messagebox.showinfo("Success", "Saved successfully")
            self.txt_brand.delete(0, tk.END)
            self.txt_price.delete(0, tk.END)
            self.txt_days.delete(0, tk.END)
            self.load_data()

    def load_data(self):
        for i in self.tree.get_children():
            self.tree.delete(i)

        for c in self.controller.list_computers():
            self.tree.insert("", "end", values=(
                c.get("brand", ""),
                c.get("daily_fee", ""),
                c.get("delay_days", ""),
                c.get("tax", ""),
                c.get("total", "")
            ))

