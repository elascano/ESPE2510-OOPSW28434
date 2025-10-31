import sys, os
# Add parent directory to sys.path to allow importing model package
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

import sys
from model.ChickenFarm import ChickenFarm

class ChickenFarmSimulator:
    def __init__(self):
        self.farm = ChickenFarm()


    def run(self):
        while True:
            print("\n=== Chicken Farm Simulator ===")
            print("1) Add Chicken")
            print("2) View All Chickens")
            print("3) Remove Chicken")
            print("4) Exit")


            choice = input("Select an option (1-4): ").strip()
            if choice == "1":
                self._add_chicken_flow()
            elif choice == "2":
                self._view_chickens_flow()
            elif choice == "3":
                self._remove_chicken_flow()
            elif choice == "4":
                print("Exiting program. Goodbye!")
                sys.exit(0)
            else:
                print("Invalid option, please enter a number between 1 and 4.")


    def _add_chicken_flow(self):
        print("\n--- Add Chicken ---")
        # Name validation
        while True:
            name = input("Enter chicken name: ").strip()
            if len(name) == 0:
                print("Name cannot be empty. Try again.")
            else:
                break


        # Age validation
        while True:
            age_s = input("Enter chicken age (integer >= 0): ").strip()
            if not age_s.isdigit():
                print("Age must be a non-negative integer. Try again.")
                continue
            age = int(age_s)
            break


        # Molting validation
        while True:
            m = input("Is the chicken molting? (y/n): ").strip().lower()
            if m in ("y", "yes"):
                molting = True
                break
            elif m in ("n", "no"):
                molting = False
                break
            else:
                print("Please type 'y' (yes) or 'n' (no).")


        chicken = self.farm.add_chicken(name, age, molting)
        print(f"Chicken added with ID {chicken.id} and assigned to Coop {chicken.coop_id}.")


    def _view_chickens_flow(self):
        print("\n--- All Chickens ---")
        chickens = self.farm.list_all_chickens()
        if not chickens:
            print("No chickens found.")
            return


        # Build table rows
        rows = []
        headers = ["ID", "Name", "Age", "Molting", "Coop"]
        for c, coop_id in chickens:
            rows.append([str(c.id), c.name, str(c.age), str(c.molting), str(coop_id)])


        # compute column widths
        col_widths = [len(h) for h in headers]
        for r in rows:
            for i, cell in enumerate(r):
                if len(cell) > col_widths[i]:
                    col_widths[i] = len(cell)


        # print header
        header_line = " | ".join(h.ljust(col_widths[i]) for i, h in enumerate(headers))
        sep_line = "-+-".join("-" * col_widths[i] for i in range(len(headers)))
        print(header_line)
        print(sep_line)


        for r in rows:
            print(" | ".join(r[i].ljust(col_widths[i]) for i in range(len(r))))


    def _remove_chicken_flow(self):
        print("\n--- Remove Chicken ---")
        # show chickens first
        chickens = self.farm.list_all_chickens()
        if not chickens:
            print("No chickens to remove.")
            return

        self._view_chickens_flow()

        while True:
            cid_s = input("Enter the ID of the chicken to remove: ").strip()
            if not cid_s.isdigit():
                print("ID must be an integer. Try again.")
                continue
            cid = int(cid_s)
            removed = self.farm.remove_chicken(cid)
            if removed:
                print(f"Chicken with ID {cid} removed successfully.")
            else:
                print(f"Chicken with ID {cid} not found.")
            break




if __name__ == "__main__":
    sim = ChickenFarmSimulator()
    sim.run()