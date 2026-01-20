from controller.product_controller import ProductController
from view.main_view import MainView

if __name__ == "__main__":
    controller = ProductController()
    app = MainView(controller)
    app.mainloop()
