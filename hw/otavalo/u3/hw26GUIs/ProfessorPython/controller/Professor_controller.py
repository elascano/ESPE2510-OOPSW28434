from tkinter import messagebox
import re  

class ProfessorController:
    def __init__(self, model, view):
        self.model = model
        self.view = view

    def handle_save(self):
        name = self.view.ent_name.get().strip()
        id_num = self.view.ent_id.get().strip()
        dept = self.view.combo_dept.get()
        salary_raw = self.view.ent_salary.get().strip()
        if not (name and id_num and dept and salary_raw):
            messagebox.showwarning("Warning", "All fields must be filled")
            return
        if not re.match(r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$", name):
            messagebox.showerror("Error", "Name must only contain letters, spaces, and accents")
            return

        if not (id_num.isdigit() and len(id_num) <= 5):
            messagebox.showerror("Error", "ID must be an integer with a maximum of 5 digits")
            return
        try:
            salary = float(salary_raw)
            if salary < 0:
                messagebox.showerror("Error", "Salary cannot be negative")
                return
        except ValueError:
            messagebox.showerror("Error", "Salary must be a valid number")
            return

        try:
            self.model.insert_professor(name, id_num, dept, salary)
            messagebox.showinfo("Success", "Professor saved and table updated!")
            self.refresh_table()
            self.clear_fields()
        except Exception as e:
            messagebox.showerror("Database Error", f"Could not save data: {e}")

    def refresh_table(self):
        for item in self.view.tree.get_children():
            self.view.tree.delete(item)

        for p in self.model.get_all():
            self.view.tree.insert("", "end", values=(
                p.get("id_number"), 
                p.get("name"), 
                p.get("subject"), 
                f"{p.get('bonus'):.2f}"
            ))

    def clear_fields(self):
        self.view.ent_name.delete(0, 'end')
        self.view.ent_id.delete(0, 'end')
        self.view.ent_salary.delete(0, 'end')
        self.view.combo_dept.set('')