# view/add_window.py
import tkinter as tk
from tkinter import ttk, messagebox
from controller.contact_controller import ContactController

class AddWindow:
    def __init__(self, parent):
        self.parent = parent
        self.window = tk.Toplevel(parent)
        self.window.title("Agregar Nuevo Contacto")
        self.window.geometry("400x400")
        self.window.configure(bg="#f0f0f0")
        self.window.transient(parent)
        self.window.grab_set()
        
        # Centrar ventana
        self.center_window(400, 400)
        
        self.controller = ContactController()
        self.setup_ui()
    
    def center_window(self, width, height):
        screen_width = self.window.winfo_screenwidth()
        screen_height = self.window.winfo_screenheight()
        x = (screen_width // 2) - (width // 2)
        y = (screen_height // 2) - (height // 2)
        self.window.geometry(f"{width}x{height}+{x}+{y}")
    
    def setup_ui(self):
        # Frame principal
        main_frame = tk.Frame(self.window, bg="#f0f0f0", padx=20, pady=20)
        main_frame.pack(expand=True, fill="both")
        
        # Título
        title_label = tk.Label(
            main_frame,
            text="➕ AGREGAR NUEVO CONTACTO",
            font=("Arial", 14, "bold"),
            bg="#f0f0f0",
            fg="#2c3e50"
        )
        title_label.pack(pady=(0, 20))
        
        # Formulario
        form_frame = tk.Frame(main_frame, bg="#f0f0f0")
        form_frame.pack(fill="x", pady=10)
        
        # Campos del formulario
        fields = [
            ("Nombre:", "name"),
            ("Numero:", "phone"),
            ("Email:", "email"),
            ("Addres:", "addres")
        ]
        
        self.entries = {}
        
        for i, (label_text, field_name) in enumerate(fields):
            # Label
            label = tk.Label(
                form_frame,
                text=label_text,
                font=("Arial", 10),
                bg="#f0f0f0",
                anchor="w"
            )
            label.grid(row=i, column=0, sticky="w", pady=5)
            
            # Entry
            entry = tk.Entry(
                form_frame,
                font=("Arial", 10),
                width=30
            )
            entry.grid(row=i, column=1, pady=5, padx=(10, 0))
            
            self.entries[field_name] = entry
        
        # Frame para botones
        button_frame = tk.Frame(main_frame, bg="#f0f0f0")
        button_frame.pack(pady=20)
        
        # Botón Agregar
        add_btn = tk.Button(
            button_frame,
            text="Agregar Contacto",
            font=("Arial", 11, "bold"),
            bg="#2ecc71",
            fg="white",
            width=15,
            height=1,
            relief="raised",
            cursor="hand2",
            command=self.add_contact
        )
        add_btn.pack(side="left", padx=5)
        
        # Efectos hover
        for btn in [add_btn]:
            btn.bind("<Enter>", lambda e, b=btn: 
                    b.config(bg=self.darken_color(b.cget("bg"))))
            btn.bind("<Leave>", lambda e, b=btn, c=b.cget("bg"): 
                    b.config(bg=c))
    
    def darken_color(self, color):
        colors = {
            "#2ecc71": "#27ae60",
            "#95a5a6": "#7f8c8d",
            "#e74c3c": "#c0392b"
        }
        return colors.get(color, color)
    
    def add_contact(self):
        # Obtener valores de los campos
        name = self.entries["name"].get().strip()
        phone = self.entries["phone"].get().strip()
        email = self.entries["email"].get().strip()
        addres = self.entries["addres"].get().strip()
        
        # Validaciones básicas
        if not all([name, phone, email, addres]):
            messagebox.showerror("Error", "Todos los campos son requeridos")
            return
        
        try:
            number_of_players = int(addres)
            if number_of_players < 4 or number_of_players > 26:
                messagebox.showerror("Error", "El número de jugadores debe estar entre 4 y 26")
                return
        except ValueError:
            messagebox.showerror("Error", "Número de jugadores inválido")
            return
        
        # Confirmar
        if messagebox.askyesno("Confirmar", 
                              f"¿Agregar Contacto?\n\n"
                              f"Nombre: {name}\n"
                              f"Telefono: {phone}\n"
                              f"Email: {email}\n"
                              f"Direccion: {addres}"):
            
            # Llamar al controller
            result = self.controller.add_contact(name, phone, email, addres)
            
            if result["success"]:
                messagebox.showinfo("Éxito", result["message"])
                self.clear_fields()
                self.window.destroy()
            else:
                messagebox.showerror("Error", result["message"])
    
    def clear_fields(self):
        for entry in self.entries.values():
            entry.delete(0, tk.END)
        self.entries["name"].focus_set()