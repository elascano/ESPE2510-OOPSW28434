import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry 

class FrmContactsVisual:
    def __init__(self, root):
        self.root = root
        self.root.title("Contacts Book")
        self.root.geometry("720x550") 


        lbl_title = tk.Label(root, text="CONTACTS", font=("Segoe UI", 24, "bold"))
        lbl_title.pack(pady=20)
        
        main_frame = tk.Frame(root)
        main_frame.pack(expand=True, fill="both", padx=30)

        tk.Label(main_frame, text="id:").grid(row=0, column=0, sticky="w", pady=5)
       

        tk.Label(main_frame, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_first = tk.Entry(main_frame, width=20)
        self.txt_first.grid(row=1, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_last = tk.Entry(main_frame, width=20)
        self.txt_last.grid(row=2, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Birth Date:").grid(row=3, column=0, sticky="w", pady=5)
        self.cal_date = DateEntry(main_frame, width=17, background='darkblue',
                                  foreground='white', borderwidth=2, date_pattern='dd/mm/yyyy')
        self.cal_date.grid(row=3, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Age:").grid(row=4, column=0, sticky="w", pady=5)
        


        tk.Label(main_frame, text="Type:").grid(row=5, column=0, sticky="w", pady=5)
        self.cmb_type = ttk.Combobox(main_frame, values=["Family", "Friend", "Job", "Unknown"], state="readonly")
        self.cmb_type.current(0)
        self.cmb_type.grid(row=5, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Sex:").grid(row=6, column=0, sticky="nw", pady=5)
        self.sex_var = tk.StringVar(value="M") 
        
        frame_sex = tk.Frame(main_frame)
        frame_sex.grid(row=6, column=1, sticky="w")
        tk.Radiobutton(frame_sex, text="Male", variable=self.sex_var, value="M").pack(anchor="w")
        tk.Radiobutton(frame_sex, text="Female", variable=self.sex_var, value="F").pack(anchor="w")

        tk.Label(main_frame, text="Hobbies:").grid(row=7, column=0, sticky="nw", pady=5)
        
        frame_hobbies = tk.Frame(main_frame)
        frame_hobbies.grid(row=7, column=1, sticky="w")
        
        scrollbar = tk.Scrollbar(frame_hobbies)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        
        self.lst_hobbies = tk.Listbox(frame_hobbies, height=4, width=20, 
                                      selectmode=tk.MULTIPLE, yscrollcommand=scrollbar.set)
        
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Instrument"]
        for h in hobbies:
            self.lst_hobbies.insert(tk.END, h)
            
        self.lst_hobbies.pack(side=tk.LEFT)
        scrollbar.config(command=self.lst_hobbies.yview)

        tk.Label(main_frame, text="Comments:").grid(row=0, column=2, sticky="nw", padx=(50,0))
        self.txt_comments = tk.Text(main_frame, width=30, height=18)
        self.txt_comments.grid(row=1, column=2, rowspan=8, padx=(50,0), sticky="nw")

        btn_save = tk.Button(root, text="Save", width=10)
        btn_save.pack(pady=20)

if __name__ == "__main__":
    root = tk.Tk()
    app = FrmContactsVisual(root)
    root.mainloop()