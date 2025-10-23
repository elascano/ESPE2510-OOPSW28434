from AntColonySimulatorModel.Colony import Colony

def main():
    print("\n Welcome to the Ant Farm Simulator by Maryuri\n")

    colony = Colony("Alpha Colony")
    nest = colony.nest

    ant1 = nest.create_ant()
    if ant1:
        ant1.retrieve_food()
        ant1.move("north")
        ant1.decrease_weight()

    nest.store_food(10)
    ant2 = nest.create_ant()

if __name__ == "__main__":
    main()