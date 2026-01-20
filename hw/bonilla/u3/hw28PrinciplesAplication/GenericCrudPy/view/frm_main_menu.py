import tkinter as tk
from view.frm_list import FrmList
from view.frm_add import FrmAdd
from view.frm_edit import FrmEdit
from view.frm_delete import FrmDelete

class FrmMainMenu(tk.Tk):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Generic CRUD")
        self.geometry("400x250")

        self.create_menu()

    def create_menu(self):
        menu_bar = tk.Menu(self)

        options_menu = tk.Menu(menu_bar, tearoff=0)
        options_menu.add_command(label="Show Data", command=self.open_list)
        options_menu.add_command(label="Add", command=self.open_add)
        options_menu.add_command(label="Edit", command=self.open_edit)
        options_menu.add_command(label="Delete", command=self.open_delete)
        options_menu.add_separator()
        options_menu.add_command(label="Exit", command=self.destroy)

        menu_bar.add_cascade(label="Options", menu=options_menu)

        self.config(menu=menu_bar)

    def open_list(self):
        FrmList(self.controller)

    def open_add(self):
        FrmAdd(self.controller)

    def open_edit(self):
        FrmEdit(self.controller)

    def open_delete(self):
        FrmDelete(self.controller)