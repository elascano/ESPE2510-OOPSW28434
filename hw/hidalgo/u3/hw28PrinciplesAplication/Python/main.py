from view.product_view import ProductView
from repository.mongo_repository import MongoProductRepository
from service.tax_service import TaxService
from controller.product_controller import ProductController

if __name__ == "__main__":
    # Creamos las piezas del programa
    vista = ProductView()
    repositorio = MongoProductRepository()
    servicio_iva = TaxService()
    
    # El controlador orquesta todo el sistema
    controlador = ProductController(vista, repositorio, servicio_iva)
    
    # Arrancamos la interfaz
    vista.mainloop()