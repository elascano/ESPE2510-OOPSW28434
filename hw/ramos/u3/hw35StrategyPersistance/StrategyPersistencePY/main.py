from controller.DataManager import DataManager
from view.view import view

if __name__ == "__main__":
    from controller.CsvStrategy import CsvStrategy 
    
    manager = DataManager(CsvStrategy())
    app = view(manager)
    app.mainloop()