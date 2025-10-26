import csv
class File:
    def __init__(self,Filename,headers):
        self.Filename = Filename
        self.headers = headers
    def saveDatainCSV(self, fileName, headers, data):
        try:
            with open(fileName, "w", newline='', encoding='utf-8') as file:
                writer = csv.DictWriter(file, fieldnames=headers)
                writer.writeheader()
                writer.writerows(data)
            print("Data saved successfully.")
        except Exception as e:
            print(f"An error occurred while saving data: {e}")
            return False
        return True

