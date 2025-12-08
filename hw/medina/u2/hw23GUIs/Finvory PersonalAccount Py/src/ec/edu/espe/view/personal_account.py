import tkinter as tk
from tkinter import ttk
from tkinter import messagebox

class FinvoryApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Finvory - Company Management")
        self.root.geometry("500x750")
        self.root.configure(bg="#f4f6f9")

        header_frame = tk.Frame(root, bg="#2c3e50", height=100)
        header_frame.pack(fill="x", side="top")
        
        lbl_logo = tk.Label(header_frame, text="Finvory", font=("Segoe UI", 28, "bold"), fg="white", bg="#2c3e50")
        lbl_logo.pack(pady=(20, 5))
        
        lbl_subtitle = tk.Label(header_frame, text="Create Company Account", font=("Segoe UI", 12), fg="#bdc3c7", bg="#2c3e50")
        lbl_subtitle.pack(pady=(0, 20))

        main_frame = tk.Frame(root, bg="white", bd=1, relief="solid")
        main_frame.pack(padx=30, pady=30, fill="both", expand=True)

        main_frame.columnconfigure(1, weight=1)

        style_label = {"font": ("Segoe UI", 10, "bold"), "bg": "white", "fg": "#34495e"}
        
        tk.Label(main_frame, text="Company Name:", **style_label).grid(row=0, column=0, sticky="w", padx=20, pady=(20, 5))
        self.txt_name = ttk.Entry(main_frame)
        self.txt_name.grid(row=1, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="RUC:", **style_label).grid(row=2, column=0, sticky="w", padx=20, pady=5)
        self.txt_ruc = ttk.Entry(main_frame)
        self.txt_ruc.grid(row=3, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="Address:", **style_label).grid(row=4, column=0, sticky="w", padx=20, pady=5)
        self.txt_address = tk.Text(main_frame, height=3, width=30, font=("Segoe UI", 10), bd=1, relief="solid")
        self.txt_address.grid(row=5, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="Phone:", **style_label).grid(row=6, column=0, sticky="w", padx=20, pady=5)
        self.txt_phone = ttk.Entry(main_frame)
        self.txt_phone.grid(row=7, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="Email:", **style_label).grid(row=8, column=0, sticky="w", padx=20, pady=5)
        self.txt_email = ttk.Entry(main_frame)
        self.txt_email.grid(row=9, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="Username:", **style_label).grid(row=10, column=0, sticky="w", padx=20, pady=5)
        self.txt_username = ttk.Entry(main_frame)
        self.txt_username.grid(row=11, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 15))

        tk.Label(main_frame, text="Password:", **style_label).grid(row=12, column=0, sticky="w", padx=20, pady=5)
        self.txt_password = ttk.Entry(main_frame, show="*")
        self.txt_password.grid(row=13, column=0, columnspan=2, sticky="ew", padx=20, pady=(0, 20))

        btn_create = tk.Button(main_frame, text="CREATE ACCOUNT", font=("Segoe UI", 11, "bold"), 
                               bg="#27ae60", fg="white", cursor="hand2", relief="flat", command=self.create_account)
        btn_create.grid(row=14, column=0, columnspan=2, sticky="ew", padx=20, pady=20)

    def create_account(self):
        name = self.txt_name.get()
        ruc = self.txt_ruc.get()
        address = self.txt_address.get("1.0", tk.END).strip()
        phone = self.txt_phone.get()
        email = self.txt_email.get()
        username = self.txt_username.get()
        password = self.txt_password.get()

        print("--- New CompanyAccount Created ---")
        print(f"Name: {name}")
        print(f"RUC: {ruc}")
        print(f"Address: {address}")
        print(f"Phone: {phone}")
        print(f"Email: {email}")
        print(f"Username: {username}")
        print(f"Password: {'*' * len(password)}")
        
        messagebox.showinfo("Finvory", "Company Account created successfully!")

if __name__ == "__main__":
    root = tk.Tk()
    app = FinvoryApp(root)
    root.mainloop()