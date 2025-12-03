import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

class FrmAppointmentVisual:
    def __init__(self, root):
        self.root = root
        self.root.title("Appointment System")
        self.root.geometry("850x650") 

        lbl_clinic = tk.Label(root, text="TOAMEDICAL CLINIC", font=("Segoe UI", 32, "bold"))
        lbl_clinic.pack(pady=(30, 10), anchor="c", padx=40)

        lbl_sub = tk.Label(root, text="REQUEST APPOINTMENT", font=("Segoe UI", 24))
        lbl_sub.pack(pady=(0, 30), anchor="c", padx=40)

        form_frame = tk.Frame(root)
        form_frame.pack(anchor="w", padx=40)

        lbl_font = ("Segoe UI", 14)
        
        tk.Label(form_frame, text="Appointment id:", font=lbl_font).grid(row=0, column=0, sticky="w", pady=15)
        
        tk.Label(form_frame, text="Patient's id:", font=lbl_font).grid(row=1, column=0, sticky="w", pady=15)
        self.txt_patient_id = tk.Entry(form_frame, font=lbl_font, width=15)
        self.txt_patient_id.grid(row=1, column=1, sticky="w", pady=15, padx=10)

        tk.Label(form_frame, text="Doctor's id:", font=lbl_font).grid(row=2, column=0, sticky="w", pady=15)
        self.txt_doctor_id = tk.Entry(form_frame, font=lbl_font, width=15)
        self.txt_doctor_id.grid(row=2, column=1, sticky="w", pady=15, padx=10)
        
        tk.Label(form_frame, text="Appointment's date:", font=lbl_font).grid(row=3, column=0, sticky="w", pady=15)
        self.date_chooser = DateEntry(form_frame, font=lbl_font, width=14, 
                                      background='darkblue', foreground='white', 
                                      borderwidth=2, date_pattern='dd/mm/yyyy')
        self.date_chooser.grid(row=3, column=1, sticky="w", pady=15, padx=10)

        tk.Label(form_frame, text="Appointment's time:", font=lbl_font).grid(row=4, column=0, sticky="w", pady=15)
        
        time_panel = tk.Frame(form_frame)
        time_panel.grid(row=4, column=1, sticky="w", padx=10)

        self.spin_hrs = tk.Spinbox(time_panel, from_=7, to=21, width=3, font=lbl_font, state="readonly")
        self.spin_hrs.pack(side="left")
        
        tk.Label(time_panel, text="Hrs", font=lbl_font).pack(side="left", padx=5)

        self.spin_mins = tk.Spinbox(time_panel, from_=0, to=55,increment=5, width=3, font=lbl_font, state="readonly", format="%02.0f")
        self.spin_mins.pack(side="left")

        tk.Label(time_panel, text="mins", font=lbl_font).pack(side="left", padx=5)
        
        btn_request = tk.Button(root, text="Request Appointment", font=("Segoe UI", 14), width=20)
        btn_request.pack(pady=40)


if __name__ == "__main__":
        ventana = tk.Tk()
        app = FrmAppointmentVisual(ventana)
        ventana.mainloop()
