import asyncio
import sys
from chickenFarmController import FarmController

async def main():
    print("=======================================")
    print(" STARTING FARM SIMULATOR ")
    print("=======================================")
    
    try:
        controller = FarmController('chickenFarm.json') 
        await controller.mainMenu() 
        
    except Exception as e:
        print("\n--- ERROR FATAL EN LA APLICACIÓN ---", file=sys.stderr)
        print(e, file=sys.stderr)

if __name__ == "__main__":
    asyncio.run(main())