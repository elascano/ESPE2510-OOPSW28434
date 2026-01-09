from model.product_model import ProductModel
from views.product_view import ProductView
from controllers.product_controller import ProductController

if __name__ == "__main__":

    modelo = ProductModel()
    vista = ProductView()

    app = ProductController(modelo, vista)
    
    vista.mainloop()