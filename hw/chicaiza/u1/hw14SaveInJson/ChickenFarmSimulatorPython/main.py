#!/usr/bin/env python3
"""
Chicken Farm Simulator - Main Application
"""

from chickenfarmsimulator.controller.farm_controller import FarmController
from chickenfarmsimulator.view.farm_view import FarmView

def main():
    """Función principal de la aplicación"""
    print("Starting Chicken Farm Simulator...")
    
    try:
        # Crear controlador y vista
        controller = FarmController()
        view = FarmView(controller)
        
        # Ejecutar la aplicación
        view.run()
    
    except KeyboardInterrupt:
        print("\n\nApplication interrupted by user.")
    except Exception as e:
        print(f"\nUnexpected error: {e}")
        print("Please contact technical support.")

if __name__ == "__main__":
    main()