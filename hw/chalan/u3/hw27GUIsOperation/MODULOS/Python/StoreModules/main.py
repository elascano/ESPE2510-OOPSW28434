from controller.CrudController import CrudController
from domain.domain import DOMAIN
from view.MainView import MainView

def main():
    controller = CrudController(
        DOMAIN["collection"],
        DOMAIN["priceField"],
        DOMAIN["stockField"]
    )

    MainView(controller)

if __name__ == "__main__":
    main()
