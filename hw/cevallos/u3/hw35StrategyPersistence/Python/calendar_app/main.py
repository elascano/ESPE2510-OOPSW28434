import tkinter as tk
from view.calendar_gui import CalendarGUI
from utils.mongodb_connection import MongoDBConnection

def main():
    # Inicializar conexión a MongoDB (opcional)
    MongoDBConnection.initialize()
    
    # Crear ventana principal
    root = tk.Tk()
    
    # Crear y ejecutar la GUI
    app = CalendarGUI(root)
    app.run()
    
    # Cerrar conexión a MongoDB al salir
    MongoDBConnection.close_connection()

if __name__ == "__main__":
    main()