# frm_contacts.py

import json
from datetime import datetime, date
import tkinter as tk
from tkinter import messagebox

from contact import Contact
from mongo_util import get_contacts_collection


class FrmContactsApp:

    def __init__(self, root):
        self.root = root
        self.root.title("Contacts")

        self.contact = None

        # ====== VARIABLES DE TKINTER ======
        self.first_name_var = tk.StringVar()
        self.last_name_var = tk.StringVar()
        self.birth_date_var = tk.StringVar()
        self.age_var = tk.StringVar()
        self.type_var = tk.StringVar(value="Family")
        self.sex_var = tk.StringVar(value="Female")  # Default

        # ====== FRAME PRINCIPAL ======
        main_frame = tk.Frame(root, padx=10, pady=10)
        main_frame.pack(fill="both", expand=True)

        # Título
        title_label = tk.Label(main_frame, text="CONTACTS", font=("Segoe UI", 20))
        title_label.grid(row=0, column=0, columnspan=4, pady=(0, 15))

        # ====== Fila 1: First Name, Last Name ======
        tk.Label(main_frame, text="First Name:").grid(row=1, column=0, sticky="e")
        tk.Entry(main_frame, textvariable=self.first_name_var, width=25)\
            .grid(row=1, column=1, padx=5, pady=3)

        tk.Label(main_frame, text="Last Name:").grid(row=2, column=0, sticky="e")
        tk.Entry(main_frame, textvariable=self.last_name_var, width=25)\
            .grid(row=2, column=1, padx=5, pady=3)

        # ====== Fila 2: Fecha Nacimiento, Edad ======
        tk.Label(main_frame, text="Birth Date (dd/mm/yyyy):")\
            .grid(row=3, column=0, sticky="e")
        tk.Entry(main_frame, textvariable=self.birth_date_var, width=25)\
            .grid(row=3, column=1, padx=5, pady=3)

        tk.Label(main_frame, text="Age:").grid(row=4, column=0, sticky="e")
        age_entry = tk.Entry(main_frame, textvariable=self.age_var, width=10, state="readonly")
        age_entry.grid(row=4, column=1, sticky="w", padx=5, pady=3)

        # ====== Tipo de contacto ======
        tk.Label(main_frame, text="Type:").grid(row=5, column=0, sticky="e")

        type_options = ["Family", "Friend", "Job", "Unknown"]
        type_menu = tk.OptionMenu(main_frame, self.type_var, *type_options)
        type_menu.config(width=10)
        type_menu.grid(row=5, column=1, sticky="w", padx=5, pady=3)

        # ====== Sexo (Radio Buttons) ======
        tk.Label(main_frame, text="Sex:").grid(row=6, column=0, sticky="e")
        rb_male = tk.Radiobutton(main_frame, text="Male", variable=self.sex_var, value="Male")
        rb_female = tk.Radiobutton(main_frame, text="Female", variable=self.sex_var, value="Female")

        rb_male.grid(row=6, column=1, sticky="w", padx=5, pady=3)
        rb_female.grid(row=6, column=1, sticky="w", padx=70, pady=3)

        # ====== Hobbies (Listbox) ======
        tk.Label(main_frame, text="Hobbies:").grid(row=7, column=0, sticky="ne")
        hobbies_frame = tk.Frame(main_frame)
        hobbies_frame.grid(row=7, column=1, sticky="w", padx=5, pady=3)

        self.hobbies_listbox = tk.Listbox(hobbies_frame, height=6, exportselection=False)
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for h in hobbies:
            self.hobbies_listbox.insert(tk.END, h)
        self.hobbies_listbox.grid(row=0, column=0)

        # ====== Comments (Text area) ======
        tk.Label(main_frame, text="Comments:").grid(row=1, column=2, sticky="nw", padx=(20, 0))
        self.comments_text = tk.Text(main_frame, width=35, height=10)
        self.comments_text.grid(row=1, column=3, rowspan=4, padx=5, pady=3)

        # ====== Botón Save ======
        save_button = tk.Button(main_frame, text="Save", command=self.on_save_clicked, width=10)
        save_button.grid(row=8, column=0, columnspan=4, pady=15)

        # Poner foco en el primer campo
        self.first_name_entry_focus()

    # ------- UTILIDADES GUI -------

    def first_name_entry_focus(self):
        # Buscar el Entry del first_name_var
        for widget in self.root.winfo_children():
            # No es necesario aquí, pero dejamos el método por claridad
            pass
        # Forma directa:
        self.root.after(100, lambda: self.root.focus_force())

    def clear_fields(self):
        self.first_name_var.set("")
        self.last_name_var.set("")
        self.birth_date_var.set("")
        self.age_var.set("")
        self.type_var.set("Family")
        self.sex_var.set("Female")
        self.hobbies_listbox.selection_clear(0, tk.END)
        self.comments_text.delete("1.0", tk.END)

    # ------- LÓGICA DE NEGOCIO -------

    def calculate_age(self, birth_date_str: str) -> int:
        """
        birth_date_str en formato dd/mm/yyyy
        """
        try:
            birth_date = datetime.strptime(birth_date_str, "%d/%m/%Y").date()
            today = date.today()
            years = today.year - birth_date.year - (
                (today.month, today.day) < (birth_date.month, birth_date.day)
            )
            return years
        except Exception:
            return 0

    def read_values(self):
        first_name = self.first_name_var.get().strip()
        last_name = self.last_name_var.get().strip()
        birth_date_str = self.birth_date_var.get().strip()

        age = self.calculate_age(birth_date_str)
        self.age_var.set(str(age))

        type_of_contact = self.type_var.get()
        sex = self.sex_var.get()

        hobbies = []
        selection = self.hobbies_listbox.curselection()
        if selection:
            hobbies.append(self.hobbies_listbox.get(selection[0]))

        comments = self.comments_text.get("1.0", tk.END).strip()

        self.contact = Contact(
            first_name=first_name,
            last_name=last_name,
            age=age,
            type_of_contact=type_of_contact,
            sex=sex,
            hobbies=hobbies,
            comments=comments
        )

    def save_contact_to_json(self, contact: Contact):
        try:
            data = {
                "id": contact.id,
                "firstName": contact.first_name,
                "lastName": contact.last_name,
                "age": contact.age,
                "typeOfContact": contact.type_of_contact,
                "sex": contact.sex,
                "hobbies": contact.hobbies,
                "comments": contact.comments
            }

            with open("contacts.json", "a", encoding="utf-8") as f:
                json.dump(data, f, ensure_ascii=False)
                f.write("\n")

            messagebox.showinfo("JSON", "Contacto guardado en contacts.json")
        except Exception as e:
            messagebox.showerror("Error JSON", f"Error al guardar en JSON: {e}")

    def save_contact_to_mongo(self, contact: Contact):
        try:
            collection = get_contacts_collection()
            doc = {
                "id": contact.id,
                "firstName": contact.first_name,
                "lastName": contact.last_name,
                "age": contact.age,
                "typeOfContact": contact.type_of_contact,
                "sex": contact.sex,
                "hobbies": contact.hobbies,
                "comments": contact.comments
            }
            collection.insert_one(doc)
            messagebox.showinfo("MongoDB", "Contacto guardado en MongoDB Atlas")
        except Exception as e:
            messagebox.showerror("Error MongoDB", f"Error al guardar en MongoDB: {e}")

    # ------- MANEJO DEL BOTÓN SAVE -------

    def on_save_clicked(self):
        self.read_values()

        if self.contact is None:
            messagebox.showwarning("Warning", "No hay datos para guardar")
            return

        # Diálogo tipo YES / NO / CANCEL
        result = messagebox.askyesnocancel(
            "SAVE CONTACTS?",
            f"saving contacts -->\n{self.contact}"
        )

        # YES
        if result is True:
            self.save_contact_to_json(self.contact)
            self.save_contact_to_mongo(self.contact)
            self.clear_fields()

        # NO
        elif result is False:
            confirm = messagebox.askyesno(
                "Confirmación",
                "¿Está seguro de que NO desea guardar?"
            )
            if confirm:
                self.root.destroy()
            else:
                # No borra nada, solo vuelve a la ventana
                pass

        # CANCEL (None)
        elif result is None:
            self.clear_fields()
            # Cursor implícitamente en la ventana; podrías gestionar foco
            # en el primer Entry si lo deseas con un ref directo


def main():
    root = tk.Tk()
    app = FrmContactsApp(root)
    root.mainloop()


if __name__ == "__main__":
    main()
