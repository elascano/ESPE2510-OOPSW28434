# view/main_window.py
import tkinter as tk
from tkinter import ttk, messagebox
from view.add_window import AddWindow

class MainWindow:
    def __init__(self, root):
        self.root = root
        self.root.title("Sistema de Contactos")
        self.root.geometry("400x500")
        self.root.configure(bg="#f0f0f0")
        
        # Centrar ventana
        self.center_window(400, 500)
        
        self.setup_ui()
    
    def center_window(self, width, height):
        # Obtener dimensiones de la pantalla
        screen_width = self.root.winfo_screenwidth()
        screen_height = self.root.winfo_screenheight()
        
        # Calcular posición
        x = (screen_width // 2) - (width // 2)
        y = (screen_height // 2) - (height // 2)
        
        self.root.geometry(f"{width}x{height}+{x}+{y}")
    
    def setup_ui(self):
        # Frame principal
        main_frame = tk.Frame(self.root, bg="#f0f0f0", padx=20, pady=20)
        main_frame.pack(expand=True, fill="both")
        
        # Título
        title_label = tk.Label(
            main_frame,
            text="⚽ SISTEMA DE CONTACTOS",
            font=("Arial", 16, "bold"),
            bg="#f0f0f0",
            fg="#2c3e50"
        )
        title_label.pack(pady=(0, 30))
        
        # Frame para botones
        button_frame = tk.Frame(main_frame, bg="#f0f0f0")
        button_frame.pack(expand=True)
        
        # Botones de operaciones CRUD
        button_configs = [
            ("➕ Agregar Contacto", "#2ecc71", self.open_add_window),
            ("🚪 Salir", "#95a5a6", self.exit_app)
        ]
        
        for text, color, command in button_configs:
            btn = tk.Button(
                button_frame,
                text=text,
                font=("Arial", 12),
                bg=color,
                fg="white",
                width=20,
                height=2,
                relief="raised",
                cursor="hand2",
                command=command
            )
            btn.pack(pady=10, fill="x")
            
            # Efecto hover
            btn.bind("<Enter>", lambda e, b=btn, c=color: 
                    b.config(bg=self.darken_color(c)))
            btn.bind("<Leave>", lambda e, b=btn, c=color: 
                    b.config(bg=c))
    
    def darken_color(self, color):
        # Oscurecer color para efecto hover
        colors = {
            "#2ecc71": "#27ae60",  # Verde
            "#3498db": "#2980b9",  # Azul
            "#f39c12": "#d35400",  # Naranja
            "#e74c3c": "#c0392b",  # Rojo
            "#9b59b6": "#8e44ad",  # Púrpura
            "#95a5a6": "#7f8c8d"   # Gris
        }
        return colors.get(color, color)
    
    def open_add_window(self):
        AddWindow(self.root)
    
    
    def show_statistics(self):
        from controller.contact_controller import ContactController
        
        controller = ContactController()
        result = controller.get_statistics()
        
        if result["success"]:
            messagebox.showinfo("📊 Estadísticas", result["message"])
        else:
            messagebox.showerror("Error", result["message"])
    
    def exit_app(self):
        from utils.mongodb_connection import MongoDBConnection
        
        if messagebox.askyesno("Salir", "¿Está seguro de salir del sistema?"):
            MongoDBConnection.close_connection()
            self.root.quit()