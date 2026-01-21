import tkinter as tk
from tkinter import messagebox
from datetime import datetime, date

class GUIValidation:

    COLOR_ERROR = "#ffc8c8"
    COLOR_OK = "white"

    @staticmethod
    def validate_only_numbers(entry: tk.Entry, field_name: str) -> bool:
        text = entry.get().strip()
        if not text:
            GUIValidation.show_error(entry, f"El campo '{field_name}' es obligatorio.")
            return False
        
        if not text.isdigit():
            GUIValidation.show_error(entry, f"El campo '{field_name}' debe contener solo números.")
            return False
            
        GUIValidation.mark_success(entry)
        return True

    @staticmethod
    def validate_date_range(date_entry, field_name: str) -> bool:
        selected_date = date_entry.get_date()
        today = date.today()

        if selected_date < today:
            messagebox.showwarning("Error", "La fecha no puede ser anterior a hoy.")
            return False
        
        if selected_date.year > (today.year + 1):
             messagebox.showwarning("Error", "La fecha no puede exceder el próximo año.")
             return False

        return True

    @staticmethod
    def show_error(widget, message):
        widget.config(bg=GUIValidation.COLOR_ERROR)
        messagebox.showwarning("Error", message)
        widget.focus_set()

    @staticmethod
    def mark_success(widget):
        widget.config(bg=GUIValidation.COLOR_OK)