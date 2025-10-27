import csv
from ChickenCoop import ChickenCoop
from Chiken import Chicken

class Chicken_coop_management:
    def save_chicken_to_csv(list_coops, filename="chicken_registration.csv"):
        field_titles = ["ID", "NAME", "COLOR", "AGE", "IS MOLTING", "COOP ID"]
        all_chiken_data = []
        for coop in list_coops:
            if isinstance(coop, ChickenCoop):
                coop_id = coop.chicken_coop_number
            for chicken in coop.chicken_coops:
                if isinstance(chicken, Chicken):
                    chicken_data = {
                        "ID": chicken.id,
                        "NAME": chicken.name,
                        "COLOR": chicken.color,
                        "AGE": chicken.age,
                        "IS MOLTING": chicken.isMolting,
                        "COOP ID": coop_id
                    }
                    all_chiken_data.append(chicken_data)
        if not all_chiken_data:
            print("There is no chicken data to save")
            return
        try:
            with open(filename, "w", newline='', encoding='utf-8') as csvfile:
                writer = csv.DictWriter(
                    csvfile,
                    fieldnames=field_titles,
                    delimiter= ';'     
                )
                writer.writeheader()
                writer.writerows(all_chiken_data)
        except Exception as e:
            print(f"Error saving to CSV: {e}")
                