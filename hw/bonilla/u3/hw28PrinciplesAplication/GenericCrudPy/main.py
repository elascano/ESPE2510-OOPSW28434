from controller.product_controller import GenericController
from view.frm_main_menu import FrmMainMenu

if __name__ == "__main__":
    controller = GenericController()
    app = FrmMainMenu(controller)
    app.mainloop()