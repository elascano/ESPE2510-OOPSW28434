from Controller.StockAlertController import StockAlertController
from View.StockAlertViewTk import StockAlertViewTk

def main():
    controller = StockAlertController()
    view = StockAlertViewTk(controller)

    controller.setView(view)

    view.start()

if __name__ == "__main__":
    main()
