from AntColonySimulatorModel.Colony import Colony
from AntColonySimulatorModel.AntEater import AntEater
from AntColonySimulatorModel.Tick import Tick

def main():
    print("\nWelcome to the Ant Farm Simulator by Maryuri\n")

    colony = Colony("Alpha Colony")
    nest = colony.nest
    tick = Tick(duration_ms=300)
    ant_eater = AntEater(1)

    nest.store_food(10)
    ant1 = nest.create_ant()
    ant2 = nest.create_ant()

    for _ in range(10):
        tick.advance()

        for ant in nest.ants:
            if ant.weight < 3:
                ant.retrieve_food()
            else:
                ant.move("random")

        ant_eater.tick()

if __name__ == "__main__":
    main()
