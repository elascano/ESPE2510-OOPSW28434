from ant_simulation import AntSimulation

def main():
    print("ANT AND ANT EATER SIMULATION")
    print("=" * 50)
    
    simulation = AntSimulation(width=5, height=5, max_ticks=20)
    simulation.run()
    
    print("=" * 50)
    print("Simulation completed!")

if __name__ == "__main__":
    main()