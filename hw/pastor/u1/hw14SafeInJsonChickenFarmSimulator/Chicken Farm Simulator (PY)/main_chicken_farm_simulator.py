import sys
import os

sys.path.append(os.path.abspath(os.path.dirname(__file__)))

from espe_edu_ec_chicken_farm_simulator_controller.chicken_farm_controller import ChickenFarmController

def main():
    app = ChickenFarmController()
    print("========================================")
    print("  Welcome to the Chicken Farm Simulator by Mathews Pastor! (Python Version)  ")
    print("========================================")
    app.start()

if __name__ == "__main__":
    main()