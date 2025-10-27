from controller.farmController import FarmController
import csv


def mainView():
    print("== Chicken Farm Simulator ==\n")
    
    farmController = FarmController()
    farmController.setupFarm()
    farmController.showFarm()

    with open("farmData.csv", mode="w", newline="") as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(["CoopId", "ChickenId", "Name", "Color", "Age", "IsMolting"])
        for coop in farmController.coops:
            for chicken in coop.chickens:
                writer.writerow([coop.coopId, chicken.id, chicken.name, chicken.color, chicken.age, chicken.isMolting])


    with open("farmData.txt", mode="w") as txtfile:
        for coop in farmController.coops:
            txtfile.write(f"Coop {coop.coopId}:\n")
            for chicken in coop.chickens:
                txtfile.write(f"  {chicken}\n")
            txtfile.write("\n")

    print("Data saved.")
    
    return farmController.coops
