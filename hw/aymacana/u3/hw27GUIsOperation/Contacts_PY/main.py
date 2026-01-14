# main.py
import tkinter as tk
from view.main_window import MainWindow
from utils.mongodb_connection import MongoDBConnection

def main():
    try:
        # Conectar a MongoDB
        print("🚀 Iniciando Sistema de Equipos de Fútbol...")
        print("🔗 Conectando a MongoDB...")
        MongoDBConnection.get_connection()
        
        # Crear ventana principal
        root = tk.Tk()
        
        # Configurar tema (opcional)
        root.style = tk.ttk.Style()
        root.style.theme_use('clam')
        
        # Iniciar aplicación
        app = MainWindow(root)
        
        # Manejar cierre de ventana
        def on_closing():
            from tkinter import messagebox
            if messagebox.askyesno("Salir", "¿Está seguro de salir del sistema?"):
                MongoDBConnection.close_connection()
                root.destroy()
        
        root.protocol("WM_DELETE_WINDOW", on_closing)
        
        # Ejecutar loop principal
        root.mainloop()
        
    except Exception as e:
        print(f"❌ Error fatal: {e}")
        MongoDBConnection.close_connection()

if __name__ == "__main__":
    main()