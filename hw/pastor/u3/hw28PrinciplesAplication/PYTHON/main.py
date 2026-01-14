from repository.repository_impl import RepositoryImpl
from service.service_impl import CalculateService
from controller.product_controller import ProductController
from view.main_window import MainWindow

if __name__ == "__main__":
    print("Iniciando sistema Python...")

    repository = RepositoryImpl("products")

    service = CalculateService(repository)

    controller = ProductController(service)

    app = MainWindow(controller)
    
    app.mainloop()