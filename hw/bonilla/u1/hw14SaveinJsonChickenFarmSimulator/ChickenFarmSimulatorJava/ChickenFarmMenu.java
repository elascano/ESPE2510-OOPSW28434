package ChickenFarmSimulatorJava;

import java.io.*;
import java.util.*;

public class ChickenFarmMenu {
    private List<ChickenCoop> coops;
    private String fileName = "Chickens.txt"; 

    public ChickenFarmMenu() {
        this.coops = new ArrayList<>();
        loadChickens();
    }

    private void loadChickens() {
        File file = new File(fileName);
        if (!file.exists()) {
            coops.add(new ChickenCoop("Happy Hens Coop"));
            coops.add(new ChickenCoop("Sunrise Nest"));
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Map<String, ChickenCoop> coopsDict = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String color = parts[2];
                int age = Integer.parseInt(parts[3]);
                boolean molting = Boolean.parseBoolean(parts[4]);
                String coopName = parts[5];

                coopsDict.putIfAbsent(coopName, new ChickenCoop(coopName));
                coopsDict.get(coopName).addChicken(new Chicken(id, name, color, age, molting));
            }
            this.coops = new ArrayList<>(coopsDict.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveChickens() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (ChickenCoop coop : coops) {
                for (Chicken ch : coop.getChickens()) {
                    pw.printf("%d,%s,%s,%d,%b,%s%n",
                            ch.getId(), ch.getName(), ch.getColor(),
                            ch.getAge(), ch.isMolting(), coop.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayChickens(ChickenCoop coop) {
        System.out.println("\n---- Chickens in " + coop.getName() + " ----");
        List<Chicken> chickens = coop.getChickens();
        if (chickens.isEmpty()) {
            System.out.println("No chickens found.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s %-5s %-10s%n",
                "ID", "Name", "Color", "Age", "Molting");
        for (Chicken ch : chickens) {
            System.out.printf("%-5d %-15s %-15s %-5d %-10s%n",
                    ch.getId(), ch.getName(), ch.getColor(),
                    ch.getAge(), ch.isMolting() ? "Yes" : "No");
        }
    }

    private ChickenCoop selectCoop(Scanner sc) {
        while (true) {
            System.out.println("\nSelect a Coop:");
            for (int i = 0; i < coops.size(); i++) {
                System.out.println((i + 1) + ". " + coops.get(i).getName());
            }
            System.out.print("Enter your choice: ");
            try {
                int num = Integer.parseInt(sc.nextLine());
                if (num >= 1 && num <= coops.size()) {
                    return coops.get(num - 1);
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private void addChicken(Scanner sc) {
        ChickenCoop coop = selectCoop(sc);
        System.out.print("Enter chicken name: ");
        String name = sc.nextLine();
        System.out.print("Enter chicken color: ");
        String color = sc.nextLine();

        int age;
        while (true) {
            System.out.print("Enter chicken age (number): ");
            try {
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) break;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid age. Enter a valid number.");
        }

        boolean molting;
        while (true) {
            System.out.print("Is the chicken molting? (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("yes") || ans.equals("y")) {
                molting = true;
                break;
            } else if (ans.equals("no") || ans.equals("n")) {
                molting = false;
                break;
            }
            System.out.println("Invalid input. Enter yes or no.");
        }

        int nextId = coop.getNextChickenId();
        coop.addChicken(new Chicken(nextId, name, color, age, molting));
        saveChickens();
        System.out.println("\nThe chicken was added successfully!\n");
    }

    private void editChicken(Scanner sc) {
        ChickenCoop coop = selectCoop(sc);
        displayChickens(coop);
        System.out.print("Enter chicken ID to edit: ");
        int id = Integer.parseInt(sc.nextLine());
        Chicken chicken = coop.getChickens().stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
        if (chicken == null) {
            System.out.println("Chicken not found.");
            return;
        }

        System.out.print("Enter new name (press enter to keep \"" + chicken.getName() + "\"): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) chicken.setName(name);

        System.out.print("Enter new color (press enter to keep \"" + chicken.getColor() + "\"): ");
        String color = sc.nextLine();
        if (!color.isEmpty()) chicken.setColor(color);

        System.out.print("Enter new age (press enter to keep \"" + chicken.getAge() + "\"): ");
        String ageStr = sc.nextLine();
        if (!ageStr.isEmpty()) chicken.setAge(Integer.parseInt(ageStr));

        System.out.print("Is the chicken molting? (yes/no, press enter to keep \"" + 
                         (chicken.isMolting() ? "Yes" : "No") + "\"): ");
        String molting = sc.nextLine().trim().toLowerCase();
        if (molting.equals("yes") || molting.equals("y")) chicken.setMolting(true);
        else if (molting.equals("no") || molting.equals("n")) chicken.setMolting(false);

        saveChickens();
        System.out.println("\nChicken updated successfully.\n");
    }

    private void deleteChicken(Scanner sc) {
        ChickenCoop coop = selectCoop(sc);
        displayChickens(coop);
        System.out.print("Enter chicken ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        Chicken target = coop.getChickens().stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
        if (target == null) {
            System.out.println("Chicken not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this chicken? (yes/no): ");
        String ans = sc.nextLine().trim().toLowerCase();
        if (ans.startsWith("y")) {
            coop.getChickens().remove(target);
            saveChickens();
            System.out.println("Chicken deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void searchChicken(Scanner sc) {
        ChickenCoop coop = selectCoop(sc);
        System.out.print("Enter chicken ID to search: ");
        int id = Integer.parseInt(sc.nextLine());
        Chicken chicken = coop.getChickens().stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
        if (chicken == null) {
            System.out.println("Chicken not found.");
        } else {
            System.out.println(chicken);
        }
    }

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Welcome to the Chicken Farm Simulator ---");
            System.out.println("1. View Chickens");
            System.out.println("2. Add Chicken");
            System.out.println("3. Edit Chicken");
            System.out.println("4. Delete Chicken");
            System.out.println("5. Search Chicken");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> displayChickens(selectCoop(sc));
                case "2" -> addChicken(sc);
                case "3" -> editChicken(sc);
                case "4" -> deleteChicken(sc);
                case "5" -> searchChicken(sc);
                case "6" -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void main(String[] args) {
        ChickenFarmMenu menu = new ChickenFarmMenu();
        menu.mainMenu();
    }
}