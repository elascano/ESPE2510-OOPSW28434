import tkinter as tk
from tkinter import ttk, messagebox
from pencil_controller import PencilController
from pencil import Pencil

class PencilView:
    def __init__(self, root):
        self.controller = PencilController()
        self.root = root
        self.root.title("Emily Calle - Pencils CRUD (Python)")

        tk.Label(root, text="ID:").grid(row=0, column=0)
        self.txt_id = tk.Entry(root)
        self.txt_id.grid(row=0, column=1)

        tk.Label(root, text="Brand:").grid(row=1, column=0)
        self.txt_brand = tk.Entry(root)
        self.txt_brand.grid(row=1, column=1)

        tk.Label(root, text="Color:").grid(row=2, column=0)
        self.txt_color = tk.Entry(root)
        self.txt_color.grid(row=2, column=1)

        tk.Label(root, text="Price:").grid(row=3, column=0)
        self.txt_price = tk.Entry(root)
        self.txt_price.grid(row=3, column=1)
        
        tk.Button(root, text="Guardar", command=self.save).grid(row=4, column=0)
        tk.Button(root, text="Actualizar", command=self.update).grid(row=4, column=1)
        tk.Button(root, text="Eliminar", command=self.delete).grid(row=4, column=2)

        self.tree = ttk.Treeview(root, columns=("ID", "Brand", "Color", "Price"), show='headings')
        self.tree.heading("ID", text="ID")
        self.tree.heading("Brand", text="Brand")
        self.tree.heading("Color", text="Color")
        self.tree.heading("Price", text="Price")
        self.tree.grid(row=5, column=0, columnspan=3)
        self.tree.bind("<ButtonRelease-1>", self.on_click)

        self.load_table()

    def load_table(self):
        for i in self.tree.get_children():
            self.tree.delete(i)
        for p in self.controller.read_all():
            self.tree.insert("", "end", values=(p['id'], p['brand'], p['color'], p['price']))

    def save(self):
        try:
            p = Pencil(self.txt_id.get(), self.txt_brand.get(), self.txt_color.get(), float(self.txt_price.get()))
            self.controller.create(p)
            self.load_table()
            messagebox.showinfo("Éxito", "Lápiz guardado")
        except ValueError:
            messagebox.showerror("Error", "Precio debe ser un número")

    def update(self):
        data = {
            "brand": self.txt_brand.get(),
            "color": self.txt_color.get(),
            "price": float(self.txt_price.get())
        }
        self.controller.update(self.txt_id.get(), data)
        self.load_table()

    def delete(self):
        self.controller.delete(self.txt_id.get())
        self.load_table()

    def on_click(self, event):
        item = self.tree.selection()[0]
        val = self.tree.item(item, "values")
        self.txt_id.delete(0, tk.END); self.txt_id.insert(0, val[0])
        self.txt_brand.delete(0, tk.END); self.txt_brand.insert(0, val[1])
        self.txt_color.delete(0, tk.END); self.txt_color.insert(0, val[2])
        self.txt_price.delete(0, tk.END); self.txt_price.insert(0, val[3])

if __name__ == "__main__":
    root = tk.Tk()
    app = PencilView(root)
    root.mainloop()