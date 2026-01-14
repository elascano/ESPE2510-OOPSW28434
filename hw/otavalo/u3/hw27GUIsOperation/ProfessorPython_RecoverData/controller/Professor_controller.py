from tkinter import messagebox

class ProfessorController:
    def __init__(self, model, view):
        self.model = model
        self.view = view

    def refresh_table(self):
        for item in self.view.tree.get_children():
            self.view.tree.delete(item)
        
        try:
            professors = self.model.get_all()

            for professor in professors:
                salary = float(professor.get("salary", 0)) 
                calculated_bonus = salary * 0.15   

                self.view.tree.insert("", "end", values=(
                    professor.get("id_number"), 
                    professor.get("name"), 
                    professor.get("subject"), 
                    f"${salary:.2f}",
                    f"${calculated_bonus:.2f}"
                ))
        except Exception as e:
            messagebox.showerror("Error", f"Could not recover data: {e}")