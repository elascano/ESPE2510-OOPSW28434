import tkinter as tk
from tkinter import ttk, messagebox
from controller.sculpture_controller import SculptureController # Import actualizado

# [SRP] La Vista SOLO se encarga de mostrar datos y capturar eventos.
# No calcula nada, solo delega al Controller.

class FrmView(tk.Tk):
    def __init__(self):
        super().__init__()
        self.controller = SculptureController()
        
        self.title("Sculpture Gallery Management")
        self.geometry("900x600")
        self.configure(bg="#E0E0E0")
        
        self.create_widgets()
        self.load_table_data()

    def create_widgets(self):
        main_frame = tk.Frame(self, bg="#E0E0E0")
        main_frame.pack(fill=tk.BOTH, expand=True, padx=20, pady=20)

        # --- FILA 1 ---
        row1 = tk.Frame(main_frame, bg="#E0E0E0")
        row1.pack(fill=tk.X, pady=5)

        tk.Label(row1, text="Name:", bg="#E0E0E0").pack(side=tk.LEFT)
        self.txt_name = tk.Entry(row1, width=20)
        self.txt_name.pack(side=tk.LEFT, padx=5)

        tk.Label(row1, text="Base Price ($):", bg="#E0E0E0").pack(side=tk.LEFT)
        self.txt_price = tk.Entry(row1, width=10)
        self.txt_price.pack(side=tk.LEFT, padx=5)

        tk.Label(row1, text="ID:", bg="#E0E0E0").pack(side=tk.LEFT)
        self.txt_id = tk.Entry(row1, width=15)
        self.txt_id.pack(side=tk.LEFT, padx=5)

        tk.Button(row1, text="Find", command=self.btn_find_action).pack(side=tk.LEFT, padx=10)

        # --- FILA 2 (MATERIALS) ---
        row2 = tk.Frame(main_frame, bg="#E0E0E0")
        row2.pack(fill=tk.X, pady=5)
        # Cambio de etiqueta visual
        tk.Label(row2, text="Materials (comma separated):", bg="#E0E0E0").pack(side=tk.LEFT)
        self.txt_materials = tk.Entry(row2, width=60)
        self.txt_materials.pack(side=tk.LEFT, padx=5)

        # --- BOTONES ---
        row3 = tk.Frame(main_frame, bg="#E0E0E0")
        row3.pack(fill=tk.X, pady=15)
        
        tk.Button(row3, text="Create New", bg="#4CAF50", fg="white", command=self.btn_create_action).pack(side=tk.LEFT, padx=5)
        tk.Button(row3, text="Update", bg="#2196F3", fg="white", command=self.btn_update_action).pack(side=tk.LEFT, padx=5)
        tk.Button(row3, text="Delete", bg="#F44336", fg="white", command=self.btn_delete_action).pack(side=tk.LEFT, padx=5)
        tk.Button(row3, text="Clear Form", command=self.btn_cancel_action).pack(side=tk.LEFT, padx=5)

        # --- TABLA ---
        tk.Label(main_frame, text="Sculpture Inventory:", bg="#E0E0E0", font=("Arial", 11, "bold")).pack(anchor="w", pady=(20, 5))
        
        columns = ("id", "name", "materials", "price", "price_iva")
        self.tree = ttk.Treeview(main_frame, columns=columns, show="headings")
        
        self.tree.heading("id", text="ID")
        self.tree.heading("name", text="Name")
        self.tree.heading("materials", text="Materials") # Columna actualizada
        self.tree.heading("price", text="Price ($)")
        self.tree.heading("price_iva", text="Price + IVA ($)")
        
        self.tree.column("id", width=80)
        self.tree.column("name", width=150)
        self.tree.column("materials", width=200)
        self.tree.column("price", width=80)
        self.tree.column("price_iva", width=80)

        scrollbar = ttk.Scrollbar(main_frame, orient=tk.VERTICAL, command=self.tree.yview)
        self.tree.configure(yscroll=scrollbar.set)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.tree.pack(fill=tk.BOTH, expand=True)

        self.tree.bind("<<TreeviewSelect>>", self.on_table_select)

    # --- [REUSABLE] HELPERS ---
