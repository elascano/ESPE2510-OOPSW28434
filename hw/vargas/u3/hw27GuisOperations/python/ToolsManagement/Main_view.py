import tkinter as tk
from tkinter import ttk, messagebox
from DataBaseManager_utils import DatabaseManager
from TaxCalculator_utils import TaxCalculator
from Tool_model import Tool

MONGO_URI = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
DB_NAME = "ResourcesDB"
COLLECTION_NAME = "tools"

class AppInterface:
    def __init__(self, root, db_manager):
        self.db = db_manager
        self.root = root
        self.root.title("Tools Manager")
        self.root.geometry("1000x700")
        self._setup_ui()

    def _setup_ui(self):
        form_frame = ttk.LabelFrame(self.root, text="Tools Form", padding=20)
        form_frame.pack(fill="x", padx=10, pady=5)

        ttk.Label(form_frame, text="ID:").grid(row=0, column=0, padx=5)
        self.ent_id = ttk.Entry(form_frame)
        self.ent_id.grid(row=0, column=1, padx=5)

        ttk.Button(form_frame, text=" Search", command=self.on_search).grid(row=0, column=2)

        ttk.Label(form_frame, text="Description:").grid(row=0, column=3, padx=5)
        self.ent_desc = ttk.Entry(form_frame)
        self.ent_desc.grid(row=0, column=4, padx=5)

        ttk.Label(form_frame, text="Price ($):").grid(row=0, column=5, padx=5)
        self.ent_price = ttk.Entry(form_frame)
        self.ent_price.grid(row=0, column=6, padx=5)
        
        ttk.Label(form_frame, text="Stock:").grid(row=0, column=7, padx=5)
        self.ent_stock = ttk.Entry(form_frame)
        self.ent_stock.grid(row=0, column=8, padx=5)

        btn_box = ttk.Frame(form_frame)
        btn_box.grid(row=1, column=0, columnspan=7, pady=15)
        
        ttk.Button(btn_box, text="Save New", command=self.on_save).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Update", command=self.on_update).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Delete", command=self.on_delete).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Clear", command=self.clear_form).pack(side="left", padx=5)

        list_frame = ttk.LabelFrame(self.root, text="Inventory", padding=10)
        list_frame.pack(fill="both", expand=True, padx=10, pady=5)

        tool_bar = ttk.Frame(list_frame)
        tool_bar.pack(fill="x", pady=5)
        ttk.Button(tool_bar, text="Refresh List", command=self.load_list).pack(side="left")
        ttk.Button(tool_bar, text="Calculate Total Inventory", command=self.on_calc_total).pack(side="right")

        cols = ("id", "desc", "base", "total", "stock")
        self.tree = ttk.Treeview(list_frame, columns=cols, show="headings")
        self.tree.heading("id", text="ID")
        self.tree.heading("desc", text="Description")
        self.tree.heading("base", text="Base Price")
        self.tree.heading("stock", text="Stock")
        self.tree.heading("total", text="Total w/ Tax (15%)")
        self.tree.bind("<Double-1>", self.on_select_item) 
        self.tree.pack(fill="both", expand=True)

    def on_save(self):
        try:
            new_tool = self._create_tool_from_form()

            new_tool.total_with_tax = TaxCalculator.calculate_tax_for_product(new_tool)

            if self.db.insert_product(new_tool):
                messagebox.showinfo("Success", "Tool Saved!")
                self.clear_form()
                self.load_list()
            else:
                messagebox.showerror("Error", "ID exists or DB Error")
        except ValueError:
            messagebox.showerror("Error", "Check price format")

    def on_update(self):
        try:
            tool = self._create_tool_from_form()
            tool.total_with_tax = TaxCalculator.calculate_tax_for_product(tool)
            
            if self.db.update_product(tool):
                messagebox.showinfo("Success", "Tool Updated")
                self.clear_form()
                self.load_list()
            else:
                messagebox.showerror("Error", "Tool ID not found")
        except ValueError: pass

    def on_delete(self):
        p_id = self.ent_id.get()
        if not p_id: return
        if messagebox.askyesno("Confirm", "Delete this tool?"):
            if self.db.delete_product(p_id):
                messagebox.showinfo("Deleted", "Done")
                self.clear_form()
                self.load_list()

    def on_search(self):
        p_id = self.ent_id.get()
        prod = self.db.find_product_by_id(p_id)
        if prod:
            self._fill_form(prod)
        else:
            messagebox.showinfo("Info", "Not Found")

    def on_calc_total(self):
        all_products = self.db.get_all_products() 
        if not all_products: return

        base, total = TaxCalculator.calculate_inventory_total(all_products)
        
        msg = f"Items: {len(all_products)}\nTotal Base: ${base:.2f}\nTotal w/ Tax: ${total:.2f}"
        messagebox.showinfo("Financial Summary", msg)

    def load_list(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
            
        products = self.db.get_all_products()
        for p in products:
            self.tree.insert("", "end", values=(p.id, p.description, p.base_price, p.total_with_tax, p.stock))


    def _create_tool_from_form(self):
        p_id = self.ent_id.get()
        desc = self.ent_desc.get()
        price = float(self.ent_price.get())
        stock_txt = self.ent_stock.get()
        stock = int(stock_txt) if stock_txt else 0
        if not p_id: raise ValueError
        return Tool(p_id, desc, price, stock)

    def _fill_form(self, tool):
        self.clear_form()
        self.ent_id.insert(0, tool.id)
        self.ent_desc.insert(0, tool.description)
        self.ent_price.insert(0, tool.base_price)
        self.ent_stock.insert(0, tool.stock)

    def clear_form(self):
        self.ent_id.delete(0, tk.END)
        self.ent_desc.delete(0, tk.END)
        self.ent_price.delete(0, tk.END)
        self.ent_stock.delete(0, tk.END)

    def on_select_item(self, event):
        sel = self.tree.selection()
        if sel:
            vals = self.tree.item(sel[0])['values']
            temp_tool = Tool(str(vals[0]), vals[1], vals[2], vals[3])
            self._fill_form(temp_tool)

if __name__ == "__main__":
    db_instance = DatabaseManager(MONGO_URI, DB_NAME, COLLECTION_NAME)
    root = tk.Tk()
    app = AppInterface(root, db_instance)
    root.mainloop()