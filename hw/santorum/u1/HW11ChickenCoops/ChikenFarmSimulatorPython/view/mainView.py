from controller.farmController import FarmController

def mainView():
    print("=== 🐔 Chicken Farm Simulator ===\n")
    farmController = FarmController()
    farmController.setupFarm()
    farmController.showFarm()
