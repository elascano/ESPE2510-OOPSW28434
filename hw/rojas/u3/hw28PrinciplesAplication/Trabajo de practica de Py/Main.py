import tkinter as tk
from tkinter import messagebox, ttk
from Calculadora_IVA import CalculadoraIVA
from Conexion_DB import DBProductos

class App:
    def __init__(self, root):
        self.root = root
        self.root.title("Flexible Inventory System")
        self.root.geometry("700x550")
        
        # Initialize classes
        self.calc = CalculadoraIVA(0.15)
        self.db = DBProductos()

        self.setup_ui()
        self.update_table()

    def setup_ui(self):
        # --- Control Panel ---
        frame = tk.LabelFrame(self.root, text="Control Panel", padx=15, pady=15)
        frame.pack(pady=10, padx=20, fill="x")

        tk.Label(frame, text="Product ID:").grid(row=0, column=0, sticky="e")
        self.ent_id = tk.Entry(frame)
        self.ent_id.grid(row=0, column=1, pady=5)

        tk.Label(frame, text="Description:").grid(row=1, column=0, sticky="e")
        self.ent_desc = tk.Entry(frame)
        self.ent_desc.grid(row=1, column=1, pady=5)

        tk.Label(frame, text="Price without VAT:").grid(row=2, column=0, sticky="e")
        self.ent_price = tk.Entry(frame)
        self.ent_price.grid(row=2, column=1, pady=5)

        # --- Buttons ---
        btn_frame = tk.Frame(self.root)
        btn_frame.pack(pady=10)
        
        tk.Button(btn_frame, text="Save", command=self.save, bg="#28a745", fg="white", width=12).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Update", command=self.update, bg="#17a2b8", fg="white", width=12).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Delete", command=self.delete, bg="#dc3545", fg="white", width=12).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Totalize List", command=self.show_total, bg="#ffc107", width=12).pack(side="left", padx=5)

        # --- View Table ---
        self.table = ttk.Treeview(self.root, columns=("ID", "DESC", "P_RECALC", "P_DB"), show="headings")
        self.table.heading("ID", text="ID")
        self.table.heading("DESC", text="Description")
        self.table.heading("P_RECALC", text="Base (Recalculated)")
        self.table.heading("P_DB", text="Total (In DB)")
        self.table.pack(pady=10, padx=20, fill="both", expand=True)

    def save(self):
        try:
            p_id = self.ent_id.get()
            if self.db.id_exists(p_id):
                return messagebox.showwarning("Warning", "This ID already exists.")
            
            p_without = float(self.ent_price.get())
            p_with = self.calc.calculate_with_vat(p_without)
            
            data = {
                "_id": p_id,
                "description": self.ent_desc.get(),
                "price_without_vat": p_without,
                "price_with_vat": p_with,
                "app_version": "1.0"
            }
            
            self.db.save(data)
            messagebox.showinfo("Success", "Product registered.")
            self.update_table()
        except ValueError:
            messagebox.showerror("Error", "Price must be a number.")

    def update(self):
        p_id = self.ent_id.get()
        current_product = self.db.find_by_id(p_id)
        
        if current_product:
            try:
                p_without = float(self.ent_price.get())
                p_with = self.calc.calculate_with_vat(p_without)
                
                new_data = {
                    "description": self.ent_desc.get(),
                    "price_without_vat": p_without,
                    "price_with_vat": p_with
                }
                
                self.db.update(p_id, new_data)
                messagebox.showinfo("Success", "Product updated.")
                self.update_table()
            except ValueError:
                messagebox.showerror("Error", "Invalid price.")
        else:
            messagebox.showerror("Error", "No product found with that ID.")

    def delete(self):
        p_id = self.ent_id.get()
        if self.db.delete(p_id):
            messagebox.showinfo("Success", "Product deleted.")
            self.update_table()
        else:
            messagebox.showerror("Error", "ID not found.")

    def show_total(self):
        products = self.db.get_all()
        total = self.calc.calculate_total_list(products)
        messagebox.showinfo("Balance", f"Accumulated total (15% VAT included):\n${total}")

    def update_table(self):
        # Clear table
        for i in self.table.get_children(): self.table.delete(i)
        
        # Load from DB
        for p in self.db.get_all():
            # Recalculate base price when reading for validation
            p_base_recalc = self.calc.calculate_without_vat(p.get('price_with_vat', 0))
            
            self.table.insert("", "end", values=(
                p.get('_id'), 
                p.get('description', 'N/A'), 
                f"{p_base_recalc:.2f}", 
                f"{p.get('price_with_vat', 0):.2f}"
            ))

if __name__ == "__main__":
    root = tk.Tk()
    app = App(root)
    root.mainloop()