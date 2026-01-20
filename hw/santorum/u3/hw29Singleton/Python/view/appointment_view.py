import tkinter as tk
from tkinter import messagebox

from controller.appointment_controller import AppointmentController
from model.appointment import Appointment


class AppointmentView:
    def __init__(self):
        self.controller = AppointmentController()

        self.window = tk.Tk()
        self.window.title("Clinic Appointment System")
        self.window.geometry("350x300")

        self._build_ui()
        self.window.mainloop()

    def _build_ui(self):
        labels = [
            "Appointment ID",
            "Patient ID",
            "Patient Email",
            "Date (YYYY-MM-DD)",
            "Time (HH:MM)"
        ]

        self.entries = []

        for label in labels:
            tk.Label(self.window, text=label).pack()
            entry = tk.Entry(self.window)
            entry.pack()
            self.entries.append(entry)

        tk.Button(
            self.window,
            text="Register Appointment",
            command=self._register_appointment
        ).pack(pady=10)

    def _register_appointment(self):
        appointment = Appointment(
            self.entries[0].get(),
            self.entries[1].get(),
            self.entries[2].get(),
            self.entries[3].get(),
            self.entries[4].get()
        )

        self.controller.register_appointment(appointment)
        messagebox.showinfo("Success", "Appointment registered and email sent.")


if __name__ == "__main__":
    AppointmentView()
