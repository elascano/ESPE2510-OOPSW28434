import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from pymongo import MongoClient
from datetime import date, datetime
import re
from bson.objectid import ObjectId

class FrmContactsVisual:
    def __init__(self, root):
        self.root = root
        self.root.title("Contacts Book")
        self.root.geometry("1000x700")
        MONGO_URI = 'mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/Contacts?retryWrites=true&w=majority'
        DB_NAME = 'Contacts'
        COLLECTION_NAME = 'ContactsBookPY'

        self.client = None
        self.db = None
        self.collection = None

        try:
            self.client = MongoClient(MONGO_URI)
            self.db = self.client[DB_NAME]
            self.collection = self.db[COLLECTION_NAME]
            self.client.admin.command('ping')
        except Exception as e:
            messagebox.showerror("Connection Error", f"Could not connect to MongoDB: {e}")
        self.contact_id_var = tk.StringVar(value="")
        self._id_mongo = None 

        self.create_widgets()
    def get_next_contact_id(self):
        """Finds the maximum 'contact_id' and returns the next one."""
        if self.client is None: return 1
        
        try:
            max_id_doc = self.collection.find_one(
                sort=[('contact_id', -1)]
            )
            if max_id_doc and 'contact_id' in max_id_doc:
                return max_id_doc['contact_id'] + 1
            else:
                return 1 
        except Exception as e:
            print(f"Error fetching max contact_id: {e}")
            messagebox.showerror("Database Error", "Failed to retrieve next contact ID.")
            return 1

    def is_valid_name(self, name):
        """Validates that the string only contains letters (including ñ, Ñ, and accents) and spaces."""
        return re.match(r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$", name) is not None

    def get_selected_hobbies(self):
        selected_indices = self.lst_hobbies.curselection()
        selected_hobbies = [self.lst_hobbies.get(i) for i in selected_indices]
        return selected_hobbies

    # GUI
    def create_widgets(self):
        lbl_title = tk.Label(self.root, text="CONTACTS BOOK", font=("Segoe UI", 24, "bold"))
        lbl_title.pack(pady=10)
        main_frame = tk.Frame(self.root)
        main_frame.pack(expand=True, fill="both", padx=30)
        form_frame = tk.LabelFrame(main_frame, text="Contact Details", padx=10, pady=10)
        form_frame.grid(row=0, column=0, padx=10, pady=10, sticky="n")
        tk.Label(form_frame, text="Contact ID:").grid(row=0, column=0, sticky="w", pady=5)
        self.contact_id_lbl = tk.Label(form_frame, textvariable=self.contact_id_var, fg="blue", font=("", 10, "bold"))
        self.contact_id_lbl.grid(row=0, column=1, sticky="w", pady=5)
        self.contact_id_var.set("New Contact")
        tk.Label(form_frame, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_first = tk.Entry(form_frame, width=25)
        self.txt_first.grid(row=1, column=1, sticky="w", pady=5)
        tk.Label(form_frame, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_last = tk.Entry(form_frame, width=25)
        self.txt_last.grid(row=2, column=1, sticky="w", pady=5)
        tk.Label(form_frame, text="Birth Date:").grid(row=3, column=0, sticky="w", pady=5)
        today = date.today()
        initial_date = date(today.year - 20, today.month, today.day)
        self.cal_date = DateEntry(form_frame, width=22, background='darkblue',foreground='white', borderwidth=2, date_pattern='dd/mm/yyyy',                               
                                 year=initial_date.year, month=initial_date.month, day=initial_date.day)
        self.cal_date.grid(row=3, column=1, sticky="w", pady=5)
        tk.Label(form_frame, text="Type:").grid(row=4, column=0, sticky="w", pady=5)
        self.cmb_type = ttk.Combobox(form_frame, values=["Family", "Friend", "Job", "Unknown"], state="readonly", width=22)
        self.cmb_type.current(0)
        self.cmb_type.grid(row=4, column=1, sticky="w", pady=5)
        tk.Label(form_frame, text="Sex:").grid(row=5, column=0, sticky="nw", pady=5)
        self.sex_var = tk.StringVar(value="M")
        frame_sex = tk.Frame(form_frame)
        frame_sex.grid(row=5, column=1, sticky="w")
        tk.Radiobutton(frame_sex, text="Male", variable=self.sex_var, value="M").pack(side=tk.LEFT, padx=5)
        tk.Radiobutton(frame_sex, text="Female", variable=self.sex_var, value="F").pack(side=tk.LEFT, padx=5)
        tk.Label(form_frame, text="Hobbies:").grid(row=6, column=0, sticky="nw", pady=5)
        frame_hobbies = tk.Frame(form_frame)
        frame_hobbies.grid(row=6, column=1, sticky="w")
        scrollbar = tk.Scrollbar(frame_hobbies)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.lst_hobbies = tk.Listbox(frame_hobbies, height=4, width=25,selectmode=tk.MULTIPLE, yscrollcommand=scrollbar.set)                                   
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an Instrument"]
        for h in hobbies:
            self.lst_hobbies.insert(tk.END, h)
        self.lst_hobbies.pack(side=tk.LEFT)
        scrollbar.config(command=self.lst_hobbies.yview)

        tk.Label(form_frame, text="Comments:").grid(row=7, column=0, sticky="nw", pady=5)
        self.txt_comments = tk.Text(form_frame, width=25, height=5)
        self.txt_comments.grid(row=7, column=1, sticky="w", pady=5)
        btn_save = tk.Button(form_frame, text="Save New Contact", width=20, command=self.save_contact)
        btn_save.grid(row=8, column=0, columnspan=2, pady=10)
        btn_new = tk.Button(form_frame, text="New/Clear Form", width=20, command=self.clear_form)
        btn_new.grid(row=9, column=0, columnspan=2, pady=5)
        btn_update = tk.Button(form_frame, text="Update Contact", width=20,command=self.edit_contact, state=tk.DISABLED)                         
        btn_update.grid(row=10, column=0, columnspan=2, pady=5)
        self.btn_update = btn_update
        search_frame = tk.LabelFrame(main_frame, text="Search and Load", padx=10, pady=10)
        search_frame.grid(row=0, column=1, padx=10, pady=10, sticky="n")
        search_input_frame = tk.Frame(search_frame)
        search_input_frame.pack(pady=5)
        tk.Label(search_input_frame, text="Search (ID, Name, or Last Name):").pack(side=tk.LEFT)
        self.txt_search = tk.Entry(search_input_frame, width=25)
        self.txt_search.pack(side=tk.LEFT, padx=5)
        btn_search = tk.Button(search_input_frame, text="Search", command=self.search_contact)
        btn_search.pack(side=tk.LEFT)
        tree_frame = tk.Frame(search_frame)
        tree_frame.pack(pady=10)
        columns = ("ID", "First Name", "Last Name", "Type", "Birth Date")
        self.tree = ttk.Treeview(tree_frame, columns=columns, show="headings", height=15)
        self.tree.heading("ID", text="ID")
        self.tree.heading("First Name", text="First Name")
        self.tree.heading("Last Name", text="Last Name")
        self.tree.heading("Type", text="Type")
        self.tree.heading("Birth Date", text="Birth Date")
        self.tree.column("ID", width=50, anchor=tk.CENTER)
        self.tree.column("First Name", width=100)
        self.tree.column("Last Name", width=100)
        self.tree.column("Type", width=80)
        self.tree.column("Birth Date", width=90, anchor=tk.CENTER)

        self.tree.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
        tree_scrollbar = ttk.Scrollbar(tree_frame, orient="vertical", command=self.tree.yview)
        tree_scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.tree.config(yscrollcommand=tree_scrollbar.set)
        btn_load = tk.Button(search_frame, text="Load Selected for Editing", width=30,command=self.load_selected_for_edit)                       
        btn_load.pack(pady=10)
    # DATABASE

    def load_contact_data(self, contact):
        """Loads contact data into the form fields."""
        self.clear_form(search_results=False)

        self._id_mongo = contact['_id']

        if 'contact_id' in contact:
            self.contact_id_var.set(str(contact['contact_id']))
        else:
            self.contact_id_var.set("N/A")
        self.txt_first.insert(0, contact['first_name'])
        self.txt_last.insert(0, contact['last_name'])

        if contact['birth_date']:
            date_obj = contact['birth_date'].date()
            self.cal_date.set_date(date_obj)
        self.cmb_type.set(contact['type'])
        self.sex_var.set(contact['sex'])
        self.txt_comments.insert("1.0", contact['comments'])
        hobbies_list = contact.get('hobbies', [])
        all_hobbies = [self.lst_hobbies.get(i) for i in range(self.lst_hobbies.size())]
        for hobby in hobbies_list:
            try:
                index = all_hobbies.index(hobby)
                self.lst_hobbies.selection_set(index)
            except ValueError:
                pass
        self.btn_update.config(state=tk.NORMAL)

    def load_selected_for_edit(self):
        """Loads the data of the contact selected in the Treeview into the form."""
        selected_item = self.tree.focus()
        if not selected_item:
            messagebox.showwarning("Warning", "Please select a contact from the search results to load for editing.")
            return
        try:
            mongo_id_str = self.tree.item(selected_item, "tags")[0]
            contact = self.collection.find_one({"_id": ObjectId(mongo_id_str)})
            if contact:
                self.load_contact_data(contact)
            else:
                messagebox.showerror("Error", "Contact not found in the database.")
        except IndexError:
             messagebox.showerror("Error", "Invalid selection.")
        except Exception as e:
            messagebox.showerror("Loading Error", f"An error occurred while loading the contact: {e}")

    def display_search_results(self, contacts):
        """Populates the Treeview with the list of contacts."""
        for item in self.tree.get_children():
            self.tree.delete(item)

        if not contacts:
            messagebox.showinfo("Info", "No contacts found matching the criteria.")
            return
        for contact in contacts:
            birth_date_str = contact.get('birth_date').strftime("%d/%m/%Y") if contact.get('birth_date') else "N/A"
            self.tree.insert("", tk.END, values=(
                contact.get('contact_id', 'N/A'),
                contact.get('first_name', ''),
                contact.get('last_name', ''),
                contact.get('type', 'Unknown'),
                birth_date_str
            ), tags=(str(contact['_id']),))
        messagebox.showinfo("Success", f"{len(contacts)} contact(s) found. Select one to load for editing.")

    def search_contact(self):
        if self.client is None:
            messagebox.showerror("Error", "No database connection.")
            return
        search_term = self.txt_search.get().strip()
        if not search_term:
            query = {}
        else:
            query = {}
            if search_term.isdigit():
                query = {"contact_id": int(search_term)}
            else:
                query = {
                    "$or": [
                        {"first_name": {"$regex": search_term, "$options": "i"}},
                        {"last_name": {"$regex": search_term, "$options": "i"}}
                    ]
                }
        try:
            contacts = list(self.collection.find(query).sort("contact_id", 1))
            self.display_search_results(contacts)
        except Exception as e:
            messagebox.showerror("Search Error", f"An error occurred during search: {e}")

    def save_contact(self):
        if self.client is None:
            messagebox.showerror("Error", "No database connection.")
        if self._id_mongo is not None:
            response = messagebox.askyesno("Warning",
                                           "A contact is currently loaded for editing. Do you want to clear the form and save this as a NEW contact?")
            if not response:
                return
            self.clear_form(search_results=False)
        first_name = self.txt_first.get().strip()
        last_name = self.txt_last.get().strip()
        birth_date = self.cal_date.get_date()
        contact_type = self.cmb_type.get()
        sex = self.sex_var.get()
        hobbies = self.get_selected_hobbies()
        comments = self.txt_comments.get("1.0", tk.END).strip()
        if not first_name or not last_name:
            messagebox.showwarning("Warning", "First Name and Last Name fields are required.")
            return
        if not self.is_valid_name(first_name) or not self.is_valid_name(last_name):
            messagebox.showwarning("Warning", "First Name and Last Name must only contain letters, spaces, and accents (áéíóúñ).")
            return
        try:
            birth_date_dt = datetime.combine(birth_date, datetime.min.time())
            created_at_dt = datetime.combine(date.today(), datetime.min.time())
        except ValueError as e:
            messagebox.showerror("Data Error", f"Invalid date: {e}")
            return

        contact_data = {
            "contact_id": self.get_next_contact_id(), 
            "first_name": first_name,
            "last_name": last_name,
            "birth_date": birth_date_dt,
            "type": contact_type,
            "sex": sex,
            "hobbies": hobbies,
            "comments": comments,
            "created_at": created_at_dt
        }
        try:
            result = self.collection.insert_one(contact_data)
            self.contact_id_var.set(str(contact_data['contact_id']))
            self._id_mongo = result.inserted_id
            messagebox.showinfo("Success", f"Contact saved successfully with ID: {contact_data['contact_id']}.")
            self.btn_update.config(state=tk.NORMAL)
        except Exception as e:
            messagebox.showerror("Save Error", f"An error occurred while saving the contact: {e}")

    def edit_contact(self):
        if self.client is None:
            messagebox.showerror("Error", "No database connection.")
            return
        if self._id_mongo is None:
            messagebox.showwarning("Warning", "No contact selected for editing. Please search and load a contact first.")
            return

        first_name = self.txt_first.get().strip()
        last_name = self.txt_last.get().strip()
        birth_date = self.cal_date.get_date()
        contact_type = self.cmb_type.get()
        sex = self.sex_var.get()
        hobbies = self.get_selected_hobbies()
        comments = self.txt_comments.get("1.0", tk.END).strip()
        if not first_name or not last_name:
            messagebox.showwarning("Warning", "First Name and Last Name fields are required.")
            return

        if not self.is_valid_name(first_name) or not self.is_valid_name(last_name):
            messagebox.showwarning("Warning", "First Name and Last Name must only contain letters, spaces, and accents (áéíóúñ).")
            return
        try:
            birth_date_dt = datetime.combine(birth_date, datetime.min.time())
        except ValueError as e:
            messagebox.showerror("Data Error", f"Invalid date: {e}")
            return
        updated_data = {
            "$set": {
                "first_name": first_name,
                "last_name": last_name,
                "birth_date": birth_date_dt,
                "type": contact_type,
                "sex": sex,
                "hobbies": hobbies,
                "comments": comments,
                "updated_at": datetime.now()
            }
        }
        try:
            self.collection.update_one({"_id": self._id_mongo}, updated_data)
            messagebox.showinfo("Success", f"Contact ID {self.contact_id_var.get()} updated successfully.")
            self.search_contact()
        except Exception as e:
            messagebox.showerror("Update Error", f"An error occurred while updating the contact: {e}")

    def clear_form(self, search_results=True):
        """Resets all fields in the form."""
        self.txt_first.delete(0, tk.END)
        self.txt_last.delete(0, tk.END)
        initial_date = date.today().replace(year=date.today().year - 20)
        self.cal_date.set_date(initial_date)
        self.cmb_type.current(0)
        self.sex_var.set("M")
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_comments.delete("1.0", tk.END)

        self.contact_id_var.set("New Contact")
        self._id_mongo = None
        self.btn_update.config(state=tk.DISABLED)

        if search_results:
            self.txt_search.delete(0, tk.END)
            for item in self.tree.get_children():
                self.tree.delete(item)

if __name__ == "__main__":
    root = tk.Tk()
    app = FrmContactsVisual(root)
    root.mainloop()