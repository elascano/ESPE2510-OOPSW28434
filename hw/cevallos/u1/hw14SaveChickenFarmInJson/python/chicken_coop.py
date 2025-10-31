class ChickenCoop:
    def __init__(self, id, description):
        self.id = id
        self.description = description
        self.chickens = []
    
    def add_chicken(self, chicken):
        self.chickens.append(chicken)
        print(f"Chicken {chicken.get_name()} added to coop {self.id}")
    
    def remove_chicken(self, chicken_id):
        for i, chicken in enumerate(self.chickens):
            if chicken.get_id() == chicken_id:
                del self.chickens[i]
                print(f"Chicken with ID {chicken_id} removed from coop {self.id}")
                return
        
        print(f"Chicken with ID {chicken_id} not found in coop {self.id}")
    
    def list_chickens(self):
        print(f"\n--- Chickens in Coop {self.id} - {self.description} ---")
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(f"  {chicken}")
    
    def make_all_do_stuff(self):
        print(f"\n--- All chickens in Coop {self.id} are active! ---")
        if not self.chickens:
            print("No chickens in this coop to do stuff.")
            return
        
        for chicken in self.chickens:
            print(f"\n--- {chicken.get_name()} is doing stuff ---")
            chicken.do_stuff()
    
    def find_chicken_by_id(self, chicken_id):
        for chicken in self.chickens:
            if chicken.get_id() == chicken_id:
                return chicken
        return None
    
    def get_chickens(self):
        return self.chickens
    
    def get_id(self):
        return self.id
    
    def get_description(self):
        return self.description
    
    def get_chicken_count(self):
        return len(self.chickens)
    
    def __str__(self):
        return f"ChickenCoop{{id={self.id}, description='{self.description}', chickens={len(self.chickens)}}}"
    
    def __repr__(self):
        return self.__str__()