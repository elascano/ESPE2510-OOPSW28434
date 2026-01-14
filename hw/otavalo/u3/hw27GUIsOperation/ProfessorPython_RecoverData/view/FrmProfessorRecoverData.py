import tkinter as tk
from tkinter import ttk
import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from model.Professor import ProfessorModel
from controller.Professor_controller import ProfessorController

class FrmProfessorRecoverData(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Professor Data Recovery - RecoverData DB")
        self.geometry("850x550")
        self.configure(bg="#ffffff") 
        style = ttk.Style()
        style.theme_use("clam")
        style.configure("Treeview.Heading", background="#34495e", foreground="white", font=('Arial', 10, 'bold'))
        style.configure("Treeview", rowheight=25)

        self.lbl_title = tk.Label(
            self, 
            text="PROFESSOR RECOVERY & CALCULATION", 
            font=("Arial", 16, "bold"), 
            bg="#ffffff",
            fg="#2c3e50",
            pady=20
        )
        self.lbl_title.pack()

        self.btn_refresh = tk.Button(
            self, 
            text="🔄 REFRESH AND CALCULATE", 
            bg="#f8f9fa", 
            fg="#333", 
            font=("Arial", 9, "bold"),
            padx=20,
            pady=5,
            relief="groove"
        )
        self.btn_refresh.pack(pady=10)

        columns = ("id", "name", "subject", "salary", "bonus")
        self.tree = ttk.Treeview(self, columns=columns, show="headings")
        
        self.tree.heading("id", text="ID NUMBER")
        self.tree.heading("name", text="FULL NAME")
        self.tree.heading("subject", text="SUBJECT")
        self.tree.heading("salary", text="BASE SALARY")
        self.tree.heading("bonus", text="BONUS (15%)")
        
        self.tree.column("id", width=100, anchor="center")
        self.tree.column("name", width=220)
        self.tree.column("subject", width=150)
        self.tree.column("salary", width=120, anchor="e")
        self.tree.column("bonus", width=120, anchor="e")
        
        self.tree.pack(fill="both", expand=True, padx=30, pady=20)

if __name__ == "__main__":
    app = FrmProfessorRecoverData()
    model = ProfessorModel()
    controller = ProfessorController(model, app)
    
    app.btn_refresh.config(command=controller.refresh_table)

    controller.refresh_table()
    
    app.mainloop()