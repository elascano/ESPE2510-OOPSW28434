from pymongo import MongoClient

class ProfessorModel:
    def __init__(self):

        uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/" 
        self.client = MongoClient(uri)
        self.db = self.client["university_dbPy"]
        self.collection = self.db["professors"]

    def calculate_bonus(self, salary):
        return float(salary) * 0.15

    def insert_professor(self, name, id_num,department, salary):
        clean_salary = round(float(salary), 2)
        bonus = self.calculate_bonus(salary)
        data = {
            "name": name,
            "id_number": id_num,
            "subject": department,
            "base_salary": float(salary),
            "bonus": bonus
        }
        return self.collection.insert_one(data)

    def get_all(self):
        return list(self.collection.find())