import tkinter as tk
from tkinter import ttk
import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from model.Professor import ProfessorModel
from controller.Professor_controller import ProfessorController

class FrmProfessor(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Professor Management System")
        self.geometry("600x500")
        
        
        self.lbl_title = tk.Label(
            self, 
            text="PROFESSOR SYSTEM", 
            font=("Arial", 18, "bold"), 
            pady=20
        )
        self.lbl_title.pack()

        # Entradas de texto
        tk.Label(self, text="Full Name:").pack()
        self.ent_name = tk.Entry(self, width=35)
        self.ent_name.pack(pady=5)

        tk.Label(self, text="ID Number:").pack()
        self.ent_id = tk.Entry(self, width=35)
        self.ent_id.pack(pady=5)

        tk.Label(self, text="Subject:").pack()
        self.combo_dept = ttk.Combobox(self, values=["Math", "Science", "History", "Arts", "IT"], state="readonly", width=32)
        self.combo_dept.pack(pady=5)

        tk.Label(self, text="Salary:").pack()
        self.ent_salary = tk.Entry(self, width=35)
        self.ent_salary.pack(pady=5)

        self.btn_save = tk.Button(self, text="Save & Update Table", bg="#28a745", fg="white", font=("Arial", 10, "bold"))
        self.btn_save.pack(pady=15)

        columns = ("id", "name", "dept", "bonus")
        self.tree = ttk.Treeview(self, columns=columns, show="headings")
        
        self.tree.heading("id", text="ID")
        self.tree.heading("name", text="Name")
        self.tree.heading("dept", text="Subject")
        self.tree.heading("bonus", text="Bonus (15%)")
        
        self.tree.column("id", width=50)
        self.tree.column("bonus", width=80, anchor="center")
        
        self.tree.pack(fill="both", expand=True, padx=10, pady=10)

if __name__ == "__main__":
    view = FrmProfessor()
    model = ProfessorModel()
    controller = ProfessorController(model, view)

    view.btn_save.config(command=controller.handle_save)
    
    controller.refresh_table()
    
    view.mainloop()