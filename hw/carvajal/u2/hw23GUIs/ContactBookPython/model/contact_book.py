import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry


# ============================================================
#   CLASE CONTACT (Igual que en Java pero en Python)
# ============================================================
class Contact:
    def __init__(self, id, first_name, last_name, birth_date, age, contact_type, sex, hobbies, comments):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.birth_date = birth_date
        self.age = age
        self.contact_type = contact_type
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def __str__(self):
        return f"{self.id} - {self.first_name} {self.last_name}"


# ============================================================
#   VENTANA PRINCIPAL
# ============================================================
class ContactBookGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Contact Book")
        self.root.geometry("780x550")
        self.root.resizable(False, False)

        # Lista de contactos (prototipo igual que en Java)
        self.contact_list = []

        # ===================== UI =============================
        self.create_widgets()

    def create_widgets(self):
        title = tk.Label(self.root, text="CONTACTS", font=("Arial", 20))
        title.pack(pady=10)

        frame = tk.Frame(self.root)
        frame.pack()

        # ------- Labels y Entrys --------
        tk.Label(frame, text="id:").grid(row=0, column=0, sticky="w")
        self.id_entry = tk.Entry(frame)
        self.id_entry.grid(row=0, column=1)

        tk.Label(frame, text="First Name:").grid(row=1, column=0, sticky="w")
        self.first_name_entry = tk.Entry(frame)
        self.first_name_entry.grid(row=1, column=1)

        tk.Label(frame, text="Last Name:").grid(row=2, column=0, sticky="w")
        self.last_name_entry = tk.Entry(frame)
        self.last_name_entry.grid(row=2, column=1)

        tk.Label(frame, text="Birth Date:").grid(row=3, column=0, sticky="w")
        self.birth_calendar = DateEntry(frame, date_pattern="dd/mm/yyyy")
        self.birth_calendar.grid(row=3, column=1)

        tk.Label(frame, text="Age:").grid(row=4, column=0, sticky="w")
        self.age_entry = tk.Entry(frame)
        self.age_entry.grid(row=4, column=1)

        tk.Label(frame, text="Type:").grid(row=5, column=0, sticky="w")
        self.type_combo = ttk.Combobox(frame, values=["Family", "Friend", "Job", "Unknown"])
        self.type_combo.current(0)
        self.type_combo.grid(row=5, column=1)

        # -------- Comentarios --------
        tk.Label(frame, text="Comments:").grid(row=0, column=2, sticky="nw")
        self.comments_text = tk.Text(frame, width=40, height=10)
        self.comments_text.grid(row=0, column=3, rowspan=6, padx=10)

        # -------- Sexo (Radio buttons) --------
        tk.Label(frame, text="Sex:").grid(row=6, column=0, sticky="w")
        self.sex_var = tk.StringVar()
        tk.Radiobutton(frame, text="Male", value="Male", variable=self.sex_var).grid(row=6, column=1, sticky="w")
        tk.Radiobutton(frame, text="Female", value="Female", variable=self.sex_var).grid(row=7, column=1, sticky="w")

        # -------- Hobbies (Listbox) --------
        tk.Label(frame, text="Hobbies:").grid(row=8, column=0, sticky="nw")
        self.hobby_list = tk.Listbox(frame, selectmode=tk.MULTIPLE, height=7)

        hobbies = ["Play soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for h in hobbies:
            self.hobby_list.insert(tk.END, h)

        self.hobby_list.grid(row=8, column=1)

        # -------- Botón Save --------
        save_button = tk.Button(self.root, text="Save", width=10, command=self.save_contact)
        save_button.pack(pady=20)

    # ========================================================
    #       GUARDAR CONTACTO (prototipo igual que Java)
    # ========================================================
    def save_contact(self):
        try:
            id = int(self.id_entry.get())
            first = self.first_name_entry.get()
            last = self.last_name_entry.get()
            birth = self.birth_calendar.get()
            age = int(self.age_entry.get())
            type = self.type_combo.get()
            sex = self.sex_var.get()
            comments = self.comments_text.get("1.0", tk.END).strip()

            hobbies = []
            selected = self.hobby_list.curselection()
            for i in selected:
                hobbies.append(self.hobby_list.get(i))

            # Crear objeto Contact
            contact = Contact(id, first, last, birth, age, type, sex, hobbies, comments)
            self.contact_list.append(contact)

            messagebox.showinfo("Success", "Contacto guardado correctamente")

            # Mostrar en consola para verificar
            print("Contactos guardados:")
            for c in self.contact_list:
                print(c)

        except Exception as e:
            messagebox.showerror("Error", f"Ocurrió un error: {e}")


# ============================================================
#   MAIN
# ============================================================
if __name__ == "__main__":
    root = tk.Tk()
    app = ContactBookGUI(root)
    root.mainloop()
