import tkinter as tk
from tkinter import ttk, messagebox

class LibraryView:
    def __init__(self, root, controller):
        self.root = root
        self.controller = controller
        self.root.title("Library Management - Python")
        self.root.geometry("600x700")
        self.root.configure(bg="#f4f7f6")

        self.card = tk.Frame(self.root, bg="white", padx=20, pady=20, highlightbackground="#ddd", highlightthickness=1)
        self.card.pack(pady=20, padx=20, fill="x")

        tk.Label(self.card, text="Book Entry", font=("Arial", 18, "bold"), bg="white").pack(pady=10)

        self.txt_name = self._create_input("Book Title")
        self.txt_price = self._create_input("Daily Fee ($)")
        self.txt_qty = self._create_input("Delay Days")

        self.btn_save = tk.Button(
            self.card, text="SAVE REGISTRY", command=self.handle_save, 
            bg="#6c5ce7", fg="white", font=("Arial", 11, "bold"), pady=10, cursor="hand2", bd=0
        )
        self.btn_save.pack(fill="x", pady=10)

        # 
        self.style = ttk.Style()
        self.style.theme_use("default")
        self.style.configure("Treeview.Heading", foreground="black",                            
                            font=("Arial", 10, "bold"),
                            background="#f0f0f0") 

        self.columns = ("Title", "Fee", "Days", "Fine", "Total")
        self.tree = ttk.Treeview(self.root, columns=self.columns, show="headings", height=10)

        self.tree.heading("Title", text="Book Title")
        self.tree.heading("Fee", text="Fee ($)")
        self.tree.heading("Days", text="Delay Days")
        self.tree.heading("Fine", text="Fine (3%)")
        self.tree.heading("Total", text="Total Pay")

        for col in self.columns:
            self.tree.column(col, width=100, anchor="center")

        self.tree.pack(pady=20, padx=20, fill="both", expand=True)
        self.load_data()

    def _create_input(self, label_text):
        tk.Label(self.card, text=label_text, bg="white", font=("Arial", 9)).pack(anchor="w")
        entry = tk.Entry(self.card, font=("Arial", 11), bd=1, relief="solid")
        entry.pack(fill="x", pady=(0, 10), ipady=5)
        return entry

    def handle_save(self):
        name = self.txt_name.get().strip()
        price = self.txt_price.get()
        qty = self.txt_qty.get()

        if not name or not price or not qty:
            messagebox.showwarning("Validation", "All fields are required")
            return

        if self.controller.save_registry(name, price, qty):
            messagebox.showinfo("Success", "Registry Saved!")
            self.txt_name.delete(0, tk.END)
            self.txt_price.delete(0, tk.END)
            self.txt_qty.delete(0, tk.END)
            self.load_data()

    def load_data(self):
        for i in self.tree.get_children():
            self.tree.delete(i)
        
        books = self.controller.list_books()
        for b in books:
            self.tree.insert("", "end", values=(
                b.get('title', ''), 
                f"${b.get('daily_fee', 0)}", 
                b.get('delay_days', 0), 
                f"${b.get('tax', 0)}", 
                f"${b.get('total', 0)}"
            ))