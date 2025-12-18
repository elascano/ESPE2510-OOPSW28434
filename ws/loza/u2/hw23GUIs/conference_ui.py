import tkinter as tk
from tkinter import messagebox


# ------------------------
# Clase principal del sistema
# ------------------------
class ConferenceSystem(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Conference Paper Submission System")
        self.geometry("600x450")

        self.current_frame = None
        self.show_frame(LoginScreen)

    # Cambia de pantalla
    def show_frame(self, frame_class):
        if self.current_frame is not None:
            self.current_frame.destroy()

        self.current_frame = frame_class(self)
        self.current_frame.pack(fill="both", expand=True)


# ------------------------
# Login
# ------------------------
class LoginScreen(tk.Frame):
    def __init__(self, master):
        super().__init__(master)

        tk.Label(self, text="LOGIN", font=("Arial", 20)).pack(pady=20)

        tk.Label(self, text="Usuario:").pack()
        tk.Entry(self).pack()

        tk.Label(self, text="Contraseña:").pack()
        tk.Entry(self, show="*").pack()

        tk.Button(self, text="Ingresar",
                  command=lambda: master.show_frame(MainMenu)).pack(pady=10)

        tk.Button(self, text="Registrarse",
                  command=lambda: master.show_frame(RegisterScreen)).pack()

        tk.Button(self, text="Activar Cuenta",
                  command=lambda: master.show_frame(ActivationScreen)).pack()


# ------------------------
# Registro
# ------------------------
class RegisterScreen(tk.Frame):
    def __init__(self, master):
        super().__init__(master)

        tk.Label(self, text="REGISTRO DE AUTOR", font=("Arial", 20)).pack(pady=20)

        tk.Labe
