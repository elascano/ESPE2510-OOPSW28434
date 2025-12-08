import tkinter as tk
from tkinter import messagebox
from tkcalendar import DateEntry   

class ContactView(tk.Frame):

    def __init__(self, master):
        super().__init__(master)
        self.configure(bg="#e6f2ff")
        self.create_widgets()
    
    def create_widgets(self):
        # ===== TITLE =====
        title = tk.Label(
            self, 
            text="CONTACTS", 
            font=("Arial", 18, "bold"), 
            bg="#cce0ff"
        )
        title.grid(row=0, column=0, columnspan=2, pady=10)

        # First Name
        tk.Label(self, text="First Name:", bg="#e6f2ff").grid(row=1, column=0, sticky="w", pady=5)
        self.txtFirstName = tk.Entry(self, width=30)
        self.txtFirstName.grid(row=1, column=1, pady=5)

        # Last Name
        tk.Label(self, text="Last Name:", bg="#e6f2ff").grid(row=2, column=0, sticky="w", pady=5)
        self.txtLastName = tk.Entry(self, width=30)
        self.txtLastName.grid(row=2, column=1, pady=5)

        # Birth Date (NOW WITH DATE PICKER)
        tk.Label(self, text="Birth Date:", bg="#e6f2ff").grid(row=3, column=0, sticky="w", pady=5)

        self.birthDate = DateEntry(
            self, 
            width=27, 
            background="blue",
            foreground="white",
            date_pattern="yyyy-mm-dd"   # formato YYYY-MM-DD
        )
        self.birthDate.grid(row=3, column=1, pady=5)

        # Age (manual input)
        tk.Label(self, text="Age:", bg="#e6f2ff").grid(row=4, column=0, sticky="w", pady=5)
        self.txtAge = tk.Entry(self, width=30)
        self.txtAge.grid(row=4, column=1, pady=5)

        # Gender
        tk.Label(self, text="Gender:", bg="#e6f2ff").grid(row=5, column=0, sticky="w", pady=5)

        self.genderVar = tk.StringVar()

        self.rbMale = tk.Radiobutton(
            self, text="Male", variable=self.genderVar,
            value="Male", bg="#e6f2ff"
        )
        self.rbMale.grid(row=5, column=1, sticky="w")

        self.rbFemale = tk.Radiobutton(
            self, text="Female", variable=self.genderVar,
            value="Female", bg="#e6f2ff"
        )
        self.rbFemale.grid(row=5, column=1, sticky="e")

        # Hobbies (multiple selection)
        tk.Label(self, text="Hobbies:", bg="#e6f2ff").grid(row=6, column=0, sticky="nw", pady=5)

        self.hobbiesList = tk.Listbox(self, selectmode="multiple", height=5)
        self.hobbiesList.grid(row=6, column=1, pady=5)

        hobbies = ["Sports", "Music", "Reading", "Gaming", "Traveling", "Cooking"]
        for h in hobbies:
            self.hobbiesList.insert(tk.END, h)

        # Comments
        tk.Label(self, text="Comments:", bg="#e6f2ff").grid(row=7, column=0, sticky="nw", pady=5)
        self.txtComments = tk.Text(self, width=30, height=4)
        self.txtComments.grid(row=7, column=1, pady=5)

        # Button (no save yet)
        self.btnShowMsg = tk.Button(self, text="Save", command=self.show_message)
        self.btnShowMsg.grid(row=8, column=0, columnspan=2, pady=15)

    # ---------------------------
    # TEMPORARY BUTTON ACTION
    # ---------------------------
    def show_message(self):
        messagebox.showinfo("Info", "UI working! No data saved yet.")
