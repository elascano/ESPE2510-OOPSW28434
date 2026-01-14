import tkinter as tk

# Importamos las clases correctas
from model.Computer import ComputerModel
from controller.ComputerController import ComputerController
from view.FrmComputers import ComputersView

if __name__ == "__main__":
    root = tk.Tk()

    try:
        model = ComputerModel()
        controller = ComputerController(model)
        app = ComputersView(root, controller)

        root.mainloop()
    except Exception as e:
        print(f"Critical Error at Launch: {e}")
