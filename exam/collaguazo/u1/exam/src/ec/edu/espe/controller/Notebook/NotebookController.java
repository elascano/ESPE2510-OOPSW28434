/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.controller.Notebook;

/**
 *
 * @author Pablo Collaguazo
 */
import model.Notebook;
import service.JsonFileService;
import java.util.ArrayList;
import java.util.Scanner;

public class NotebookController {
    private ArrayList<Notebook> notebooks;
    private JsonFileService jsonService;
    private Scanner scanner;
    
    public NotebookController() {
        this.jsonService = new JsonFileService();
        this.notebooks = jsonService.loadNotebooksFromFile();
        this.scanner = new Scanner(System.in);
    }
    
    // CREATE - Agregar nuevo notebook
    public void addNotebook() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📓 AGREGAR NUEVO NOTEBOOK");
        System.out.println("=".repeat(50));
        
        try {
            System.out.print("Ingrese ID: ");
            String id = scanner.nextLine();
            
            // Verificar si el ID ya existe
            if (findNotebookById(id) != null) {
                System.out.println("❌ Error: Ya existe un notebook con ID " + id + "!");
                return;
            }
            
            System.out.print("Ingrese Marca: ");
            String brand = scanner.nextLine();
            
            System.out.print("Ingrese Número de Páginas: ");
            int pages = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese Tamaño (A4, A5, Carta, etc.): ");
            String size = scanner.nextLine();
            
            System.out.print("Ingrese Precio: ");
            double price = Double.parseDouble(scanner.nextLine());
            
            // Validaciones
            if (pages <= 0) {
                System.out.println("❌ Error: El número de páginas debe ser mayor a 0");
                return;
            }
            
            if (price < 0) {
                System.out.println("❌ Error: El precio no puede ser negativo");
                return;
            }
            
            Notebook notebook = new Notebook(id, brand, pages, size, price);
            notebooks.add(notebook);
            jsonService.saveNotebooksToFile(notebooks);
            
            System.out.println("✅ Notebook agregado exitosamente!");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Entrada numérica inválida");
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    // READ - Mostrar todos los notebooks
    public void displayAllNotebooks() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📚 TODOS LOS NOTEBOOKS");
        System.out.println("=".repeat(50));
        
        if (notebooks.isEmpty()) {
            System.out.println("📭 No se encontraron notebooks.");
        } else {
            for (int i = 0; i < notebooks.size(); i++) {
                System.out.println((i + 1) + ". " + notebooks.get(i));
            }
        }
    }
    
    // READ - Buscar notebook por ID
    public void searchNotebookById() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🔍 BUSCAR NOTEBOOK");
        System.out.println("=".repeat(50));
        
        System.out.print("Ingrese ID del notebook a buscar: ");
        String id = scanner.nextLine();
        
        Notebook notebook = findNotebookById(id);
        if (notebook != null) {
            System.out.println("✅ Notebook encontrado: " + notebook);
        } else {
            System.out.println("❌ No se encontró notebook con ID: " + id);
        }
    }
    
    // UPDATE - Actualizar notebook
    public void updateNotebook() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("✏️ ACTUALIZAR NOTEBOOK");
        System.out.println("=".repeat(50));
        
        System.out.print("Ingrese ID del notebook a actualizar: ");
        String id = scanner.nextLine();
        
        Notebook notebook = findNotebookById(id);
        if (notebook == null) {
            System.out.println("❌ No se encontró notebook con ID: " + id);
            return;
        }
        
        System.out.println("📋 Datos actuales: " + notebook);
        System.out.println("\nIngrese nuevos datos (presione Enter para mantener valor actual):");
        
        try {
            System.out.print("Marca (" + notebook.getBrand() + "): ");
            String brand = scanner.nextLine();
            if (!brand.isEmpty()) notebook.setBrand(brand);
            
            System.out.print("Páginas (" + notebook.getPages() + "): ");
            String pagesStr = scanner.nextLine();
            if (!pagesStr.isEmpty()) {
                int pages = Integer.parseInt(pagesStr);
                if (pages > 0) {
                    notebook.setPages(pages);
                } else {
                    System.out.println("❌ El número de páginas debe ser mayor a 0");
                    return;
                }
            }
            
            System.out.print("Tamaño (" + notebook.getSize() + "): ");
            String size = scanner.nextLine();
            if (!size.isEmpty()) notebook.setSize(size);
            
            System.out.print("Precio ($" + notebook.getPrice() + "): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) {
                double price = Double.parseDouble(priceStr);
                if (price >= 0) {
                    notebook.setPrice(price);
                } else {
                    System.out.println("❌ El precio no puede ser negativo");
                    return;
                }
            }
            
            jsonService.saveNotebooksToFile(notebooks);
            System.out.println("✅ Notebook actualizado exitosamente!");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Entrada numérica inválida");
        }
    }
    
    // DELETE - Eliminar notebook
    public void deleteNotebook() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🗑️ ELIMINAR NOTEBOOK");
        System.out.println("=".repeat(50));
        
        System.out.print("Ingrese ID del notebook a eliminar: ");
        String id = scanner.nextLine();
        
        Notebook notebook = findNotebookById(id);
        if (notebook != null) {
            notebooks.remove(notebook);
            jsonService.saveNotebooksToFile(notebooks);
            System.out.println("✅ Notebook eliminado exitosamente!");
        } else {
            System.out.println("❌ No se encontró notebook con ID: " + id);
        }
    }
    
    // Método auxiliar para buscar por ID
    private Notebook findNotebookById(String id) {
        for (Notebook notebook : notebooks) {
            if (notebook.getId().equals(id)) {
                return notebook;
            }
        }
        return null;
    }
    
    // Mostrar estadísticas
    public void showStatistics() {
        if (notebooks.isEmpty()) {
            System.out.println("📭 No hay notebooks para mostrar estadísticas.");
            return;
        }
        
        int totalNotebooks = notebooks.size();
        double avgPrice = notebooks.stream().mapToDouble(Notebook::getPrice).average().orElse(0);
        double avgPages = notebooks.stream().mapToDouble(Notebook::getPages).average().orElse(0);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 ESTADÍSTICAS");
        System.out.println("=".repeat(50));
        System.out.println("Total de notebooks: " + totalNotebooks);
        System.out.printf("Precio promedio: $%.2f\n", avgPrice);
        System.out.printf("Páginas promedio: %.1f\n", avgPages);
        
        // Encontrar marca más común (implementación básica)
        String mostCommonBrand = findMostCommonBrand();
        System.out.println("Marca más común: " + mostCommonBrand);
    }
    
    private String findMostCommonBrand() {
        // Implementación simple para encontrar la marca más común
        java.util.Map<String, Integer> brandCount = new java.util.HashMap<>();
        for (Notebook notebook : notebooks) {
            brandCount.put(notebook.getBrand(), brandCount.getOrDefault(notebook.getBrand(), 0) + 1);
        }
        
        return brandCount.entrySet().stream()
                .max(java.util.Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
    
    public ArrayList<Notebook> getAllNotebooks() {
        return notebooks;
    }
    
    public void closeScanner() {
        scanner.close();
    }
}
