import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

root = tk.Tk()
root.title("Task")
root.geometry("800x500")
root.configure(bg="#cfd3db")

title = tk.Label(root, text="TAREA", font=("Arial", 20, "bold"), bg="#cfd3db")
title.pack(pady=10)

frame = tk.Frame(root, bg="#cfd3db")
frame.pack(pady=10)

tk.Label(frame, text="id:", bg="#cfd3db").grid(row=0, column=0, sticky="w", pady=5)

tk.Label(frame, text="Nombre:", bg="#cfd3db").grid(row=1, column=0, sticky="w", pady=5)
tk.Entry(frame, width=25).grid(row=1, column=1, pady=5)

tk.Label(frame, text="Dia de Creacion:", bg="#cfd3db").grid(row=2, column=0, sticky="w", pady=5)
DateEntry(frame, width=18).grid(row=2, column=1, sticky="w")

tk.Label(frame, text="Dia de Entrega:", bg="#cfd3db").grid(row=3, column=0, sticky="w", pady=5)
DateEntry(frame, width=18).grid(row=3, column=1, sticky="w")

tk.Label(frame, text="Estado:", bg="#cfd3db").grid(row=4, column=0, sticky="w", pady=5)
status_combo = ttk.Combobox(frame, values=["Pendiente", "Iniciado", "Terminado"], width=22)
status_combo.current(0)
status_combo.grid(row=4, column=1, sticky="w")

tk.Label(frame, text="Cliente:", bg="#cfd3db").grid(row=5, column=0, sticky="w", pady=5)
customer_combo = ttk.Combobox(frame, values=["Constructora Andes", "Innovatech Solutions", "AndinaCorp"], width=22)
customer_combo.current(0)
customer_combo.grid(row=5, column=1, sticky="w")

tk.Label(frame, text="Asiganar a:", bg="#cfd3db").grid(row=6, column=0, sticky="w", pady=5)
assign_combo = ttk.Combobox(frame, values=["Paulo Ramos", "Jouse Rojas", "Thais Santorum"], width=22)
assign_combo.current(0)
assign_combo.grid(row=6, column=1, sticky="w")

tk.Label(frame, text="Documento:", bg="#cfd3db").grid(row=0, column=3, sticky="w", pady=5)

doc_var = tk.StringVar(value="SI")
tk.Radiobutton(frame, text="SI", variable=doc_var, value="SI", bg="#cfd3db").grid(row=0, column=4, sticky="w")
tk.Radiobutton(frame, text="NO", variable=doc_var, value="NO", bg="#cfd3db").grid(row=0, column=5, sticky="w")

tk.Label(frame, text="Nombre:", bg="#cfd3db").grid(row=1, column=3, sticky="w", pady=5)
tk.Entry(frame, width=25).grid(row=1, column=4, columnspan=2, pady=5)

tk.Label(frame, text="Tipo de Documento:", bg="#cfd3db").grid(row=2, column=3, sticky="w", pady=5)
doc_combo = ttk.Combobox(frame, values=["Facturas", "Recibos", "Notas de Credito", "Estado Finaciero"], width=22)
doc_combo.current(0)
doc_combo.grid(row=2, column=4, columnspan=2, sticky="w")

tk.Label(frame, text="Dia de Revision:", bg="#cfd3db").grid(row=3, column=3, sticky="w", pady=5)
DateEntry(frame, width=18).grid(row=3, column=4, sticky="w")

tk.Label(frame, text="Detalles:", bg="#cfd3db").grid(row=4, column=3, sticky="nw", pady=5)
details_text = tk.Text(frame, width=40, height=10)
details_text.grid(row=4, column=4, columnspan=2, rowspan=4, pady=5)

tk.Button(root, text="Crear", width=12).pack(pady=20)

root.mainloop()

