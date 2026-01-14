import tkinter as tk
# Importamos las clases específicas de cada módulo
from model.Book import BookModel
from controller.BookController import BookController
from view.FrmLibrary import LibraryView

if __name__ == "__main__":
    root = tk.Tk()
    
    try:
        model = BookModel()
        controller = BookController(model)
        app = LibraryView(root, controller)
        
        root.mainloop()
    except Exception as e:
        print(f"Critical Error at Launch: {e}")