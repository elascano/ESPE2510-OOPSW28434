from model.Chicken import Chicken

class ChickenController:
    def make_chicken_do_something(self, chicken: Chicken, action: str):
        action = action.lower()
        if action == "cluck":
            chicken.cluck()
        elif action == "eat":
            chicken.eat()
        elif action == "poop":
            chicken.poop()
        elif action == "egg":
            chicken.lay_an_egg()
        else:
            print("Unknown action.")