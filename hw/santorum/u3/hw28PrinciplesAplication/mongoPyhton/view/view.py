import tkinter as tk
from tkinter import messagebox, ttk
from controller.MongoCrud import MongoCrud
from model.Toy import Toy


class View:

    def __init__(self):
        self.mongo = MongoCrud()

        self.root = tk.Tk()
        self.root.title("Toy Shop - CRUD")
        self.root.geometry("420x420")
        self.root.resizable(False, False)

        # ===== FORM =====
        tk.Label(self.root, text="Toy ID:").grid(row=0, column=0, padx=10, pady=5, sticky="e")
        tk.Label(self.root, text="Name:").grid(row=1, column=0, padx=10, pady=5, sticky="e")
        tk.Label(self.root, text="Price:").grid(row=2, column=0, padx=10, pady=5, sticky="e")
        tk.Label(self.root, text="Price IVA:").grid(row=3, column=0, padx=10, pady=5, sticky="e")

        self.txt_id = tk.Entry(self.root)
        self.txt_name = tk.Entry(self.root)
        self.txt_price = tk.Entry(self.root)
        self.txt_price_iva = tk.Entry(self.root, state="disabled")

        self.txt_id.grid(row=0, column=1, padx=5, pady=5)
        self.txt_name.grid(row=1, column=1, padx=5, pady=5)
        self.txt_price.grid(row=2, column=1, padx=5, pady=5)
        self.txt_price_iva.grid(row=3, column=1, padx=5, pady=5)

        # ===== BUTTONS =====
        tk.Button(self.root, text="Save", width=12, command=self.save)\
            .grid(row=4, column=0, padx=10, pady=10)

        tk.Button(self.root, text="Find", width=12, command=self.find)\
            .grid(row=4, column=1, padx=10, pady=10)

        tk.Button(self.root, text="Update", width=12, command=self.update)\
            .grid(row=5, column=0, padx=10, pady=5)

        tk.Button(self.root, text="Delete", width=12, command=self.delete)\
            .grid(row=5, column=1, padx=10, pady=5)

        tk.Button(self.root, text="Load All", width=26, command=self.load_table)\
            .grid(row=6, column=0, columnspan=2, pady=10)

        # ===== TABLE =====
        columns = ("ID", "Name", "Price", "IVA")
        self.table = ttk.Treeview(self.root, columns=columns, show="headings", height=8)

        self.table.heading("ID", text="ID")
        self.table.heading("Name", text="Name")
        self.table.heading("Price", text="Price")
        self.table.heading("IVA", text="Price IVA")

        self.table.column("ID", width=60, anchor="center")
        self.table.column("Name", width=150)
        self.table.column("Price", width=80, anchor="e")
        self.table.column("IVA", width=80, anchor="e")

        self.table.grid(row=7, column=0, columnspan=2, padx=10, pady=5)

        self.root.mainloop()

    # ===== METHODS =====
    def save(self):
        try:
            toy = Toy(
                int(self.txt_id.get()),
                self.txt_name.get(),
                float(self.txt_price.get())
            )
            self.mongo.create(toy)

            self._set_price_iva(toy.calculate_price_iva())
            messagebox.showinfo("OK", "Toy saved successfully")

        except ValueError:
            messagebox.showerror("Error", "Invalid data")

    def find(self):
        self.clear_table()

        try:
            toy = self.mongo.read_by_id(int(self.txt_id.get()))
            if toy:
                self.txt_name.delete(0, tk.END)
                self.txt_price.delete(0, tk.END)

                self.txt_name.insert(0, toy.name)
                self.txt_price.insert(0, toy.price)
                self._set_price_iva(toy.price_iva)

                self.table.insert("", tk.END, values=(
                    toy.id,
                    toy.name,
                    f"{toy.price:.2f}",
                    f"{toy.price_iva:.2f}"
                ))
            else:
                messagebox.showwarning("Not found", "Toy not found")

        except ValueError:
            messagebox.showerror("Error", "Invalid ID")

    def update(self):
        try:
            toy = Toy(
                int(self.txt_id.get()),
                self.txt_name.get(),
                float(self.txt_price.get())
            )
            self.mongo.update(toy)
            self._set_price_iva(toy.calculate_price_iva())
            messagebox.showinfo("OK", "Toy updated successfully")

        except ValueError:
            messagebox.showerror("Error", "Invalid data")

    def delete(self):
        try:
            self.mongo.delete(int(self.txt_id.get()))
            self.clear_form()
            messagebox.showinfo("OK", "Toy deleted successfully")

        except ValueError:
            messagebox.showerror("Error", "Invalid ID")

    def load_table(self):
        self.clear_table()
        toys = self.mongo.read_all()

        for toy in toys:
            self.table.insert("", tk.END, values=(
                toy.id,
                toy.name,
                f"{toy.price:.2f}",
                f"{toy.price_iva:.2f}"
            ))


    def clear_table(self):
        for row in self.table.get_children():
            self.table.delete(row)

    def clear_form(self):
        self.txt_id.delete(0, tk.END)
        self.txt_name.delete(0, tk.END)
        self.txt_price.delete(0, tk.END)
        self._set_price_iva("")

    def _set_price_iva(self, value):
        self.txt_price_iva.config(state="normal")
        self.txt_price_iva.delete(0, tk.END)
        self.txt_price_iva.insert(0, value)
        self.txt_price_iva.config(state="disabled")


if __name__ == "__main__":
    View()
