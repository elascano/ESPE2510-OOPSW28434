from services.notebook_service import NotebookService

def display_menu():
    print("\n" + "="*50)
    print("📓 SISTEMA DE GESTIÓN DE NOTEBOOKS")
    print("="*50)
    print("1. Agregar Notebook")
    print("2. Mostrar Todos los Notebooks")
    print("3. Buscar Notebook por ID")
    print("4. Actualizar Notebook")
    print("5. Eliminar Notebook")
    print("6. Estadísticas")
    print("7. Salir")
    print("="*50)

def main():
    notebook_service = NotebookService()
    
    while True:
        display_menu()
        
        try:
            choice = input("Seleccione una opción (1-7): ").strip()
            
            if choice == '1':
                notebook_service.add_notebook()
            elif choice == '2':
                notebook_service.display_all_notebooks()
            elif choice == '3':
                notebook_service.search_notebook()
            elif choice == '4':
                notebook_service.update_notebook()
            elif choice == '5':
                notebook_service.delete_notebook()
            elif choice == '6':
                notebook_service.get_statistics()
            elif choice == '7':
                print("👋 ¡Gracias por usar el Sistema de Gestión de Notebooks!")
                break
            else:
                print("❌ Opción inválida! Por favor seleccione 1-7.")
            
            if choice != '7':
                input("\n⏎ Presione Enter para continuar...")
                
        except KeyboardInterrupt:
            print("\n\n👋 ¡Sesión terminada! Hasta luego.")
            break
        except Exception as e:
            print(f"❌ Error inesperado: {e}")
            input("\n⏎ Presione Enter para continuar...")

if __name__ == "__main__":
    main()