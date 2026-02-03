from tkinter import *
from tkinter.ttk import Treeview
from tkinter import messagebox
from pymongo import MongoClient
from reportlab.platypus import SimpleDocTemplate, Paragraph, Table
from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib.pagesizes import A4
MONGO_URI = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/?retryWrites=true&w=majority"
DB_NAME = "factura_db"
COLLECTION_NAME = "productos"
IVA = 0.15
producto_id = None

def calcular():
    try:
        precio = float(entry_price.get())
        cantidad = int(entry_quantity.get())

        subtotal = precio * cantidad
        iva = subtotal * IVA
        total = subtotal + iva

        entry_subtotal.delete(0, END)
        entry_iva.delete(0, END)
        entry_total.delete(0, END)

        entry_subtotal.insert(0, f"{subtotal:.2f}")
        entry_iva.insert(0, f"{iva:.2f}")
        entry_total.insert(0, f"{total:.2f}")

    except ValueError:
        messagebox.showerror("Error", "Cantidad y precio deben ser numéricos")

def guardar():
    global producto_id

    if not entry_name.get() or not entry_quantity.get() or not entry_price.get():
        messagebox.showerror("Error", "Complete todos los campos")
        return

    producto = {
        "name": entry_name.get(),
        "quantity": int(entry_quantity.get()),
        "price": float(entry_price.get()),
        "subtotal": float(entry_subtotal.get()),
        "iva": float(entry_iva.get()),
        "total": float(entry_total.get())
    }

    cliente = MongoClient(MONGO_URI)
    coleccion = cliente[DB_NAME][COLLECTION_NAME]

    if producto_id is None:
        coleccion.insert_one(producto)
    else:
        coleccion.update_one({"_id": producto_id}, {"$set": producto})

    cliente.close()
    cargar()
    cancelar()

def cargar():
    global total_general
    total_general = 0

    cliente = MongoClient(MONGO_URI)
    coleccion = cliente[DB_NAME][COLLECTION_NAME]

    for fila in tabla.get_children():
        tabla.delete(fila)

    for prod in coleccion.find():
        tabla.insert(
            "", END,
            iid=str(prod["_id"]),
            values=(
                prod["name"],
                prod["quantity"],
                prod["price"],
                prod["subtotal"],
                prod["iva"],
                prod["total"]
            )
        )
        total_general += prod["total"]

    label_total.config(text=f"TOTAL FACTURA: ${total_general:.2f}")
    cliente.close()

def seleccionar(event):
    global producto_id
    item = tabla.selection()
    if item:
        producto_id = item[0]
        valores = tabla.item(item, "values")

        entry_name.delete(0, END)
        entry_quantity.delete(0, END)
        entry_price.delete(0, END)
        entry_subtotal.delete(0, END)
        entry_iva.delete(0, END)
        entry_total.delete(0, END)

        entry_name.insert(0, valores[0])
        entry_quantity.insert(0, valores[1])
        entry_price.insert(0, valores[2])
        entry_subtotal.insert(0, valores[3])
        entry_iva.insert(0, valores[4])
        entry_total.insert(0, valores[5])

def eliminar():
    global producto_id

    if not producto_id:
        messagebox.showerror("Error", "Seleccione un producto")
        return

    cliente = MongoClient(MONGO_URI)
    coleccion = cliente[DB_NAME][COLLECTION_NAME]
    coleccion.delete_one({"_id": producto_id})
    cliente.close()

    cargar()
    cancelar()

def exportar_pdf():
    cliente = MongoClient(MONGO_URI)
    coleccion = cliente[DB_NAME][COLLECTION_NAME]

    doc = SimpleDocTemplate("factura.pdf", pagesize=A4)
    styles = getSampleStyleSheet()
    elementos = [Paragraph("FACTURA DE PRODUCTOS", styles["Title"])]

    data = [["Producto", "Cantidad", "Precio", "Subtotal", "IVA", "Total"]]
    total = 0

    for prod in coleccion.find():
        data.append([
            prod["name"],
            prod["quantity"],
            prod["price"],
            prod["subtotal"],
            prod["iva"],
            prod["total"]
        ])
        total += prod["total"]

    data.append(["", "", "", "", "TOTAL", f"${total:.2f}"])
    elementos.append(Table(data))
    doc.build(elementos)

    cliente.close()
    messagebox.showinfo("PDF", "Factura exportada como factura.pdf")

def cancelar():
    global producto_id
    producto_id = None
    for entry in (entry_name, entry_quantity, entry_price, entry_subtotal, entry_iva, entry_total):
        entry.delete(0, END)

root = Tk()
root.title("Factura Productos")
root.geometry("950x600")

Label(root, text="Producto").grid(row=0, column=0)
entry_name = Entry(root)
entry_name.grid(row=0, column=1)

Label(root, text="Cantidad").grid(row=1, column=0)
entry_quantity = Entry(root)
entry_quantity.grid(row=1, column=1)

Label(root, text="Precio").grid(row=2, column=0)
entry_price = Entry(root)
entry_price.grid(row=2, column=1)

Button(root, text="Calcular", command=calcular).grid(row=3, column=1)

Label(root, text="Subtotal").grid(row=4, column=0)
entry_subtotal = Entry(root)
entry_subtotal.grid(row=4, column=1)

Label(root, text="IVA").grid(row=5, column=0)
entry_iva = Entry(root)
entry_iva.grid(row=5, column=1)

Label(root, text="Total").grid(row=6, column=0)
entry_total = Entry(root)
entry_total.grid(row=6, column=1)

Button(root, text="Guardar", command=guardar).grid(row=7, column=0)
Button(root, text="Eliminar", command=eliminar).grid(row=7, column=1)
Button(root, text="Cancelar", command=cancelar).grid(row=7, column=2)
Button(root, text="Mostrar", command=cargar).grid(row=7, column=3)
Button(root, text="Exportar PDF", command=exportar_pdf).grid(row=7, column=4)

tabla = Treeview(
    root,
    columns=("Producto", "Cantidad", "Precio", "Subtotal", "IVA", "Total"),
    show="headings"
)

for col in ("Producto", "Cantidad", "Precio", "Subtotal", "IVA", "Total"):
    tabla.heading(col, text=col)

tabla.grid(row=8, column=0, columnspan=5, pady=15)
tabla.bind("<<TreeviewSelect>>", seleccionar)

label_total = Label(root, text="TOTAL FACTURA: $0.00", font=("Arial", 12, "bold"))
label_total.grid(row=9, column=0, columnspan=5)

root.mainloop()
