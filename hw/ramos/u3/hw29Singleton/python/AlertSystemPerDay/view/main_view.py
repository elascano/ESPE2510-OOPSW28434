import tkinter as tk
from tkinter import messagebox


class MainView:

    def __init__(self, controller):
        self.controller = controller
        self.root = tk.Tk()
        self._init_ui()

    def _init_ui(self):
        self.root.title("AlertSystemPerDay")
        self.root.geometry("500x400")

        input_frame = tk.Frame(self.root)
        input_frame.pack(padx=10, pady=10, fill=tk.X)

        tk.Label(input_frame, text="Task name:").grid(row=0, column=0, sticky="w")
        self.task_name_field = tk.Entry(input_frame)
        self.task_name_field.grid(row=0, column=1, sticky="ew")

        tk.Label(input_frame, text="Deadline:").grid(row=1, column=0, sticky="w")
        self.task_date_field = tk.Entry(input_frame)
        self.task_date_field.insert(0, "YYYY-MM-DD")
        self.task_date_field.grid(row=1, column=1, sticky="ew")

        tk.Label(input_frame, text="Days in advance:").grid(row=2, column=0, sticky="w")
        self.alert_days_field = tk.Entry(input_frame)
        self.alert_days_field.grid(row=2, column=1, sticky="ew")

        add_task_btn = tk.Button(
            input_frame,
            text="Add Task",
            command=self._on_add_task
        )
        add_task_btn.grid(row=3, column=0, pady=5)

        update_alert_btn = tk.Button(
            input_frame,
            text="Update Days",
            command=self._on_update_alert_days
        )
        update_alert_btn.grid(row=3, column=1, pady=5)

        input_frame.columnconfigure(1, weight=1)

        self.task_list_area = tk.Text(self.root, height=12, state=tk.DISABLED)
        self.task_list_area.pack(padx=10, pady=10, fill=tk.BOTH, expand=True)


    def _on_add_task(self):
        self.controller.add_task(
            self.task_name_field.get(),
            self.task_date_field.get()
        )

    def _on_update_alert_days(self):
        self.controller.update_alert_days(
            self.alert_days_field.get()
        )

    def update_task_list(self, text: str):
        self.task_list_area.config(state=tk.NORMAL)
        self.task_list_area.delete("1.0", tk.END)
        self.task_list_area.insert(tk.END, text)
        self.task_list_area.config(state=tk.DISABLED)

    def show_alert(self, message: str):
        messagebox.showwarning("ALERTA", message)

    def run(self):
        self.root.mainloop()
