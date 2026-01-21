import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import datetime
import threading

from ec.edu.espe.singleton.utils.mongo_manager import MongoManager
from ec.edu.espe.singleton.utils.gui_validation import GUIValidation
from ec.edu.espe.singleton.utils.appointment_notification_service import AppointmentNotificationService
from ec.edu.espe.singleton.model.appointment import Appointment
from ec.edu.espe.singleton.model.date_model import DateModel

class FrmRequestAppointment(tk.Tk):
    def __init__(self):
        super().__init__()
        self.mongo_manager = MongoManager()
        self.appointment = Appointment()
        
        self.title("Clínica Toamedical - Agendar Cita")
        self.geometry("520x550")
        self.configure(bg="white")
        
        self.init_components()

    def init_components(self):
        main_panel = tk.Frame(self, bg="white", padx=20, pady=20)
        main_panel.pack(fill=tk.BOTH, expand=True)

        header_frame = tk.Frame(main_panel, bg="white")
        header_frame.pack(fill=tk.X, pady=(0, 20))
        
        btn_back = tk.Button(header_frame, text="Regresar al menú", command=self.on_back)
        btn_back.pack(side=tk.LEFT)
        
        lbl_title = tk.Label(header_frame, text="CLÍNICA TOAMEDICAL", 
                             font=("Segoe UI", 18, "bold"), bg="white")
        lbl_title.pack(side=tk.LEFT, padx=20)

        tk.Label(main_panel, text="AGENDAR CITA", font=("Segoe UI", 12), bg="white").pack(pady=10)

        form_frame = tk.Frame(main_panel, bg="white")
        form_frame.pack(pady=10)

        tk.Label(form_frame, text="ID de la cita:", bg="white", anchor="e").grid(row=0, column=0, padx=5, pady=5, sticky="e")
        self.txt_appointment_id = tk.Entry(form_frame)
        self.txt_appointment_id.grid(row=0, column=1, padx=5, pady=5, sticky="w")

        tk.Label(form_frame, text="ID del paciente:", bg="white", anchor="e").grid(row=1, column=0, padx=5, pady=5, sticky="e")
        self.txt_patient_id = tk.Entry(form_frame)
        self.txt_patient_id.grid(row=1, column=1, padx=5, pady=5, sticky="w")

        tk.Label(form_frame, text="ID del doctor:", bg="white", anchor="e").grid(row=2, column=0, padx=5, pady=5, sticky="e")
        self.txt_doctor_id = tk.Entry(form_frame)
        self.txt_doctor_id.grid(row=2, column=1, padx=5, pady=5, sticky="w")

        tk.Label(form_frame, text="Fecha de la cita:", bg="white", anchor="e").grid(row=3, column=0, padx=5, pady=5, sticky="e")
        self.cal_date = DateEntry(form_frame, width=17, background='darkblue', foreground='white', borderwidth=2)
        self.cal_date.grid(row=3, column=1, padx=5, pady=5, sticky="w")

        tk.Label(form_frame, text="Hora de la cita:", bg="white", anchor="e").grid(row=4, column=0, padx=5, pady=5, sticky="e")
        
        time_frame = tk.Frame(form_frame, bg="white")
        time_frame.grid(row=4, column=1, sticky="w")
        
        self.spin_hour = tk.Spinbox(time_frame, from_=7, to=21, width=3)
        self.spin_hour.pack(side=tk.LEFT)
        tk.Label(time_frame, text="Hrs", bg="white").pack(side=tk.LEFT, padx=2)
        
        self.spin_minutes = tk.Spinbox(time_frame, from_=0, to=55, increment=5, width=3)
        self.spin_minutes.pack(side=tk.LEFT)
        tk.Label(time_frame, text="Mins", bg="white").pack(side=tk.LEFT, padx=2)

        tk.Button(main_panel, text="Agendar cita", command=self.on_request_appointment, 
                  bg="#e1e1e1", padx=20, pady=5).pack(pady=40)

    def on_back(self):
        print("Regresando al menú...")

    def on_request_appointment(self):
        if not GUIValidation.validate_only_numbers(self.txt_appointment_id, "id de la cita"): return
        if not GUIValidation.validate_only_numbers(self.txt_doctor_id, "id del doctor"): return
        if not GUIValidation.validate_only_numbers(self.txt_patient_id, "id del paciente"): return
        if not GUIValidation.validate_date_range(self.cal_date, "Fecha de la cita"): return

        self.read_values()

        confirm = messagebox.askyesno("Confirmar", f"¿Desea agendar esta Cita?\n{self.appointment}")

        if confirm:
            try:
                app_id = int(self.txt_appointment_id.get())
                pat_id = int(self.txt_patient_id.get())
                doc_id = int(self.txt_doctor_id.get())
                selected_date = self.cal_date.get_date()
                hour = int(self.spin_hour.get())
                minute = int(self.spin_minutes.get())

                if self.check_id_exists(app_id):
                    messagebox.showerror("ID Duplicado", f"Error: El ID de cita {app_id} ya existe.")
                    return

                if not self.check_patient_exists(pat_id):
                    messagebox.showerror("Paciente No Encontrado", f"Error: El paciente con ID {pat_id} no existe.")
                    return

                if not self.check_doctor_exists(doc_id):
                    messagebox.showerror("Doctor No Encontrado", f"Error: El doctor con ID {doc_id} no existe.")
                    return

                datetime_selected = datetime.combine(selected_date, datetime.min.time())
                
                if self.check_schedule_conflict(doc_id, datetime_selected, hour, minute):
                    messagebox.showwarning("Horario Ocupado", "El doctor ya tiene una cita en ese horario.")
                    return

                date_doc = self.mongo_manager.create_date_document(datetime_selected, hour, minute)
                
                appointment_doc = {
                    "appointmentId": app_id,
                    "patientId": pat_id,
                    "doctorId": doc_id,
                    "status": "Agendado",
                    "date": date_doc
                }

                self.mongo_manager.insert("appointments", appointment_doc)
                
                date_str = f"{selected_date.strftime('%d/%m/%Y')} a las {hour}:{minute:02d}"
                self.send_notification_in_background(str(pat_id), date_str)

                messagebox.showinfo("Éxito", "La cita fue guardada exitosamente.")
                self.empty_fields()

            except ValueError:
                messagebox.showerror("Error", "Error en el formato de los datos numéricos.")
            except Exception as e:
                messagebox.showerror("Error", f"Ocurrió un error inesperado: {e}")

    def read_values(self):
        try:
            app_id = int(self.txt_appointment_id.get())
            pat_id = int(self.txt_patient_id.get())
            doc_id = int(self.txt_doctor_id.get())
            
            sel_date = self.cal_date.get_date()
            hour = int(self.spin_hour.get())
            minute = int(self.spin_minutes.get())

            date_model = DateModel(sel_date.day, sel_date.month, sel_date.year, hour, minute)
            self.appointment = Appointment(app_id, date_model, pat_id, doc_id)
        except:
            pass 

    def empty_fields(self):
        self.txt_appointment_id.delete(0, tk.END)
        self.txt_patient_id.delete(0, tk.END)
        self.txt_doctor_id.delete(0, tk.END)
        self.cal_date.set_date(datetime.now())
        self.spin_hour.delete(0, tk.END)
        self.spin_hour.insert(0, 7)
        self.spin_minutes.delete(0, tk.END)
        self.spin_minutes.insert(0, 0)
        self.txt_appointment_id.config(bg="white")
        self.txt_patient_id.config(bg="white")
        self.txt_doctor_id.config(bg="white")

    def check_id_exists(self, app_id: int) -> bool:
        results = self.mongo_manager.find("appointments", {"appointmentId": app_id})
        return len(results) > 0

    def check_doctor_exists(self, doctor_id: int) -> bool:
        results = self.mongo_manager.find("doctors", {"doctorId": doctor_id})
        return len(results) > 0

    def check_patient_exists(self, patient_id: int) -> bool:
        results = self.mongo_manager.find("patients", {"patientId": patient_id})
        return len(results) > 0

    def check_schedule_conflict(self, doctor_id: int, date_obj: datetime, hour: int, minute: int) -> bool:
        date_str = date_obj.strftime("%Y-%m-%d")
        time_str = f"{hour:02d}:{minute:02d}"

        filter_doc = {
            "doctorId": doctor_id,
            "date.date": date_str,
            "date.time": time_str
        }
        results = self.mongo_manager.find("appointments", filter_doc)
        return len(results) > 0

    def send_notification_in_background(self, patient_id: str, date_info: str):
        def task():
            try:
                pat_id_int = int(patient_id)
                patient_email = self.mongo_manager.get_email("patients", "patientId", pat_id_int)
                patient_name = self.mongo_manager.get_info("patients", "patientId", pat_id_int, "fullName")
                
                if patient_email and patient_name:
                    service = AppointmentNotificationService()
                    service.send_reservation_confirmation(patient_email, patient_name, date_info)
                else:
                    print("No se encontró email o nombre del paciente para notificación.")
                    
            except Exception as e:
                print(f"Error enviando notificación: {e}")

        threading.Thread(target=task).start()

if __name__ == "__main__":
    app = FrmRequestAppointment()
    app.mainloop()