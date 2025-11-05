from Chicken import Chicken

if __name__ == "__main__":
    print("\nThis is my Chicken Farm Simulator by Maryuri")
    print("The Chicken is\n")
    
    chicken = Chicken(1, "Lucy", "White and Brown", False, 2)
    print(chicken)
    
    chicken.set_color("Brown")