# --- [REUSABLE] HELPER: Convierte Texto a Lista ---
    # Antes: parse_list. Ahora: nombre descriptivo.
    # Entrada: "  Oro ,  Plata, Bronce  "
    # Salida:  ["Oro", "Plata", "Bronce"]
    def convert_text_to_list(self, text_with_commas):
        # 1. Validación de seguridad: Si está vacío, devolvemos lista vacía
        if not text_with_commas:
            return []
        
        # 2. Dividir la cadena donde haya comas
        # "Oro , Plata" -> ["Oro ", " Plata"] (aún tiene espacios sucios)
        raw_items = text_with_commas.split(',')
        
        # 3. Limpieza (Algoritmo de filtrado)
        clean_list = []
        for item in raw_items:
            # .strip() elimina espacios al inicio y final
            clean_item = item.strip()
            
            # Solo agregamos si quedó texto (evita guardar vacíos si ponen ",,")
            if clean_item:
                clean_list.append(clean_item)
                
        return clean_list

    def clear_form(self):
        self.txt_id.delete(0, tk.END)
        self.txt_name.delete(0, tk.END)
        self.txt_price.delete(0, tk.END)
        self.txt_materials.delete(0, tk.END)
        for item in self.tree.selection():
            self.tree.selection_remove(item)

    def load_table_data(self, data_list=None):
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        if data_list is None:
            data_list = self.controller.get_all_sculptures()
            
        for s in data_list:
            # Convertimos la lista de materiales a texto para la tabla
            mat_str = ", ".join(s.materials)
            self.tree.insert("", tk.END, values=(s.id, s.name, mat_str, s.price, s.price_with_iva))

    # --- ACCIONES ---
    def on_table_select(self, event):
        selected_item = self.tree.selection()
        if not selected_item: return
        
        values = self.tree.item(selected_item[0])['values']
        
        self.txt_id.delete(0, tk.END); self.txt_id.insert(0, str(values[0]))
        self.txt_name.delete(0, tk.END); self.txt_name.insert(0, str(values[1]))
        self.txt_materials.delete(0, tk.END); self.txt_materials.insert(0, str(values[2]))
        self.txt_price.delete(0, tk.END); self.txt_price.insert(0, str(values[3]))

    def btn_create_action(self):
        try:
            id_val = self.txt_id.get()
            name = self.txt_name.get()
            price = float(self.txt_price.get())
            materials = self.convert_text_to_list(self.txt_materials.get())

            if self.controller.create_sculpture(id_val, name, price, materials):
                messagebox.showinfo("Success", "Sculpture Saved")
                self.clear_form()
                self.load_table_data()
            else:
                messagebox.showerror("Error", "Could not save (Check ID duplicate)")
        except ValueError:
            messagebox.showerror("Error", "Price must be a number")

    def btn_update_action(self):
        id_val = self.txt_id.get()
        if not id_val:
            messagebox.showwarning("Warning", "Select a sculpture to update.")
            return

        try:
            name = self.txt_name.get()
            price = float(self.txt_price.get())
            materials = self.convert_text_to_list(self.txt_materials.get())

            updated = self.controller.update_sculpture(id_val, name, price, materials)

            if updated:
                messagebox.showinfo("Success", "Sculpture Updated")
                self.load_table_data()
                self.clear_form()
            else:
                messagebox.showerror("Error", "Update Failed: ID not found.")
        except ValueError:
             messagebox.showerror("Error", "Price must be a number")

    def btn_delete_action(self):
        selected_item = self.tree.selection()
        if not selected_item:
            messagebox.showwarning("Warning", "Select a row to delete.")
            return

        values = self.tree.item(selected_item[0])['values']
        id_to_delete = str(values[0])

        if messagebox.askyesno("Confirm", f"Delete ID: {id_to_delete}?"):
            if self.controller.delete_sculpture(id_to_delete):
                messagebox.showinfo("Success", "Deleted from DB.")
                self.load_table_data()
                self.clear_form()
            else:
                messagebox.showerror("Error", "Delete Failed.")

    def btn_find_action(self):
        id_search = self.txt_id.get()
        if not id_search:
            self.load_table_data()
        else:
            found = self.controller.find_sculpture_by_id(id_search)
            if found:
                self.load_table_data([found])
            else:
                messagebox.showinfo("Info", "Not found")
                self.load_table_data()

    def btn_cancel_action(self):
        self.clear_form()
        self.load_table_data()