import customtkinter as ctk
import tkinter as tk

class FrmSuppliers(ctk.CTk):
    def __init__(self):
        super().__init__()
        self.title("REGISTRO DE PROVEEDORES")
        self.geometry("600x650")

        self.panel_1 = ctk.CTkFrame(self, height=60, corner_radius=0)
        self.panel_1.pack(side="top", fill = "x", padx = 10, pady=(10, 5))
        self.lbl_title = ctk.CTkLabel(self.panel_1, text="REGISTRO DE PROVEEDORES", font=("Segoe UI",16,"bold"))
        self.lbl_title.pack(side="left", padx=20, pady=10)
        
        self.panel_2 = ctk.CTkLabel(self.panel_1,text="FINVORY", font=("Rockwell", 16, "italic"),text_color="gray")
        self.panel_2.pack(side="right", padx=20, pady=10)

        self.panel_2 = ctk.CTkFrame(self, corner_radius=10)
        self.panel_2.pack(fill="both", expand=True, padx=10, pady=5)

        self.panel_2.grid_columnconfigure(1, weight=1)
        self.panel_2.grid_columnconfigure(3, weight=1)

        self.lbl_sec1 = ctk.CTkLabel(self.panel_2, text="DATOS GENERALES", font=("SansSerif", 20, "bold"))
        self.lbl_sec1.grid(row=0, column=0, columnspan=4, sticky="w", padx=20, pady=(20, 10))

        ctk.CTkLabel(self.panel_2, text="CI/ID").grid(row=1, column=0, padx=10, sticky="e")
        self.txt_id = ctk.CTkEntry(self.panel_2) 
        self.txt_id.grid(row=1, column=1, padx=10, pady=5, sticky="ew")

        ctk.CTkLabel(self.panel_2, text="NOMBRE COMPLETO").grid(row=1, column=2, padx=10, sticky="e")
        self.txt_fullname = ctk.CTkEntry(self.panel_2) 
        self.txt_fullname.grid(row=1, column=3, padx=10, pady=5, sticky="ew")

        ctk.CTkLabel(self.panel_2, text="CELULAR").grid(row=2, column=0, padx=10, sticky="e")
        self.txt_phone = ctk.CTkEntry(self.panel_2) 
        self.txt_phone.grid(row=2, column=1, padx=10, pady=5, sticky="ew")

        ctk.CTkLabel(self.panel_2, text="EMAIL").grid(row=2, column=2, padx=10, sticky="e")
        self.txt_email = ctk.CTkEntry(self.panel_2) 
        self.txt_email.grid(row=2, column=3, padx=10, pady=5, sticky="ew")

        self.lbl_sec2 = ctk.CTkLabel(self.panel_2, text="SERVICIOS", font=("SansSerif", 20, "bold"))
        self.lbl_sec2.grid(row=3, column=0, columnspan=4, sticky="w", padx=20, pady=(30, 10))

        ctk.CTkLabel(self.panel_2, text="DESCRIPCIÓN").grid(row=4, column=0, padx=20, sticky="w")
        
        self.txt_description = ctk.CTkTextbox(self.panel_2, height=100)
        self.txt_description.grid(row=5, column=0, columnspan=4, padx=20, pady=5, sticky="ew")

        self.panel_3 = ctk.CTkFrame(self, height=50, corner_radius=0, fg_color="transparent")
        self.panel_3.pack(side="bottom", fill="x", padx=10, pady=10)

        self.btn_return = ctk.CTkButton(self.panel_3, text="ATRAS", fg_color="gray", command=self.action_return)
        self.btn_return.pack(side="left", padx=50, expand=True)

        self.btn_add = ctk.CTkButton(self.panel_3, text="REGISTRAR", command=self.action_add)
        self.btn_add.pack(side="right", padx=50, expand=True)
        
    def action_return(self):
        print("Botón Atras presionado")
        self.destroy()

    def action_add(self):
        print(f"Registrando a: {self.txt_fullname.get()}")
if __name__ == "__main__":
    app = FrmSuppliers()
    app.mainloop()