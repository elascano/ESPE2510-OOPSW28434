import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

class ViewContactApp:
    def __init__(self, root):
        self.root = root
        self.root.title("CONTACTS")
        self.root.geometry("800x700")
        
        title_label = tk.Label(root, text="CONTACTS", font=("Segoe UI", 24))
        title_label.pack(pady=20)
        
        main_frame = tk.Frame(root)
        main_frame.pack(padx=20, pady=20, fill="both", expand=True)
        
        main_frame.columnconfigure(1, weight=1)
        
        tk.Label(main_frame, text="Id:").grid(row=0, column=0, sticky="w", pady=10)
        self.txt_id = tk.Entry(main_frame)
        self.txt_id.grid(row=0, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="First Name:").grid(row=1, column=0, sticky="w", pady=10)
        self.txt_first_name = tk.Entry(main_frame)
        self.txt_first_name.grid(row=1, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="Last Name:").grid(row=2, column=0, sticky="w", pady=10)
        self.txt_last_name = tk.Entry(main_frame)
        self.txt_last_name.grid(row=2, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="Birth Date:").grid(row=3, column=0, sticky="w", pady=10)
        self.date_picker = DateEntry(main_frame, width=12, background='darkblue',
                                     foreground='white', borderwidth=2, date_pattern='dd/mm/yyyy')
        self.date_picker.grid(row=3, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="Type:").grid(row=4, column=0, sticky="w", pady=10)
        self.combo_type = ttk.Combobox(main_frame, values=["Family", "Friend", "Job", "Unknown"])
        self.combo_type.current(0)
        self.combo_type.grid(row=4, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="Sex:").grid(row=5, column=0, sticky="nw", pady=10)
        self.sex_var = tk.StringVar(value="Female") 
        
        frame_sex = tk.Frame(main_frame)
        frame_sex.grid(row=5, column=1, sticky="w", padx=10)
        
        rb_female = tk.Radiobutton(frame_sex, text="Female", variable=self.sex_var, value="Female")
        rb_male = tk.Radiobutton(frame_sex, text="Male", variable=self.sex_var, value="Male")
        rb_female.pack(anchor="w")
        rb_male.pack(anchor="w")
        
        tk.Label(main_frame, text="Hobbies:").grid(row=6, column=0, sticky="nw", pady=10)
        
        hobbies_data = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play Instrument"]
        self.list_hobbies = tk.Listbox(main_frame, selectmode=tk.MULTIPLE, height=6)
        for hobby in hobbies_data:
            self.list_hobbies.insert(tk.END, hobby)
        self.list_hobbies.grid(row=6, column=1, sticky="w", padx=10)
        
        tk.Label(main_frame, text="Comments").grid(row=1, column=2, sticky="nw", padx=50)
        self.txt_comments = tk.Text(main_frame, width=30, height=10)
        self.txt_comments.grid(row=2, column=2, rowspan=5, padx=50, sticky="n")
        
        btn_save = tk.Button(main_frame, text="Save", command=self.save_action)
        btn_save.grid(row=7, column=1, pady=30)
        
    def save_action(self):
        pass

if __name__ == "__main__":
    root = tk.Tk()
    app = ViewContactApp(root)
    root.mainloop()