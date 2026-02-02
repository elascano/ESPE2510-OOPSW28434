import tkinter as tk
from tkinter import ttk, messagebox
from controller.tool_controller import ToolController

class FrmTool:
    def __init__(self, root, controller: ToolController):
        self.controller = controller
        self.root = root
        self.root.title("Tools Management System")  
        self.root.geometry("1000x600")
        self._setup_ui()
        self.load_list()

    def _setup_ui(self):
        form_frame = ttk.LabelFrame(self.root, text="Tool Details", padding=10)
        form_frame.pack(fill="x", padx=10, pady=5)

        ttk.Label(form_frame, text="ID:").grid(row=0, column=0, padx=5)
        self.ent_id = ttk.Entry(form_frame)
        self.ent_id.grid(row=0, column=1, padx=5)

        ttk.Button(form_frame, text="Find", command=self.on_search).grid(row=0, column=2)

        ttk.Label(form_frame, text="Description:").grid(row=0, column=3, padx=5)
        self.ent_desc = ttk.Entry(form_frame)
        self.ent_desc.grid(row=0, column=4, padx=5)

        ttk.Label(form_frame, text="Base Price ($):").grid(row=0, column=5, padx=5)
        self.ent_price = ttk.Entry(form_frame)
        self.ent_price.grid(row=0, column=6, padx=5)
        
        ttk.Label(form_frame, text="Stock:").grid(row=0, column=7, padx=5)
        self.ent_stock = ttk.Entry(form_frame)
        self.ent_stock.grid(row=0, column=8, padx=5)

        btn_box = ttk.Frame(form_frame)
        btn_box.grid(row=1, column=0, columnspan=9, pady=15)
        
        ttk.Button(btn_box, text="Save New", command=self.on_save).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Update", command=self.on_update).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Delete", command=self.on_delete).pack(side="left", padx=5)
        ttk.Button(btn_box, text="Clear Form", command=self.clear_form).pack(side="left", padx=5)

        list_frame = ttk.LabelFrame(self.root, text="Inventory", padding=10)
        list_frame.pack(fill="both", expand=True, padx=10, pady=5)
        
        tool_bar = ttk.Frame(list_frame)
        tool_bar.pack(fill="x", pady=5)
        ttk.Button(tool_bar, text="Calculate Total Inventory", command=self.on_calc_total).pack(side="right")

        cols = ("id", "desc", "base", "total", "stock")
        self.tree = ttk.Treeview(list_frame, columns=cols, show="headings")
        self.tree.heading("id", text="ID")
        self.tree.heading("desc", text="Description")
        self.tree.heading("base", text="Base Price")
        self.tree.heading("total", text="Total w/ Tax")
        self.tree.heading("stock", text="Stock")
        
        self.tree.column("id", width=80)
        self.tree.column("base", width=100)
        self.tree.column("total", width=100)
        self.tree.column("stock", width=80)
        
        self.tree.bind("<Double-1>", self.on_select_item)
        self.tree.pack(fill="both", expand=True)

    # --- Events ---
    def on_save(self):
        try:
            p_id, desc, price, stock = self._get_form_data()
            if self.controller.create_tool(p_id, desc, price, stock):
                messagebox.showinfo("Success", "Tool Saved")
                self.clear_form()
                self.load_list()
            else:
                messagebox.showerror("Error", "Could not save (Duplicate ID?)")
        except ValueError:
            messagebox.showwarning("Error", "Check numeric fields")

    def on_update(self):
        try:
            p_id, desc, price, stock = self._get_form_data()
            if self.controller.update_tool(p_id, desc, price, stock):
                messagebox.showinfo("Success", "Tool Updated")
                self.clear_form()
                self.load_list()
            else:
                messagebox.showerror("Error", "ID not found")
        except ValueError: pass

    def on_delete(self):
        p_id = self.ent_id.get()
        if not p_id: return
        if messagebox.askyesno("Confirm", f"Delete ID {p_id}?"):
            if self.controller.delete_tool(p_id):
                messagebox.showinfo("Deleted", "Record deleted")
                self.clear_form()
                self.load_list()
            else:
                messagebox.showerror("Error", "Could not delete")

    def on_search(self):
        p_id = self.ent_id.get()
        tool = self.controller.find_tool(p_id)
        if tool:
            self._fill_form(tool.id, tool.description, tool.base_price, tool.stock)
        else:
            messagebox.showinfo("Info", "Not Found")

    def on_calc_total(self):
        count, base, total = self.controller.calculate_inventory_value()
        msg = f"Items: {count}\nTotal Base: ${base:.2f}\nTotal w/ Tax: ${total:.2f}"
        messagebox.showinfo("Financial Summary", msg)

    def load_list(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        tools = self.controller.get_all_tools()
        for t in tools:
            self.tree.insert("", "end", values=(t.id, t.description, t.base_price, t.total_with_tax, t.stock))

    def _get_form_data(self):
        p_id = self.ent_id.get()
        desc = self.ent_desc.get()
        price_txt = self.ent_price.get()
        stock_txt = self.ent_stock.get()
        
        if not p_id: raise ValueError
        price = float(price_txt) if price_txt else 0.0
        stock = int(stock_txt) if stock_txt else 0
        
        return p_id, desc, price, stock

    def _fill_form(self, t_id, t_desc, t_price, t_stock):
        self.clear_form()
        self.ent_id.insert(0, t_id)
        self.ent_desc.insert(0, t_desc)
        self.ent_price.insert(0, t_price)
        self.ent_stock.insert(0, t_stock)

    def clear_form(self):
        self.ent_id.delete(0, tk.END)
        self.ent_desc.delete(0, tk.END)
        self.ent_price.delete(0, tk.END)
        self.ent_stock.delete(0, tk.END)

    def on_select_item(self, event):
        sel = self.tree.selection()
        if sel:
            vals = self.tree.item(sel[0])['values']
            self._fill_form(str(vals[0]), vals[1], vals[2], vals[4])